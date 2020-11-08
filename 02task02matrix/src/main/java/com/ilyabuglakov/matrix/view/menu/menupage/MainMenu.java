package com.ilyabuglakov.matrix.view.menu.menupage;

import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.bean.MenuPageName;
import com.ilyabuglakov.matrix.view.menu.MenuPage;

public class MainMenu extends MenuPage {

    public MainMenu() {
        super(MenuPageName.MAIN_MENU);
        options.add(CommandName.READ_MATRIX);
        options.add(CommandName.SHOW_MATRIX);
        options.add(CommandName.FULL_LOCK);
        options.add(CommandName.STATE_LOCK_THREAD);
        options.add(CommandName.PREPARED_THREAD);
        options.add(CommandName.ARBITRATION_THREAD);
        options.add(CommandName.CHANGE_LOCALE);
        options.add(CommandName.EXIT);
    }
}
