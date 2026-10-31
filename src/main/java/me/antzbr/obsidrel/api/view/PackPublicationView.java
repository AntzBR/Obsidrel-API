package me.antzbr.obsidrel.api.view;

/**
 * Read-only snapshot of Core's pack publication state.
 *
 * This describes what Core knows; it does not claim that a proxy has downloaded
 * the ZIP or verified its bytes. A proxy must independently check the content.
 *
 * @param provider configured distribution provider
 * @param url provider's current delivery URL, possibly empty or player-dependent
 * @param sha1 SHA-1 of the current local build, or an empty string if unavailable
 * @param built whether a local ZIP exists
 * @param stale whether inputs changed since that ZIP was built
 * @param configured whether the distribution provider is configured
 * @param published whether the provider reports the current build published
 * @param publishing whether publication is currently in progress
 * @param reason non-secret diagnostic explaining an unavailable state
 */
public record PackPublicationView(String provider, String url, String sha1,
        boolean built, boolean stale, boolean configured, boolean published,
        boolean publishing, String reason) {

    public PackPublicationView {
        provider = provider == null ? "" : provider;
        url = url == null ? "" : url;
        sha1 = sha1 == null ? "" : sha1;
        reason = reason == null ? "" : reason;
    }

    public static PackPublicationView unavailable() {
        return new PackPublicationView("", "", "", false, false, false,
                false, false, "publication snapshot is not available");
    }

    /**
     * True only if Core reports a published, current build with a usable
     * checksum and an absolute HTTP(S) URL. HTTP transport security and the
     * actual ZIP bytes must still be independently verified by the proxy.
     */
    public boolean readyForProxy() {
        if (!built || stale || !configured || !published || publishing
                || !sha1.matches("[0-9a-fA-F]{40}")) return false;
        try {
            java.net.URI uri = java.net.URI.create(url);
            return uri.isAbsolute() && uri.getHost() != null
                    && ("https".equalsIgnoreCase(uri.getScheme())
                            || "http".equalsIgnoreCase(uri.getScheme()));
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
