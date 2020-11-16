package com.ilyabuglakov.composite.bean.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Composite implements Component<String> {

    private final ComponentType componentType;
    private List<Component<String>> children;

    public Composite(ComponentType componentType, List<Component<String>> children) {
        this.componentType = componentType;
        this.children = new ArrayList<>(children);
    }

    public void add(Component<String> component) {
        children.add(component);
    }

    public void remove(Component<String> component) {
        children.remove(component);
    }

    public String collect() {
        return children.stream()
                .map(Component<String>::collect)
                .collect(Collectors.joining());
    }
}
