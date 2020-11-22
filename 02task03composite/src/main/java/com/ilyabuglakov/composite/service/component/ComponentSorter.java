package com.ilyabuglakov.composite.service.component;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This sorter class will sort contentNodes in List without moving any delimiters from their place
 */
public class ComponentSorter {

    public List<Component<String>> sortWithFreezeDelimiters(List<Component<String>> nodes,
                                                            Comparator<Component<String>> comparator) {
        Map<Boolean, List<Integer>> indexes = IntStream.range(0, nodes.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> nodes.get(i).collect().matches(ComponentType.DELIMITER.getDelimiterPattern())));
        List<Component<String>> contentNodes = indexes.get(false).stream()
                .map(nodes::get)
                .collect(Collectors.toList());
        List<Component<String>> sorted = contentNodes.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        if(indexes.containsKey(true)) {
            indexes.get(true).forEach(i -> sorted.add(i, nodes.get(i)));
        }
        return sorted;
    }

}
