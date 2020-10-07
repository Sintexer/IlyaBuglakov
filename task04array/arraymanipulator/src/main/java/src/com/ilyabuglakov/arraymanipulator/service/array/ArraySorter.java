package src.com.ilyabuglakov.arraymanipulator.service.array;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayModificator;

public class ArraySorter<T extends Comparable<T>> {

    ArrayCreator<T> creator = new ArrayCreator<>();
    ArrayModificator<T> modificator = new ArrayModificator<T>();

    public Array<T> sortInsertion(Array<T> array) {
        Array<T> sorted = creator.createArray(array);
        for (int i = 0; i < sorted.size(); ++i) {
            for (int j = i; j > 0; --j) {
                if (sorted.get(j).compareTo(sorted.get(j-1)) > 0)
                    break;
                modificator.swapValues(sorted, j, j-1);
            }
        }
        return sorted;
    }

    public Array<T> sortShell(Array<T> array) {
        Array<T> sorted = creator.createArray(array);
        int[] steps = new int[]{1750, 701, 301, 132, 57, 23, 10, 4, 1};
        for (int d : steps) {
            for (int i = d; i < sorted.size(); i += d) {
                for (int j = i; j >= d; j -= d)
                    if (sorted.get(j).compareTo(sorted.get(j - d)) < 0)
                        modificator.swapValues(sorted, j, j - d);
            }
        }
        return sorted;
    }

    public Array<T> sortMerge(Array<T> array) {
        return sortMergeRec(array);
    }

    private Array<T> sortMergeRec(Array<T> array) {
        if (array.size() < 2)
            return array;
        int middle = array.size() / 2;
        Array<T> left = creator.subarray(array, 0, middle);
        Array<T> right = creator.subarray(array, middle, array.size());
        left = sortMergeRec(left);
        right = sortMergeRec(right);
        return sortMergeMerge(left, right);
    }

    private Array<T> sortMergeMerge(Array<T> left, Array<T> right) {
        Array<T> result = creator.createArray(left.size() + right.size());
        int i = 0, j = 0, k = 0;
        while (k < result.size()) {
            if (i < left.size() && j < right.size()) {
                if (left.get(i).compareTo(right.get(j)) < 0) {
                    result.set(k++, left.get(i++));
                } else {
                    result.set(k++, right.get(j++));
                }
            } else if (j < right.size()) {
                result.set(k++, right.get(j++));
            } else {
                result.set(k++, left.get(i++));
            }

        }
        return result;
    }

}
