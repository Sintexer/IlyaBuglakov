package src.com.ilyabuglakov.arraymanipulator.repository;

import java.util.Arrays;
import java.util.Iterator;

public class Array<T> implements ArrayInterface<T>, Iterable<T> {
    private T[] content;

    public Array(int size){
        content = (T[])new Object[size];
    }

    public T[] getContent(){
        return Arrays.copyOf(content, content.length);
    }

    public void setContent(T[] content){
        this.content = Arrays.copyOf(content, content.length);
    }

    public void set(int index, T value){
        content[index] = value;
    }

    public T get(int index){
        return content[index];
    }

    public int size(){
        return content.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array<?> array = (Array<?>) o;
        return Arrays.equals(content, array.content);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(content);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private T[] iteratorContent = getContent();
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index<iteratorContent.length;
            }

            @Override
            public T next() {
                return iteratorContent[index++];
            }
        };
    }
}
