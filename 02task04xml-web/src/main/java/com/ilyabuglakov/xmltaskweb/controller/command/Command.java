package com.ilyabuglakov.xmltaskweb.controller.command;

import com.ilyabuglakov.xmltaskweb.exception.InvalidInpitXMLException;
import com.ilyabuglakov.xmltaskweb.exception.ParserException;
import com.ilyabuglakov.xmltaskweb.exception.SchemaException;
import com.ilyabuglakov.xmltaskweb.model.gem.Gem;

import java.util.Set;

/**
 * The Functional interface for application servlet needs
 */
@FunctionalInterface
public interface Command {
    Set<Gem> execute(String xml) throws InvalidInpitXMLException, ParserException;
}
