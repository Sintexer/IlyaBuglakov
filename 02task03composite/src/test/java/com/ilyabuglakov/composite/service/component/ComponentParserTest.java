package com.ilyabuglakov.composite.service.component;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class ComponentParserTest {

    private String text = "\t\tSome test text. To parse for various, cases etc. Not at all\n" +
            "and yet smth good, now! good luck\n" +
            "\t\tNew Paragraph, test is going on. Sentence\n" +
            "Broken time sequence, end of text.";

    @DataProvider(name = "words")
    public Object[][] validationDataWords(){
        return new Object[][]{
                {
                        Arrays.asList("\t\t", "Some", " ", "test", "text", ". ", "To", " ", "parse", " ", "for", "various", ", ",
                                "cases", " ", "etc", ". ", "Not", " ", "at", "all", "and", "yet", "smth", "good", "now",
                                "luck", "\n\t\t", "New", " ", "Paragraph", ", ", "test", " ", "is", " ", "going", " ", "on", ". ",
                                "Sentence", "\\n", "Broken", " ", "time", " ", "sequence",", ", "end"," ", "of"," ", "text", ".")
                },
        }
    }

    @Test
    public void testParse() {

    }
}