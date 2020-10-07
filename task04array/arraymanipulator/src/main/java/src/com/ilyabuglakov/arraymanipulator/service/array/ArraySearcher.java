package src.com.ilyabuglakov.arraymanipulator.service.array;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;

public class ArraySearcher<T extends Comparable<T>> {

    public int indexOf(ArrayInterface<T> array, T element){
        int i = 0;
        for(T arrayElement : array){
            if(arrayElement.equals(element))
                return i;
            ++i;
        }
        return -1;
    }

    public T findMax(ArrayInterface<T> array){
        T max = array.get(0);
        for(T element: array){
            if(element.compareTo(max)>0)
                max = element;
        }
        return max;
    }

    public T findMin(ArrayInterface<T> array){ //TO DO
        T min = array.get(0);
        for(T element: array){
            if(element.compareTo(min)<0)
                min = element;
        }
        return min;
    }

}
