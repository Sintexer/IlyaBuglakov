package src.com.ilyabuglakov.arraymanipulator.repository;

import java.util.Iterator;
import java.util.Objects;

public class JuggedArray<T> implements ArrayInterface<T> {

    private Array<Array<T>> arrays;

    public JuggedArray(int size) {
        arrays = new Array<>(size);
    }

    public JuggedArray(int rows, int cols) {
        arrays = new Array<>(rows);
        for (int i = 0; i < rows; ++i) {
            arrays.set(i, new Array<>(cols));
        }
    }

    public void setRow(int index, Array<T> row) {
        arrays.set(index, row);
    }

    public int size() {
        return arrays.size();
    }

    public Array<Array<T>> getContent() {
        return arrays;
    }

    public Array<T> get(int index) {
        return arrays.get(index);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (var arr : arrays)
            string.append(arr).append("\n");
        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JuggedArray<?> that = (JuggedArray<?>) o;
        return Objects.equals(arrays, that.arrays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrays);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<Array<T>> rows = arrays.iterator();
            Iterator<T> current = rows.next().iterator();

            @Override
            public boolean hasNext() {
                if (current.hasNext())
                    return true;
                return rows.hasNext();
            }

            @Override
            public T next() {
                if (!current.hasNext())
                    current = rows.next().iterator();
                return current.next();
            }
        };
    }
}
