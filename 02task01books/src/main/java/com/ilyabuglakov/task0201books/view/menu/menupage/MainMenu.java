package com.ilyabuglakov.task0201books.view.menu.menupage;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MenuPageName;
import com.ilyabuglakov.task0201books.view.menu.MenuPage;

public class MainMenu extends MenuPage {

    public MainMenu() {
        super(MenuPageName.MAIN_MENU);
        options.add(CommandName.READ_PUBLICATIONS_FILE);
        options.add(CommandName.FLUSH_TO_FILE);
        options.add(CommandName.REMOVE_PUBLICATION);
        options.add(CommandName.FIND_BY_TAG);
        options.add(CommandName.SORT);
        options.add(CommandName.CHANGE_LOCALE);
        options.add(CommandName.EXIT);
    }
}
