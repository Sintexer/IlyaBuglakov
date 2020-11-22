package com.ilyabuglakov.composite.bean.component.comparator;

import com.ilyabuglakov.composite.bean.component.Component;
import com.ilyabuglakov.composite.bean.component.ComponentType;

import java.util.Comparator;

public class ComponentStringComparator implements Comparator<Component<String>> {

    public static Comparator<Component<String>> comparingParagraphs(){
        return Comparator.comparing(component -> component.collectChildren(ComponentType.SENTENCE).size());
    }

    public static Comparator<Component<String>> comparingWords(){
        return Comparator.comparing(component -> component.collect().length());
    }

    public static Comparator<Component<String>> comparingLexeme(char symbol){
        return Comparator.comparing(component -> component.collect().chars()
            .filter(i -> i==(int)symbol)
            .count());
    }

    public static Comparator<Component<String>> comparingByAlphabet(){
        return Comparator.comparing(component -> component.collect().toLowerCase());
    }

    @Override
    public int compare(Component<String> o1, Component<String> o2) {
        return o1.collect().compareTo(o2.collect());
    }

}
