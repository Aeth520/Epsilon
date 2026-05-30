package com.github.epsilon.settings.impl;

import com.github.epsilon.settings.Setting;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class IntSetting extends Setting<Integer> {

    private final int min;
    private final int max;
    private final int step;
    private final boolean percentageMode;

    public IntSetting(String name, int defaultValue, int min, int max, int step, Dependency dependency, boolean percentageMode) {
        super(name, dependency, null);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
        this.percentageMode = percentageMode;
    }

    public IntSetting(String name, int defaultValue, int min, int max, int step, Dependency dependency, boolean percentageMode, Consumer<Integer> onChanged) {
        super(name, dependency, onChanged);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
        this.percentageMode = percentageMode;
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(Mth.clamp(value, min, max));
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getStep() {
        return step;
    }

    public boolean isPercentageMode() {
        return percentageMode;
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(value);
    }

    @Override
    public void deserialize(JsonElement element) {
        if (element != null && element.isJsonPrimitive()) {
            this.value = Mth.clamp(element.getAsInt(), min, max);
        }
    }

}
