package com.ilyabuglakov.stringmanipulator.service.input;

/**
 * InputValidator helps to validate numbers, represented as Strings.
 */
public class InputValidator {

    private InputValidator() {
    }

    /**
     * Checks, if int in String valid or not
     *
     * @param source String with int
     * @return true, if int in String can be parsed
     */
    public static boolean validInt(String source) {
        try {
            int a = Integer.parseInt(source);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
