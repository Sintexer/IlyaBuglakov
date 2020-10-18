package com.ilyabuglakov.stringmanipulator.service;

import java.util.Collection;

/**
 * Decorates collections to display them in console
 */
public class OutputDecorator {

    private OutputDecorator() {
    }

    /**
     * Creates enumerated list as String from each collection element
     *
     * @param collection - elements to build list with
     * @return String with enumerated elements from collection
     */
    public static String toEnumeratedString(Collection<?> collection) {
        int i = 1;
        StringBuilder result = new StringBuilder();
        for (var element : collection) {
            result.append(i++ + ". " + element + "\n");
        }
        return result.toString();
    }

}
