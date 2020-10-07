package src.com.ilyabuglakov.arraymanipulator.service;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;

import java.util.Arrays;
import java.util.List;

public class ArrayCreator<T> {

    public Array<T> createArray(int size) {
        return new Array<>(size);
    }

    public Array<T> createArray(Array<T> array) {
        return createArray(array.getContent());
    }

    public Array<T> createArray(T[] array) {
        Array<T> newArray = new Array<>(1);
        newArray.setContent(array);
        return newArray;
    }

    public Array<T> createArray(List<T> list) {
        Array<T> newArray = new Array<>(list.size());
        int i = 0;
        for (T element : list) {
            newArray.set(i++, element);
        }
        return newArray;
    }

    public Array<T> subarray(Array<T> array, int from, int to) {
        T[] content = array.getContent();
        return createArray(Arrays.copyOfRange(content, from, to));
    }

}
