package com.ilyabuglakov.composite.bean.component;

public class Symbol implements Component<String> {

    private String content;

    public void add(Component<String> component) {

    }

    public void remove(Component<String> component) {

    }

    public String collect() {
        return content;
    }
}
