package src.com.ilyabuglakov.arraymanipulator.repository;

import java.util.Iterator;

public interface ArrayInterface<T> extends Iterable<T> {
    T get(int index);
}
