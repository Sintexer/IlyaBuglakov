package com.ilyabuglakov.stringmanipulator.view.menu;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.beans.MenuPage;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private static Menu instance = new Menu();
    private Map<MenuPage, List<CommandName>> options = new EnumMap<>(MenuPage.class);
    private MenuPage currentPage = MenuPage.MAIN_MENU;

    private Menu() {
        options.put(MenuPage.MAIN_MENU, new ArrayList<>());
        add(MenuPage.MAIN_MENU, CommandName.INPUT_STRING);
        add(MenuPage.MAIN_MENU, CommandName.SHOW_STRING);
        add(MenuPage.MAIN_MENU, CommandName.CLEAN_THE_TEXT);
        add(MenuPage.MAIN_MENU, CommandName.DELETE_CONSONANT_WORDS);
        add(MenuPage.MAIN_MENU, CommandName.CHOOSE_LOCALE);
        add(MenuPage.MAIN_MENU, CommandName.EXIT);
    }

    public static Menu getInstance() {
        return instance;
    }

    private void add(MenuPage page, CommandName commandName) {
        options.get(page).add(commandName);
    }

    public void setCurrentPage(MenuPage currentPage) {
        this.currentPage = currentPage;
    }

    public List<CommandName> getOptions() {
        return options.get(currentPage);
    }

}
