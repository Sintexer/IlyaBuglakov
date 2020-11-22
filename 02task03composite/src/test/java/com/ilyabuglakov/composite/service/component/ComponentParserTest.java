package com.ilyabuglakov.composite.service.component;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.dal.file.BufferedTextReader;
import com.ilyabuglakov.composite.exception.FileReadException;
import com.ilyabuglakov.composite.service.PathService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentParserTest {

    private Logger logger = LogManager.getLogger(this.getClass());

    private String text = "\t\tSome test text. To parse for various, cases etc. Not at all\n" +
            "and yet smth good, now! good luck.\n" +
            "\t\tNew Paragraph, test is going on. Sentence\n" +
            "Broken time sequence, end of text.";

    private ComponentParser parserChain;

    @BeforeMethod
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
                                "\n",
                                "\t\tNew Paragraph, test is going on. Sentence\n" +
                                        "Broken time sequence, end of text.")
                },
                {
                        new ComponentParser(ComponentType.TEXT, ComponentType.SENTENCE),
                        ComponentType.SENTENCE,
                        Arrays.asList("\t\t", "Some test text.", " ", "To parse for various, cases etc.", " ", "Not at all\n" +
                                        "and yet smth good, now!", " ", "good luck.",
                                "\n\t\t", "New Paragraph, test is going on.", " ", "Sentence\n" +
                                        "Broken time sequence, end of text.")
                },
                {
                        new ComponentParser(ComponentType.TEXT, ComponentType.LEXEME),
                        ComponentType.LEXEME,
                        Arrays.asList("\t\t", "Some", " ", "test", " ", "text.", " ", "To", " ", "parse", " ", "for", " ",
                                "various,", " ", "cases", " ", "etc.", " ", "Not" ," ", "at", " ", "all", "\n",
                                "and", " ", "yet", " ", "smth", " ", "good,", " ", "now!", " ", "good", " ",
                                "luck.", "\n\t\t", "New" ," ", "Paragraph," ," ", "test", " ", "is" ," ",
                                "going" ," ", "on.", " ", "Sentence", "\n", "Broken", " ", "time", " ", "sequence,", " ",
                                "end", " ", "of", " ", "text.")
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
                        Arrays.asList("\t\t", "Some", " ", "test", " ", "text", ".", " ", "To", " ", "parse", " ", "for", " ",
                                "various", ",", " ", "cases", " ", "etc",".", " ", "Not" ," ", "at", " ", "all", "\n",
                                "and", " ", "yet", " ", "smth", " ", "good", ",", " ", "now", "!", " ", "good", " ",
                                "luck", ".", "\n", "\t\t", "New" ," ", "Paragraph", "," ," ", "test", " ", "is" ," ",
                                "going" ," ", "on", ".", " ", "Sentence", "\n", "Broken", " ", "time", " ", "sequence", ",", " ",
                                "end", " ", "of", " ", "text", ".")
                },
        };
    }

    @Test(dataProvider = "nodes")
    public void testParse(ComponentParser parser, ComponentType type, List<String> expected) {
        List<String> result = parser.parse(text).collectChildren(type).stream()
                .map(Component::collect)
                .collect(Collectors.toList());
        logger.info("Expected: " + expected.toString());
        logger.info("Actual: " + result.toString());
        Assert.assertEquals(result.size(), expected.size(), result.toString());
        Assert.assertTrue(result.containsAll(expected), result.toString());
    }

    @Test
    public void testIdentityTest(){
        parserChain.setNext(new ComponentParser(ComponentType.WORD, ComponentType.SYMBOL));
        String actual = parserChain.parse(text).collect();
        logger.info("Actual: " + actual);
        Assert.assertEquals(actual, text);
    }

    @Test
    public void badParsersOrder() {
        ComponentParser parser = new ComponentParser(ComponentType.TEXT, ComponentType.SYMBOL);
        parser.setNext(new ComponentParser(ComponentType.SYMBOL, ComponentType.PARAGRAPH));
        Assert.assertEquals(parser.parse(text).collectChildren(ComponentType.PARAGRAPH).size(), 0);
    }

    @Test
    public void testSymbolParse() {
        final String dir = "symbolTest/";
        final String sourceFile = "source.txt";
        final String expectedFile = "expected.txt";
        parserChain.setNext(new ComponentParser(ComponentType.WORD, ComponentType.SYMBOL));
        try(BufferedTextReader reader = new BufferedTextReader(PathService.getInstance().getResourcePath(dir+sourceFile), 256);) {
            String text = reader.next();
            List<String> expected = Files.readAllLines(Paths.get(PathService.getInstance()
                    .getResourcePath(dir+expectedFile).substring(1)))
                    .stream()
                    .flatMap(line -> Arrays.asList(line.split("[|]")).stream())
                    .collect(Collectors.toList());
            List<String> actual = parserChain.parse(text).collectChildren(ComponentType.SYMBOL).stream()
                    .map(Component::collect)
                    .collect(Collectors.toList());
            Assert.assertEquals(actual.size(), expected.size(), "\n" + expected.toString() + "\n" + actual.toString());
            Assert.assertTrue(actual.containsAll(expected), expected.toString() + "\n" + actual.toString());
        } catch (FileNotFoundException e) {
            logger.error("File not found exception", e);
        } catch (IOException e){
            logger.error("File IO exception", e);
        } catch (FileReadException e) {
            logger.error("File read exception", e);
        }
    }
}