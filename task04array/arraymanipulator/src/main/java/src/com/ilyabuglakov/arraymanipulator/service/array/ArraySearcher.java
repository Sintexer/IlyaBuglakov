package src.com.ilyabuglakov.arraymanipulator.service.array;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.NumberService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public T findMin(ArrayInterface<T> array){ //TODO
        T min = array.get(0);
        for(T element: array){
            if(element.compareTo(min)<0)
                min = element;
        }
        return min;
    }

    public <E extends Integer> Array<E> findByPredicate(Array<E> array, Predicate<E> predicate){
        List<E> finded = new ArrayList<>();
        NumberService numberService = new NumberService();
        for(E element : array){
            if(predicate.test(element))
                finded.add(element);
        }
        return new ArrayCreator<E>().createArray(finded);
    }

    public int binarySearch(Array<T> array, T element){
        int current = array.size()/2;
        int prev=-1;
        while (current!=prev && current>0 && current<array.size()){
            T currentElement = array.get(current);
            if(currentElement.compareTo(element) == 0)
                return current;
            prev = current;
            if(currentElement.compareTo(element) <0)
                current+=current/2;
            else if(currentElement.compareTo(element)>0)
                current-=current/2;
        }
        return -1;
    }

}
