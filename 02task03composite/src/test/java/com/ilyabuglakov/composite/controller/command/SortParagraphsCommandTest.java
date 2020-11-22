package com.ilyabuglakov.composite.controller.command;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.service.component.ComponentParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SortParagraphsCommandTest {

    public final Logger logger = LogManager.getLogger(this.getClass().getName());

    public static final String text =
            "\t\tabcd. efg.\n" +
            "\t\ta. b. c.\n" +
            "\t\ta.";
    public static final String sortedText =
            "\t\ta.\n" +
                    "\t\tabcd. efg.\n" +
                    "\t\ta. b. c.";

    @Test
    public void testExecute() {
        Command command = new  SortParagraphsCommand();
        ComponentParser parser = new ComponentParser(ComponentType.TEXT, ComponentType.PARAGRAPH);
        parser.setNext(new ComponentParser(ComponentType.PARAGRAPH, ComponentType.SENTENCE));
        String actual = command.execute(parser.parse(text));
        logger.info(actual);
        assertEquals(actual, sortedText);
    }
}