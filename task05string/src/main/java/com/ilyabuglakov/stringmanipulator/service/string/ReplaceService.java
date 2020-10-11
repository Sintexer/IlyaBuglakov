package com.ilyabuglakov.stringmanipulator.service.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceService {

    public String replaceSymbols(String content, int index, char symbol) {
        Matcher matcher = Pattern.compile("\\w+(\\w)").matcher(content);
        StringBuilder correctedContent = new StringBuilder(content);
        matcher.results()
                .filter(match -> match.start(1) - match.start() >= index)
                .forEach(match -> correctedContent.setCharAt(match.start() + index, symbol));
        return correctedContent.toString();
    }

    public String replaceMistake(String source, char indicator, char mistake, char correction) {
        String pattern = "" + indicator + mistake + "\\B";
        return source.replaceAll(pattern, "" + indicator + correction);
    }

    public String replaceWords(String source, int wordLength, String replacement) {
        String pattern = "\\b\\w{" + wordLength + "}\\b";
        return source.replaceAll(pattern, replacement);
    }

    public String deleteAllNotLetters(String source){
        return replaceAllMatched(source, "[\\W\\d_]", " ");
    }

    public String replaceAllMatched(String source, String pattern, String replacement){
        return source.replaceAll(pattern, replacement);
    }

}
