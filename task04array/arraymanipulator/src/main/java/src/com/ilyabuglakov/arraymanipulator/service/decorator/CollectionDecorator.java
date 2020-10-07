package src.com.ilyabuglakov.arraymanipulator.service.decorator;

import java.util.Collection;

public class CollectionDecorator {

    public static String toEnumeratedList(Collection<?> collection){
        StringBuilder enumerated = new StringBuilder("");
        int i =1;
        for(Object element: collection){
            enumerated.append(i++).append(". ").append(element). append(".\n");
        }
        return enumerated.toString();
    }

}
