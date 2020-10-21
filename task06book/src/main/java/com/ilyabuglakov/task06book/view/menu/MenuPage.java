package com.ilyabuglakov.task06book.view.menu;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.bean.MenuPageName;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuPage {
    protected List<CommandName> options;
    private final MenuPageName name;


    public MenuPage(MenuPageName name){
        this.name = name;
        options = new ArrayList<>();
    }


    List<CommandName> getOptions(){
        return new ArrayList<>(options);
    }

    public MenuPageName getName(){
        return name;
    }

}
