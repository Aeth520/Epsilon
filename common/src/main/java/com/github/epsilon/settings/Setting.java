package com.github.epsilon.settings;

import com.github.epsilon.assets.i18n.TranslateComponent;
import com.github.epsilon.events.bus.EventBus;
import com.github.epsilon.events.impl.SettingChangedEvent;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public abstract class Setting<V> {

    protected final String name;
    protected V value;
    protected V defaultValue;
    protected final Dependency dependency;
    protected final List<Consumer<V>> onChangeListeners = new ArrayList<>();
    protected final List<String> aliases = new ArrayList<>();
    protected SettingGroup group;
    protected boolean suppressCallbacks = false;

    protected TranslateComponent translateComponent;

    public Setting(String name, Dependency dependency, Consumer<V> onChanged) {
        this.name = name;
        this.dependency = dependency;
        if (onChanged != null) {
            this.onChangeListeners.add(onChanged);
        }
    }

    public void initTranslateComponent(TranslateComponent component) {
        this.translateComponent = component;
    }

    public TranslateComponent getTranslateComponent() {
        return translateComponent;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return translateComponent != null ? translateComponent.getTranslatedName() : name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        if (!suppressCallbacks) {
            for (Consumer<V> listener : onChangeListeners) {
                listener.accept(value);
            }
            EventBus.INSTANCE.post(new SettingChangedEvent(this, oldValue, value));
        }
    }

    public void setValueSilently(V value) {
        this.value = value;
    }

    public void reset() {
        this.value = this.defaultValue;
    }

    public V getDefaultValue() {
        return defaultValue;
    }

    public boolean isAvailable() {
        return dependency != null && this.dependency.check();
    }

    public SettingGroup getGroup() {
        return group;
    }

    @SuppressWarnings("unchecked")
    public <S extends Setting<V>> S group(SettingGroup group) {
        this.group = group;
        return (S) this;
    }

    /**
     * Adds a change listener that is called whenever the value changes.
     */
    @SuppressWarnings("unchecked")
    public <S extends Setting<V>> S addChangeListener(Consumer<V> listener) {
        onChangeListeners.add(listener);
        return (S) this;
    }

    /**
     * Removes a previously added change listener.
     */
    public void removeChangeListener(Consumer<V> listener) {
        onChangeListeners.remove(listener);
    }

    /**
     * Returns an unmodifiable view of all change listeners.
     */
    public List<Consumer<V>> getChangeListeners() {
        return Collections.unmodifiableList(onChangeListeners);
    }

    /**
     * Adds an alias (legacy name) for this setting. Used for config migration when settings are renamed.
     */
    @SuppressWarnings("unchecked")
    public <S extends Setting<V>> S alias(String alias) {
        aliases.add(alias);
        return (S) this;
    }

    /**
     * Returns the list of aliases for this setting.
     */
    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    /**
     * Sets whether callbacks (listeners and event posting) should be suppressed.
     * Used during config loading to avoid triggering side effects.
     */
    public void setSuppressCallbacks(boolean suppress) {
        this.suppressCallbacks = suppress;
    }

    /**
     * Serialize this setting's value to a JSON element.
     */
    public abstract JsonElement serialize();

    /**
     * Deserialize this setting's value from a JSON element.
     */
    public abstract void deserialize(JsonElement element);

    @FunctionalInterface
    public interface Dependency {
        boolean check();
    }

    public Dependency getDependency() {
        return dependency;
    }

}
