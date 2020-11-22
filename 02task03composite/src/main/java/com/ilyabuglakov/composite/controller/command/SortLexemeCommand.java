package com.ilyabuglakov.composite.controller.command;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.bean.component.comparator.ComponentStringComparator;
import com.ilyabuglakov.composite.service.component.ComponentSorter;

import java.util.stream.Collectors;

public class SortLexemeCommand implements Command {

    private char symbol;

    @Override
    public String execute(Component<String> root) {
        ComponentSorter sorter = new ComponentSorter();
        return sorter.sortWithFreezeDelimiters(root.collectChildren(ComponentType.LEXEME),
                ComponentStringComparator.comparingLexeme(symbol)
                        .reversed()
                        .thenComparing(ComponentStringComparator.comparingByAlphabet()))
                .stream()
                .map(Component::collect)
                .collect(Collectors.joining());
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
