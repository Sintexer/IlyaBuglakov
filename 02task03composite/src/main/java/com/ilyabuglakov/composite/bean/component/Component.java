package com.ilyabuglakov.composite.bean.component;

import java.util.List;

/**
 * Component is abstract class used as the base for Composite and CompositeLeaf classes.
 *
 *
 * @param <T> - component type
 */
public abstract class Component<T> {

    private final ComponentType type;

    /**
     * Constructor sets the type of the Component
     * @param type - component type
     */
    public Component(ComponentType type) {
        this.type = type;
    }

    /**
     * Getter returns the type of the component
     * @return type of the component
     */
    public ComponentType getType() {
        return type;
    }

    /**
     * Should add node child
     * @param component - node child
     */
    public abstract void add(Component<T> component);

    /**
     * Should remove node child
     * @param component - node child
     */
    public abstract void remove(Component<T> component);

    /**
     * Should collect all node children with certain type
     * @param type - type of nodes to collect
     * @return List of children nodes with certain type
     */
    public abstract List<Component<T>> collectChildren(ComponentType type);

    /**
     * Should collapse all children nodes together by certain algorithm
     * @return total value of all sub nodes
     */
    public abstract T collect();
}
