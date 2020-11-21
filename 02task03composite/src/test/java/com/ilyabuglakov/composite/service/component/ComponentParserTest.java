package com.ilyabuglakov.composite.service.component;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentParserTest {
    //TODO add logger

    private String text = "\t\tSome test text. To parse for various, cases etc. Not at all\n" +
            "and yet smth good, now! good luck.\n" +
            "\t\tNew Paragraph, test is going on. Sentence\n" +
            "Broken time sequence, end of text.";

    private ComponentParser parserChain;

    @BeforeClass
    public void initChain() {
        parserChain = new ComponentParser(ComponentType.TEXT, ComponentType.PARAGRAPH);
        parserChain.setNext(new ComponentParser(ComponentType.PARAGRAPH, ComponentType.SENTENCE))
                .setNext(new ComponentParser(ComponentType.SENTENCE, ComponentType.LEXEME))
                .setNext(new ComponentParser(ComponentType.LEXEME, ComponentType.WORD));
    }

    @DataProvider(name = "nodes")
    public Object[][] validationDataWords() {
        return new Object[][]{
                {
                        new ComponentParser(ComponentType.TEXT, ComponentType.PARAGRAPH),
                        ComponentType.PARAGRAPH,
                        Arrays.asList("\t\tSome test text. To parse for various, cases etc. Not at all\n" +
                                        "and yet smth good, now! good luck.",
                                "\n\t\tNew Paragraph, test is going on. Sentence\n" +
                                        "Broken time sequence, end of text.")
                },
                {
                        new ComponentParser(ComponentType.TEXT, ComponentType.SENTENCE),
                        ComponentType.SENTENCE,
                        Arrays.asList("\t\tSome test text.", " To parse for various, cases etc.", " Not at all\n" +
                                        "and yet smth good, now!", " good luck.",
                                "\n\t\tNew Paragraph, test is going on.", " Sentence\n" +
                                        "Broken time sequence, end of text.")
                },
                {
                        new ComponentParser(ComponentType.TEXT, ComponentType.LEXEME),
                        ComponentType.LEXEME,
                        Arrays.asList("\t\tSome ", "test ", "text. ", "To ", "parse ", "for ",
                                "various, ", "cases ", "etc. ", "Not ", "at ", "all\n",
                                "and ", "yet ", "smth ", "good, ", "now! ", "good ",
                                "luck.\n\t\t", "New ", "Paragraph, ", "test ", "is ",
                                "going ", "on. ", "Sentence\n", "Broken ", "time ", "sequence, ",
                                "end ", "of ", "text.")
                },
                {
                        new ComponentParser(ComponentType.TEXT, ComponentType.WORD),
                        ComponentType.WORD,
                        Arrays.asList("\t\t", "Some", " ", "test", " ", "text", ". ", "To", " ", "parse", " ", "for",
                                " ", "various", ", ", "cases", " ", "etc", ". ", "Not", " ", "at", " ", "all", "\n",
                                "and", " ", "yet", " ", "smth", " ", "good", ", ", "now", "! ", "good",
                                " ", "luck", ".\n\t\t", "New", " ", "Paragraph", ", ", "test", " ", "is", " ",
                                "going", " ", "on", ". ", "Sentence", "\n", "Broken", " ", "time", " ", "sequence",
                                ", ", "end", " ", "of", " ", "text", ".")
                },
                {
                        parserChain,
                        ComponentType.WORD,
                        Arrays.asList("\t\t", "Some", " ", "test", " ", "text", ".", " ", "To", " ", "parse", " ", "for",
                                " ", "various", ", ", "cases", " ", "etc", ".", " ", "Not", " ", "at", " ", "all", "\n",
                                "and", " ", "yet", " ", "smth", " ", "good", ", ", "now", "!", " ", "good",
                                " ", "luck", ".", "\n\t\t", "New", " ", "Paragraph", ", ", "test", " ", "is", " ",
                                "going", " ", "on", ".", " ", "Sentence", "\n", "Broken", " ", "time", " ", "sequence",
                                ", ", "end", " ", "of", " ", "text", ".")
                },
        };
    }

    @Test(dataProvider = "nodes")
    public void testParse(ComponentParser parser, ComponentType type, List<String> expected) {
        List<String> result = parser.parse(text).collectChildren(type).stream()
                .map(Component::collect)
                .collect(Collectors.toList());
        Assert.assertEquals(result.size(), expected.size());
        Assert.assertTrue(result.containsAll(expected));
    }

    @Test
    public void badParsersOrder() {
        ComponentParser parser = new ComponentParser(ComponentType.TEXT, ComponentType.SYMBOL);
        parser.setNext(new ComponentParser(ComponentType.SYMBOL, ComponentType.PARAGRAPH));
        Assert.assertEquals(parser.parse(text).collectChildren(ComponentType.PARAGRAPH).size(), 0);
    }
}