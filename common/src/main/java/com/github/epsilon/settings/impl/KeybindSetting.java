package com.github.epsilon.settings.impl;

import com.github.epsilon.settings.Setting;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.function.Consumer;

public class KeybindSetting extends Setting<Integer> {

    public KeybindSetting(String name, int defaultValue, Dependency dependency) {
        super(name, dependency, null);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public KeybindSetting(String name, int defaultValue, Dependency dependency, Consumer<Integer> onChanged) {
        super(name, dependency, onChanged);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(value);
    }

    @Override
    public void deserialize(JsonElement element) {
        if (element != null && element.isJsonPrimitive()) {
            this.value = element.getAsInt();
        }
    }

}
