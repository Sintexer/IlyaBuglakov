package com.ilyabuglakov.composite.controller.command;

import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.service.component.ComponentParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SortLexemeCommandTest {

    public final Logger logger = LogManager.getLogger(this.getClass().getName());

    public static final String text = "\t\tSome test text. To parse for various, cases etc. Not at all\n" +
            "and yet smth good, now! good luck.\n" +
            "\t\tNew Paragraph, test is going on. Sentence\n" +
            "Broken time sequence, end of text.";
    public static final String sortedText = "\t\tParagraph, all and at cases parse various, Broken end etc. for going\n" +
            "good good, is luck. New Not now!\n" +
            "\t\tof on. Sentence sequence, smth Some test\n" +
            "test text. text. time To yet";

    @Test
    public void testExecute() {
        SortLexemeCommand command = new SortLexemeCommand();
        command.setSymbol('a');
        ComponentParser parser = new ComponentParser(ComponentType.TEXT, ComponentType.PARAGRAPH);
        parser.setNext(new ComponentParser(ComponentType.PARAGRAPH, ComponentType.SENTENCE))
                .setNext(new ComponentParser(ComponentType.SENTENCE, ComponentType.LEXEME));
        String result = command.execute(parser.parse(text));
        logger.info(result);
        assertEquals(result, sortedText);
    }
}