package com.github.epsilon.settings.impl;

import com.github.epsilon.settings.Setting;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.awt.*;
import java.util.function.Consumer;

public class ColorSetting extends Setting<Color> {

    private final boolean allowAlpha;

    public ColorSetting(String name, Color defaultValue, boolean allowAlpha, Dependency dependency) {
        super(name, dependency, null);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.allowAlpha = allowAlpha;
    }

    public ColorSetting(String name, Color defaultValue, boolean allowAlpha, Dependency dependency, Consumer<Color> onChanged) {
        super(name, dependency, onChanged);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.allowAlpha = allowAlpha;
    }

    public boolean isAllowAlpha() {
        return allowAlpha;
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(value.getRGB());
    }

    @Override
    public void deserialize(JsonElement element) {
        if (element != null && element.isJsonPrimitive()) {
            int argb = element.getAsInt();
            Color c = new Color(argb, true);
            if (!allowAlpha) {
                c = new Color(c.getRed(), c.getGreen(), c.getBlue());
            }
            this.value = c;
        }
    }

}
