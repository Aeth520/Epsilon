package com.github.epsilon.settings.impl;

import com.github.epsilon.settings.Setting;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.function.Consumer;

public class ButtonSetting extends Setting<Runnable> {

    public ButtonSetting(String name, Runnable func, Dependency dependency) {
        super(name, dependency, null);
        this.value = func;
        this.defaultValue = func;
    }

    public ButtonSetting(String name, Runnable func, Dependency dependency, Consumer<Runnable> onChanged) {
        super(name, dependency, onChanged);
        this.value = func;
        this.defaultValue = func;
    }

    @Override
    public JsonElement serialize() {
        return null; // Button settings are not serialized
    }

    @Override
    public void deserialize(JsonElement element) {
        // Button settings are not deserialized - their value is the Runnable itself
    }

}
