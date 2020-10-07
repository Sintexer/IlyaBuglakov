package src.com.ilyabuglakov.arraymanipulator.service;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;

public class ArrayModificator<T extends Number> {

    public void setValue(Array<T> array, int index, T value){
        array.set(index, value);
    }

}
