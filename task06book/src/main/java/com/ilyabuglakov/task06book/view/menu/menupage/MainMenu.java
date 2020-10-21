package com.ilyabuglakov.task06book.view.menu.menupage;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.bean.MenuPageName;
import com.ilyabuglakov.task06book.view.menu.MenuPage;

public class MainMenu extends MenuPage {

    public MainMenu() {
        super(MenuPageName.MAIN_MENU);
        options.add(CommandName.ADD_BOOK);
        options.add(CommandName.SHOW_BOOK_REPOSITORY);
        options.add(CommandName.EXIT);
    }
}
