# Self-hosted Obsidrel Maven repository

This is a **separate Reposilite instance**, serving only artifacts owned by us. It does not proxy Maven Central, Spigot or other upstream repositories. It does not replace the existing Maven Central publishing flow.

## VPS deployment (manual; no automated changes to other services)

1. Check free RAM, disk space, Docker Compose and existing reverse proxy configuration.
2. Create a DNS record for `repo.obsidrel.com` pointing to the VPS.
3. Copy `ops/reposilite/compose.yaml` to a dedicated directory such as `/opt/obsidrel-maven/compose.yaml`.
4. Run `docker compose up -d` in that directory after provisioning the first administrator token (see below).
5. Forward `https://repo.obsidrel.com` to `http://127.0.0.1:8097` through the **existing** HTTPS reverse proxy. Support WebSocket upgrades for the admin console, and do not expose port 8097 publicly. Set an appropriate upload body-size limit.
6. Enable the **public read, authenticated write** policy for the `releases` repository in Reposilite, with no external repository proxies.
7. Store backups of the Docker volume `reposilite-data`, including its embedded database and artifacts; keep a tested restore procedure.

For the **first run only**, supply `REPOSILITE_OPTS=--token admin:<temporary-secret>` as an environment variable when executing `docker compose up -d`. Sign in, open the console and issue `token-generate admin m` for a durable manager token. Then recreate the container with `REPOSILITE_OPTS` empty and invalidate the temporary credentials. Follow the [Reposilite Docker guide](https://reposilite.com/guide/docker) for this bootstrap sequence. Do not save tokens in a tracked compose file, shell script, or GitHub commit.

Create a separate deployment-only token in the Reposilite dashboard, scoped to `/releases/io/github/antzbr` with **write** permission. Use its alias as the Maven username and its generated secret as the password. Do not use the manager token for CI.

### Reverse proxy example (Nginx location)

Merge the following into the **existing TLS-enabled virtual host** for `repo.obsidrel.com`. Do not replace unrelated vhosts or certificates:

```nginx
location / {
    proxy_pass http://127.0.0.1:8097;
    proxy_http_version 1.1;
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection "upgrade";
    client_max_body_size 64m;
}
```

Use your normal ACME/Certbot procedure to issue HTTPS. Confirm `curl -I https://repo.obsidrel.com/releases/` responds over TLS before enabling GitHub publication.

## GitHub Actions setup

In **AntzBR/Obsidrel-API**, configure:

| Type | Name | Value |
| --- | --- | --- |
| Actions variable | `OBSIDREL_MAVEN_ENABLED` | `true` **only after the VPS is ready** |
| Actions secret | `OBSIDREL_MAVEN_USERNAME` | Reposilite publication token alias |
| Actions secret | `OBSIDREL_MAVEN_TOKEN` | Reposilite publication token secret |

When the variable is unset or not `true`, the existing tag workflow is unchanged. On future tagged releases, the existing `build` job continues to submit the signed artifact to Maven Central and upload to R2/GitHub. A **separate job** then downloads that same build's JAR, flattened POM, sources and Javadoc and deploys them to Reposilite. It refuses to replace an already-published version with different bytes. This job can be re-run independently if the private repository is temporarily down.

Because the POM deliberately sets `maven-deploy-plugin.skip=true`, the publisher runs `deploy-file` with a temporary minimal POM. This avoids triggering the Central Publishing Maven extension a second time. Maven credentials are provided by `actions/setup-java` in the dedicated job.

**Do not trigger a new release tag just to test the repository**: the original workflow publishes to the immutable Maven Central coordinate.

## Existing releases: bootstrap safely

The current `main` branch has API contract changes not yet released as a new version. **Do not build `main` under an existing version and upload that JAR**; that would create conflicting artifacts under the same Maven coordinates.

For historical versions, copy the published `.jar`, `.pom`, `-sources.jar`, and `-javadoc.jar` files from their **exact version on Maven Central**. Verify their checksums and upload them once to Reposilite using a deployment token and Maven's `deploy-file` goal. Do not republish any historical version to Maven Central.

## Consumer configuration

```xml
<repositories>
  <repository>
    <id>obsidrel</id>
    <url>https://repo.obsidrel.com/releases</url>
  </repository>
</repositories>
<dependencies>
  <dependency>
    <groupId>io.github.antzbr</groupId>
    <artifactId>ObsidrelAPI</artifactId>
    <version>1.0.0</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

Use a version that is actually published and verified in the selected repository. Never shade ObsidrelAPI into a plugin or addon.

Reposilite Docker: https://reposilite.com/guide/docker
