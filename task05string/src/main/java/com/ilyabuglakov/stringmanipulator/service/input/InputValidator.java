package com.ilyabuglakov.stringmanipulator.service.input;

public class InputValidator {

    public static boolean validInt(String source) {
        try {
            int a = Integer.parseInt(source);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
