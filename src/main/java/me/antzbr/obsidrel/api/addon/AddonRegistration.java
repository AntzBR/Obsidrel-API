package me.antzbr.obsidrel.api.addon;

public interface AddonRegistration extends AutoCloseable {

    String ownerId();

    boolean active();

    @Override
    void close();
}
