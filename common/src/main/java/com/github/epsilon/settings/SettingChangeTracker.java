package com.github.epsilon.settings;

public class SettingChangeTracker {

    public static final SettingChangeTracker INSTANCE = new SettingChangeTracker();

    private volatile long revision = 0;

    private SettingChangeTracker() {
    }

    public long getRevision() {
        return revision;
    }

    public void increment() {
        revision++;
    }
}
