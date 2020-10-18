package com.ilyabuglakov.stringmanipulator.service.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ReplaceService provides user with possibilities to replace
 * some patterns in source String
 */
public class ReplaceService {

    /**
     * Replaces char's at certain positions in all words in String
     *
     * @param source - source String to operate with
     * @param index  - index of character in word to replace
     * @param symbol - character to replace with
     * @return source String with replaced characters in it
     */
    public String replaceSymbols(String source, int index, char symbol) {
        Matcher matcher = Pattern.compile("\\w+(\\w)").matcher(source);
        StringBuilder correctedContent = new StringBuilder(source);
        matcher.results()
                .filter(match -> match.start(1) - match.start() >= index)
                .forEach(match -> correctedContent.setCharAt(match.start() + index, symbol));
        return correctedContent.toString();
    }

    /**
     * Replaces each mistake in all words
     *
     * @param source     - source String to operate with
     * @param indicator  - the correct character before the wrong character
     * @param mistake    - the wrong character
     * @param correction - the character to replace with
     * @return source String with corrected mistakes
     */
    public String replaceMistake(String source, char indicator, char mistake, char correction) {
        String pattern = "" + indicator + mistake + "\\B";
        return source.replaceAll(pattern, "" + indicator + correction);
    }

    /**
     * Replace all words with certain length in String with the word
     *
     * @param source      - source String to operate with
     * @param wordLength  - the int length of the word to replace
     * @param replacement - the word to replace with
     * @return source String with replaced words
     */
    public String replaceWords(String source, int wordLength, String replacement) {
        String pattern = "\\b\\w{" + wordLength + "}\\b";
        return source.replaceAll(pattern, replacement);
    }

}
