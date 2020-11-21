package com.ilyabuglakov.composite.service.component;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.bean.component.Composite;
import com.ilyabuglakov.composite.bean.component.CompositeLeaf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ComponentParser initializes with Component type, which represents the type of Component, that
 * parser should create. Components are creates by parsing String with regex pattern, which is
 * taken from ComponentType.
 *
 * ComponentParser implements pattern ChainOfResponsibility.
 */
public class ComponentParser {
    private final ComponentType leafType;
    private final ComponentType nodeType;
    private ComponentParser next;

    public ComponentParser(ComponentType nodeType, ComponentType leafType) {
        this.leafType = leafType;
        this.nodeType = nodeType;
    }

    /**
     * Method sets next parser in parsers chain
     * @param next - next parser
     * @return next parser
     */
    public ComponentParser setNext(ComponentParser next) {
        this.next = next;
        return next;
    }

    public ComponentType getLeafType(){
        return leafType;
    }

    /**
     * Method uses regex pattern to create nodes from provided string. each new node also will be
     * held by all next parsers in chain.
     *
     * @param source - source String
     * @return Component<String>, created from provided String
     */
    public Component<String> parse(String source) {
        /*
        Each pattern group is another node in tree
         */
        List<String> results = Pattern.compile(leafType.getDelimiterPattern()).matcher(source)
                .results()
                .flatMap(mr -> Stream.iterate(1, i->i<=mr.groupCount(), i -> ++i).map(mr::group))
                .filter(Objects::nonNull)
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());
        Component<String> node = new Composite(nodeType, new ArrayList<>());
        Function<String, Component<String>> leafCreator;
        if(next == null) {
            leafCreator = str -> new CompositeLeaf(leafType, str);
        } else {
            leafCreator = next::parse;
        }
        results.stream()
                .map(leafCreator)
                .forEach(node::add);
        return node;
    }
}
