package com.ilyabuglakov.composite.bean.component;

public interface Component<T> {
    void add(Component<T> component);

    void remove(Component<T> component);

    T collect();
}
