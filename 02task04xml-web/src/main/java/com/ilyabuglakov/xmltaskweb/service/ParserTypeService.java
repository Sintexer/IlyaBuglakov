package com.ilyabuglakov.xmltaskweb.service;

import com.ilyabuglakov.xmltaskweb.model.ParserType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParserTypeService {

    public static List<String> getParserTypeStringList(){
        return Arrays.stream(ParserType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
