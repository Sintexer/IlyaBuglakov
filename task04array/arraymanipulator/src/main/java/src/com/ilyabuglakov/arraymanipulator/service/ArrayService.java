package src.com.ilyabuglakov.arraymanipulator.service;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;

import java.util.Comparator;

public class ArrayService<T> { //TODO

    public boolean isSorted(ArrayInterface<T> arrayInterface, Comparator<T> comparator) {
        T prev = arrayInterface.iterator().next();
        for (T element : arrayInterface) {
            if (prev != null) {
                if (comparator.compare(element, prev) < 0)
                    return false;
            }
            prev = element;
        }
        return true;
    }

    public Integer sum(Array<Integer> array){
        Integer sum = 0;
        for(Integer element: array)
            sum+=element;
        return sum;
    }


}
