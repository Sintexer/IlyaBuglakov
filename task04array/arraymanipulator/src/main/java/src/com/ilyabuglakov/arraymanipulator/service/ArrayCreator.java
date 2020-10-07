package src.com.ilyabuglakov.arraymanipulator.service;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;

public class ArrayCreator<T> {

    public Array<T> createArray(int size){
        return new Array<>(size);
    }

    public Array<Integer> createArray(Integer[] intArray){
        Array<Integer> array = new Array<>(1);
        array.setContent(intArray);
        return array;
    }

}
