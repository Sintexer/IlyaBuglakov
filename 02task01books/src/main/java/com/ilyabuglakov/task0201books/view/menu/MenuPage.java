package com.ilyabuglakov.task0201books.view.menu;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MenuPageName;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuPage {
    protected List<CommandName> options;
    private final MenuPageName name;


    public MenuPage(MenuPageName name) {
        this.name = name;
        options = new ArrayList<>();
    }


    List<CommandName> getOptions() {
        return new ArrayList<>(options);
    }

    public MenuPageName getName() {
        return name;
    }

}
