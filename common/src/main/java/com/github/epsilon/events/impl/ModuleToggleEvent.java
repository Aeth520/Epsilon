package com.github.epsilon.events.impl;

import com.github.epsilon.modules.Module;

/**
 * Posted to the EventBus when a module is enabled or disabled.
 */
public class ModuleToggleEvent {

    private final Module module;
    private final boolean enabled;

    public ModuleToggleEvent(Module module, boolean enabled) {
        this.module = module;
        this.enabled = enabled;
    }

    public Module getModule() {
        return module;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
