package com.ilyabuglakov.stringmanipulator.service.string;

public class StringService {

    private StringService(){
    }

    public static String innerTrim(String source) {
        return source.replaceAll("\\s+", " ");
    }

}
