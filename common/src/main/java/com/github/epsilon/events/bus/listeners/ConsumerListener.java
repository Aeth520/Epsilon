package com.github.epsilon.events.bus.listeners;

import com.github.epsilon.events.bus.EventPriority;

import java.util.function.Consumer;

/**
 * Listener that takes in a {@link java.util.function.Consumer}.
 */
public class ConsumerListener<T> implements IListener {
    private final Class<T> target;
    private final int priority;
    private final Consumer<T> executor;
    private final Object owner;

    public ConsumerListener(Class<T> target, int priority, Consumer<T> executor, Object owner) {
        this.target = target;
        this.priority = priority;
        this.executor = executor;
        this.owner = owner;
    }

    public ConsumerListener(Class<T> target, int priority, Consumer<T> executor) {
        this(target, priority, executor, null);
    }

    public ConsumerListener(Class<T> target, Consumer<T> executor) {
        this(target, EventPriority.MEDIUM, executor, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void call(Object event) {
        executor.accept((T) event);
    }

    @Override
    public Class<T> getTarget() {
        return target;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getOwner() {
        return owner;
    }

}
