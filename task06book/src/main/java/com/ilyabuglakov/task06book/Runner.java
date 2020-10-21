package com.ilyabuglakov.task06book;

import com.ilyabuglakov.task06book.controller.ApplicationController;

import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        ApplicationController.getInstance().start();
//        System.out.println(
//        Runner.class.getClassLoader().getResource("data")
//        );
    }
}
