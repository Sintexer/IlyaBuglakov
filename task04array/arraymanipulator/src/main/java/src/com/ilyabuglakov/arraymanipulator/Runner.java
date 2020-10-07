package src.com.ilyabuglakov.arraymanipulator;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;

import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        ApplicationController controller = ApplicationController.getInstance();
        controller.showView();
    }
}
