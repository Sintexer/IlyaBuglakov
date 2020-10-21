package com.ilyabuglakov.task06book.view.menu.menupage;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.bean.MenuPageName;
import com.ilyabuglakov.task06book.view.menu.MenuPage;

public class MainMenu extends MenuPage {

    public MainMenu() {
        super(MenuPageName.MAIN_MENU);
        options.add(CommandName.ADD_BOOK);
        options.add(CommandName.REMOVE_BOOK);
        options.add(CommandName.READ_BOOKS_FILE);
        options.add(CommandName.FLUSH_TO_FILE);
        options.add(CommandName.FIND_BY_TAG);
        options.add(CommandName.EXIT);
    }
}
