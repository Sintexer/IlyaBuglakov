package com.ilyabuglakov.stringmanipulator.service.string;

import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ExcludeService {

    private ExcludeService() {
    }

    public static String deleteAllByPattern(String source, Pattern pattern) {
        return pattern.matcher(source).replaceAll("");
    }

    public static String deleteAllByPatternIf(String source, Pattern pattern, Predicate<MatchResult> predicate) {
        StringBuilder result = new StringBuilder(source);
        pattern.matcher(source)
                .results()
                .filter(predicate::test)
                .forEach(match -> result.delete(match.start(), match.end()));
        return result.toString();
    }

}
