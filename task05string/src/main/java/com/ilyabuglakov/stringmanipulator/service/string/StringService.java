package com.ilyabuglakov.stringmanipulator.service.string;

public class StringService {

    public String trim(String source){
        return source.replaceAll("\\s+", " ").trim();
    }

}
