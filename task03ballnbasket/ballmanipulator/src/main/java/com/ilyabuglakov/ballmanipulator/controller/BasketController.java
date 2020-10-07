package main.java.com.ilyabuglakov.ballmanipulator.controller;

import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;
import main.java.com.ilyabuglakov.ballmanipulator.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * BasketController
 * Controls the list of baskets and manages access to it
 * Also delegates input management to ViewController
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class BasketController {

    private List<Basket> baskets = new ArrayList<>();
    private View view = new View(this);

    /**
     * run() method start the dialog with user
     */
    public void run() {
        view.operateMainMenu();
    }

    /**
     * Manages access to the ist of Baskets
     *
     * @return List<Basket>
     */
    public List<Basket> getBaskets() {
        return baskets;
    }

    public List<Basket> setBaskets(List<Basket> baskets) {
        return this.baskets = new ArrayList<>(baskets);
    }

    public void setBasket(int index, Basket basket) {
        baskets.set(index, basket);
    }

}
