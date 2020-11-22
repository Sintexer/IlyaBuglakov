package com.ilyabuglakov.composite.controller.command;

import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.service.component.ComponentParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SortSentenceCommandTest {

    public final Logger logger = LogManager.getLogger(this.getClass().getName());

    public static final String text = "\t\tSome test text. To parse for various, cases etc. Not at all\n" +
            "and yet smth good, now! good luck.\n" +
            "\t\tNew Paragraph, test is going on. Sentence\n" +
            "Broken time sequence, end of text.";
    public static final String sortedText = "\t\tSome test text. To for etc parse, cases various. at Not all\n" +
            "and yet now smth, good! good luck.\n" +
            "\t\tis on, New test going Paragraph. of\n" +
            "end time text, Broken Sentence sequence.";

    @Test
    public void testExecute() {
        Command command = new SortSentenceCommand();
        ComponentParser parser = new ComponentParser(ComponentType.TEXT, ComponentType.PARAGRAPH);
        parser.setNext(new ComponentParser(ComponentType.PARAGRAPH, ComponentType.SENTENCE))
        .setNext(new ComponentParser(ComponentType.SENTENCE, ComponentType.WORD));
        String actual = command.execute(parser.parse(text));
        logger.info(actual);
        assertEquals(actual, sortedText);
    }
}