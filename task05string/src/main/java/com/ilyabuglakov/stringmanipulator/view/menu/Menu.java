package com.ilyabuglakov.stringmanipulator.view.menu;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.beans.MenuPage;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Menu class helps to associate menu options with menu pages.
 * Implemented as singleton.
 */
public class Menu {
    private static Menu instance = new Menu();
    private Map<MenuPage, List<CommandName>> options = new EnumMap<>(MenuPage.class);
    private MenuPage currentPage = MenuPage.MAIN_MENU;

    private Menu() {
        options.put(MenuPage.MAIN_MENU, new ArrayList<>());
        add(MenuPage.MAIN_MENU, CommandName.INPUT_STRING);
        add(MenuPage.MAIN_MENU, CommandName.SHOW_STRING);
        add(MenuPage.MAIN_MENU, CommandName.REPLACE_POSITION);
        add(MenuPage.MAIN_MENU, CommandName.CORRECT_MISTAKES);
        add(MenuPage.MAIN_MENU, CommandName.REPLACE_WORDS);
        add(MenuPage.MAIN_MENU, CommandName.CLEAN_THE_TEXT);
        add(MenuPage.MAIN_MENU, CommandName.DELETE_CONSONANT_WORDS);
        add(MenuPage.MAIN_MENU, CommandName.CHOOSE_LOCALE);
        add(MenuPage.MAIN_MENU, CommandName.EXIT);
    }

    private void add(MenuPage page, CommandName commandName) {
        options.get(page).add(commandName);
    }

    /**
     * Returns instance of singleton class
     * @return Menu instance
     */
    public static Menu getInstance() {
        return instance;
    }

    /**
     * Sets current menu page
     * @param currentPage - new current page
     */
    public void setCurrentPage(MenuPage currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the list of options of current page
     */
    public List<CommandName> getOptions() {
        return options.get(currentPage);
    }

}
