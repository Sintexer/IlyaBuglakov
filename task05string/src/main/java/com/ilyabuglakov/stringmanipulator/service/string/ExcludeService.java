package com.ilyabuglakov.stringmanipulator.service.string;

import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * ExcludeService provides user with methods, tha will help delete
 * the certain pattern from source String, even only if some condition is present
 */
public class ExcludeService {

    private ExcludeService() {
    }

    /**
     * Deletes al patterns from source String
     * @param source - String to operate with
     * @param pattern - pattern of char sequences to delete
     * @return source String without patterns
     */
    public static String deleteAllByPattern(String source, Pattern pattern) {
        return pattern.matcher(source).replaceAll("");
    }

    /**
     * Deletes al patterns from source String, if detected match
     * falls into Predicate condition
     * @param source - String to operate with
     * @param pattern - pattern of char sequences to delete
     * @param predicate - Predicate, used for matches
     * @return source String without certain patterns
     */
    public static String deleteAllByPatternIf(String source, Pattern pattern, Predicate<MatchResult> predicate) {
        StringBuilder result = new StringBuilder(source);
        pattern.matcher(source)
                .results()
                .filter(predicate::test)
                .forEach(match -> result.delete(match.start(), match.end()));
        return result.toString();
    }

}
