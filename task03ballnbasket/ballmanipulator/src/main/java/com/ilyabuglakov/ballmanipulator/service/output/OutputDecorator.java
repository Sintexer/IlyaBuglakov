package main.java.com.ilyabuglakov.ballmanipulator.service.output;

import java.util.List;

public class OutputDecorator {

    public static void showEnumeratedList(List<?> list){
        for(int i = 0; i<list.size();++i){
            System.out.println((i+1)+ ". " + list.get(i));
        }
    }

}
