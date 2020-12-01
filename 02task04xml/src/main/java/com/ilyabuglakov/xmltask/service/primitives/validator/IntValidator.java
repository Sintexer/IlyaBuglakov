package com.ilyabuglakov.xmltask.service.primitives.validator;

public class IntValidator {
    private IntValidator() {
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
