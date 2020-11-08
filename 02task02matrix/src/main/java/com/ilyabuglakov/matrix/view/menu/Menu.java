package com.ilyabuglakov.matrix.view.menu;


import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.bean.MenuPageName;
import com.ilyabuglakov.matrix.view.menu.menupage.MainMenu;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Menu class helps to associate menu options with menu pages.
 * Implemented as singleton.
 */
public class Menu {
    private static Menu instance = new Menu();
    private Map<MenuPageName, MenuPage> options = new EnumMap<>(MenuPageName.class);
    private MenuPageName currentPage = MenuPageName.MAIN_MENU;

    private Menu() {
        options.put(MenuPageName.MAIN_MENU, new MainMenu());
    }

    /**
     * Returns instance of singleton class
     *
     * @return Menu instance
     */
    public static Menu getInstance() {
        return instance;
    }

    /**
     * Sets current menu page
     *
     * @param currentPage - new current page
     */
    public void setCurrentPage(MenuPageName currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the list of options of current page
     */
    public List<CommandName> getOptions() {
        return options.get(currentPage).getOptions();
    }

}