package com.ilyabuglakov.composite.controller.command;

import com.ilyabuglakov.composite.bean.component.Component;

public interface Command {
    String execute(Component<String> root);
}
