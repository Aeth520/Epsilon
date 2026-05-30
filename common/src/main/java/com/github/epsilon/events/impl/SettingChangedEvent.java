package com.github.epsilon.events.impl;

import com.github.epsilon.settings.Setting;

/**
 * Posted to the EventBus whenever a setting's value changes.
 * Not posted during config loading (when suppressCallbacks is active).
 */
public class SettingChangedEvent {

    private final Setting<?> setting;
    private final Object oldValue;
    private final Object newValue;

    public SettingChangedEvent(Setting<?> setting, Object oldValue, Object newValue) {
        this.setting = setting;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Setting<?> getSetting() {
        return setting;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
