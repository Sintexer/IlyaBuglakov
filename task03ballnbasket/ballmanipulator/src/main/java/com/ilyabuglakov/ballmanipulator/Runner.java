package main.java.com.ilyabuglakov.ballmanipulator;

import main.java.com.ilyabuglakov.ballmanipulator.controller.BasketController;

/**
 * Runner is the entry point of application
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class Runner {

    /**
     * This method creates and runs the basket controller
     * @param args - unused
     */
    public static void main(String[] args) {
        BasketController basketController = new BasketController();
        basketController.run();
    }

}
