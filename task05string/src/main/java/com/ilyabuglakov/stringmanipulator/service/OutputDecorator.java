package com.ilyabuglakov.stringmanipulator.service;

import java.util.Collection;

public class OutputDecorator {

    public static String toEnumeratedString(Collection<?> collection){
        int i = 1;
        StringBuilder result = new StringBuilder();
        for(var element : collection){
            result.append(i++ +". " + element + "\n");
        }
        return result.toString();
    }

}
