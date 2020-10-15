package com.ilyabuglakov.stringmanipulator;

import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;

/**
 * Main class, runs the ApplicationController.
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationController controller = ApplicationController.getInstance();
        controller.start();
    }
}
