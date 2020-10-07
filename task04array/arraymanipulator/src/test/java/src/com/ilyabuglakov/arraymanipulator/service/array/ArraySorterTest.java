package src.com.ilyabuglakov.arraymanipulator.service.array;

import org.junit.Before;
import org.junit.Test;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayCreator;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArraySorterTest {

    private ArraySorter<Integer> sorter = new ArraySorter<>();
    private ArrayCreator<Integer> creator = new ArrayCreator<>();
    private Array<Integer> array;
    private Array<Integer> sortedArray;
    private final Integer[] defaultArray = new Integer[]{1,2,8, -2, 4,6,2,1,6,32,8};
    private final Integer[] defaultSortedArray = new Integer[]{-2, 1, 1, 2, 2, 4, 6, 6, 8, 8, 32};


    @Before
    public void beforeTest(){
        array = creator.createArray(defaultArray);
        sortedArray = creator.createArray(defaultSortedArray);
    }

    @Test
    public void sortMergeTest(){
        array = sorter.sortMerge(array);
        assertEquals(sortedArray, array);
    }

    @Test
    public void sortShellTest(){
        array = sorter.sortShell(array);
        assertEquals(sortedArray, array);
    }

    @Test
    public void sortInsertionTest(){
        array = sorter.sortInsertion(array);
        assertEquals(sortedArray, array);
    }
}