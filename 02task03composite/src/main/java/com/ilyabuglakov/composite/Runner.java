package com.ilyabuglakov.composite;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;
import com.ilyabuglakov.composite.service.component.ComponentParser;

public class Runner {
    public static void main(String[] args) {
        ComponentParser parser = new ComponentParser(ComponentType.TEXT ,ComponentType.WORD);
       // ComponentParser sentenceParser = new ComponentParser(ComponentType.SENTENCE);
//        parser.setNext(sentenceParser)
//            .setNext(new ComponentParser(ComponentType.LEXEME))
//            .setNext(new ComponentParser(ComponentType.WORD))
//            .setNext(new ComponentParser(ComponentType.SYMBOL));
        Component<String> result = parser.parse("\t\tIt has survived.. . not. only five centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the with the\n" +
                "release of Letraset sheets containing Lorem Ipsum passages, and more recently with\n" +
                "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\t\tIt is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using Ipsum is that it has a\n" +
                "more-or-less normal distribution of letters, as opposed to using 'Content here, content\n" +
                "here', making it look like readable English............\n" +
                "\t\tIt is a established fact that a reader will be of a page when looking at its\n" +
                "layout.\n" +
                "\t\tBye\n" +
                "df?!\n" +
                "\tdfgrr.");
        result.collectChildren(ComponentType.WORD).stream().map(Component::collect).forEach(System.out::println);
        System.out.println(result.collect());
    }
}
