package com.github.epsilon.settings.impl;

import com.github.epsilon.settings.Setting;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class DoubleSetting extends Setting<Double> {

    private final double min;
    private final double max;
    private final double step;
    private final boolean percentageMode;

    public DoubleSetting(String name, double defaultValue, double min, double max, double step, Dependency dependency, boolean percentageMode) {
        super(name, dependency, null);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
        this.percentageMode = percentageMode;
    }

    public DoubleSetting(String name, double defaultValue, double min, double max, double step, Dependency dependency, boolean percentageMode, Consumer<Double> onChanged) {
        super(name, dependency, onChanged);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
        this.percentageMode = percentageMode;
    }

    @Override
    public void setValue(Double value) {
        super.setValue(Mth.clamp(value, min, max));
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStep() {
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
            this.value = Mth.clamp(element.getAsDouble(), min, max);
        }
    }

}
