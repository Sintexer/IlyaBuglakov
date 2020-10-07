package src.com.ilyabuglakov.arraymanipulator.service;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;

public class ArrayModificator<T>  {

    public void setValue(Array<T> array, int index, T value){
        array.set(index, value);
    }

    public void swapValues(Array<T> array, int firstIndex, int secondIndex){
        T temp = array.get(firstIndex);
        array.set(firstIndex, array.get(secondIndex));
        array.set(secondIndex, temp);
    }

}
