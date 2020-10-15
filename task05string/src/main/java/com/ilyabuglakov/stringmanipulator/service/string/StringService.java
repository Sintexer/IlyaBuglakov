package com.ilyabuglakov.stringmanipulator.service.string;

/**
 * Provides various methods to operate Strings
 */
public class StringService {

    private StringService(){
    }

    /**
     * Replaces numerous inner spaces in source String by only one
     * @param source - source String to trim
     * @return trimmed String
     */
    public static String innerTrim(String source) {
        return source.replaceAll("\\s+", " ");
    }

}
