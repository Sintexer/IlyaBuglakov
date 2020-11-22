package com.ilyabuglakov.composite.controller.command;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.bean.component.comparator.ComponentStringComparator;
import com.ilyabuglakov.composite.service.component.ComponentSorter;

import java.util.Comparator;
import java.util.stream.Collectors;

public class SortSentenceCommand implements Command {
    @Override
    public String execute(Component<String> root) {
        ComponentSorter sorter = new ComponentSorter();
        return root.collectChildren(ComponentType.SENTENCE).stream()
                .map(sentence -> sorter.sortWithFreezeDelimiters(sentence.collectChildren(ComponentType.WORD),
                        ComponentStringComparator.comparingWords()))
                .map(lst -> lst.stream().map(Component::collect).collect(Collectors.joining()))
                .collect(Collectors.joining());
    }
}
