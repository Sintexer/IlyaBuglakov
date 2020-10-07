package src.com.ilyabuglakov.arraymanipulator.repository;

import java.util.Iterator;

public class JuggedArray<T> implements ArrayInterface, Iterable {

    private Array<Array<T>> arrays;

    public JuggedArray(int size){
        arrays = new Array<>(size);
    }

    public JuggedArray(int rows, int cols){
        arrays = new Array<>(rows);
        for(int i = 0; i<rows; ++i){
            arrays.set(i, new Array<>(cols));
        }
    }

    public void setRow(int index, Array<T> row){
        arrays.set(index, row);
    }

    public int size(){
        return arrays.size();
    }

    @Override
    public String toString() {
        String string = "";
        for(var arr: arrays)
            string+=arr+"\n";
        return string;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
