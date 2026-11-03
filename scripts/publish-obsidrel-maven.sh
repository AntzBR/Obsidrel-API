#!/usr/bin/env bash
# Publish the artifacts produced by the successful Maven Central build.
# A separate temporary POM isolates this deploy-file call from the
# main project's Central publishing extension and deploy-plugin skip=true.
set -euo pipefail

: "${OBSIDREL_MAVEN_USERNAME:?Missing OBSIDREL_MAVEN_USERNAME secret}"
: "${OBSIDREL_MAVEN_TOKEN:?Missing OBSIDREL_MAVEN_TOKEN secret}"

REPOSITORY_URL="https://repo.obsidrel.com/releases"
ARTIFACT_DIR="${GITHUB_WORKSPACE:-$PWD}/maven-release-artifacts"
POM="${ARTIFACT_DIR}/pom.xml"
test -s "$POM"

COORDINATES="$(python3 - "$POM" <<'PY'
import sys
from xml.etree import ElementTree as ET
root = ET.parse(sys.argv[1]).getroot()
prefix = root.tag.split('}')[0] + '}' if root.tag.startswith('{') else ''
group = root.findtext(prefix + 'groupId')
artifact = root.findtext(prefix + 'artifactId')
version = root.findtext(prefix + 'version')
if group != 'io.github.antzbr' or artifact != 'ObsidrelAPI':
    raise SystemExit('Unexpected Maven coordinates in flattened POM')
if not version or '/' in version or '..' in version:
    raise SystemExit('Invalid Maven version in flattened POM')
print(version)
PY
)"
VERSION="$COORDINATES"
MAIN="${ARTIFACT_DIR}/ObsidrelAPI-${VERSION}.jar"
SOURCES="${ARTIFACT_DIR}/ObsidrelAPI-${VERSION}-sources.jar"
JAVADOC="${ARTIFACT_DIR}/ObsidrelAPI-${VERSION}-javadoc.jar"
for file in "$POM" "$MAIN" "$SOURCES" "$JAVADOC"; do
  test -s "$file" || { echo "Missing artifact: $file" >&2; exit 1; }
done

TEMP_DIR="$(mktemp -d)"
trap 'rm -rf "$TEMP_DIR"' EXIT
REMOTE_URL="${REPOSITORY_URL}/io/github/antzbr/ObsidrelAPI/${VERSION}"
FOUND=0
MISSING=0
for file in "$POM" "$MAIN" "$SOURCES" "$JAVADOC"; do
  if [[ "$file" == "$POM" ]]; then
    REMOTE_NAME="ObsidrelAPI-${VERSION}.pom"
  else
    REMOTE_NAME="$(basename "$file")"
  fi
  STATUS="$(curl -sSL --max-time 30 -o "$TEMP_DIR/remote" -w '%{http_code}' "${REMOTE_URL}/${REMOTE_NAME}")"
  case "$STATUS" in
    200)
      cmp -s "$file" "$TEMP_DIR/remote" || {
        echo "Refusing to overwrite a different published artifact: $REMOTE_NAME" >&2
        exit 1
      }
      FOUND=$((FOUND + 1))
      ;;
    404) MISSING=$((MISSING + 1)) ;;
    *) echo "Unexpected repository response: HTTP $STATUS ($REMOTE_NAME)" >&2; exit 1 ;;
  esac
done

if (( FOUND == 4 )); then
  echo "All published artifacts are identical; nothing to deploy."
  exit 0
fi
if (( FOUND > 0 )); then
  echo "Partial release detected ($FOUND present, $MISSING missing). Refusing automatic overwrite." >&2
  exit 1
fi

cat > "$TEMP_DIR/pom.xml" <<'XML'
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.obsidrel.internal</groupId>
  <artifactId>maven-repository-publisher</artifactId>
  <version>1</version>
</project>
XML

mvn -B --no-transfer-progress -f "$TEMP_DIR/pom.xml" \
  org.apache.maven.plugins:maven-deploy-plugin:3.1.4:deploy-file \
  "-DrepositoryId=obsidrel" \
  "-Durl=${REPOSITORY_URL}" \
  "-DpomFile=${POM}" \
  "-Dfile=${MAIN}" \
  "-Dsources=${SOURCES}" \
  "-Djavadoc=${JAVADOC}" \
  "-DgeneratePom=false"

echo "Obsidrel Maven publication completed: io.github.antzbr:ObsidrelAPI:${VERSION}"
