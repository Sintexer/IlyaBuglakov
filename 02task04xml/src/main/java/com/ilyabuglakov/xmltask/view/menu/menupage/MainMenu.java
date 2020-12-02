package com.ilyabuglakov.xmltask.view.menu.menupage;


import com.ilyabuglakov.xmltask.model.CommandName;
import com.ilyabuglakov.xmltask.model.MenuPageName;
import com.ilyabuglakov.xmltask.view.menu.MenuPage;

public class MainMenu extends MenuPage {

    public MainMenu() {
        super(MenuPageName.MAIN_MENU);
        options.add(CommandName.DOM);
        options.add(CommandName.SAX);
        options.add(CommandName.EXIT);
    }
}
