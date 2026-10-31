# Pack publication contract

`PackAPI.publication()` returns a read-only `PackPublicationView` describing the Core's current resource pack.

The snapshot contains the active distribution provider, reported delivery URL, SHA-1 of the local build, and `built`, `stale`, `configured`, `published` and `publishing` booleans.

`readyForProxy()` means Core reports that the current build is published, is not stale, has a valid SHA-1, and has a usable absolute HTTP(S) URL. It does **not** prove that a proxy can download the exact bytes; the proxy must still validate the downloaded SHA-1 and check that layered packs are mutually compatible.

The method has a safe default implementation so third-party `PackAPI` implementors remain binary compatible.

### Versioning and release

This is an additive API change. The repository source includes this contract, but it has **not yet been published as a new Maven artifact/version**. Existing consumers can keep the released API dependency and use reflective feature detection. A separately versioned public API release is needed before external plugins can compile directly against the new types.

### Scope

This contract is intentionally read-only. It does not transfer resource-pack delivery authority. Automatic handover requires an authenticated, verified proxy readiness signal and a short-lived lease with fallback. Until that is implemented and tested, Core keeps responsibility for delivery.
