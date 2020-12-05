package src.com.ilyabuglakov.arraymanipulator.service.array;

import org.junit.Before;
import org.junit.Test;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;

import static org.junit.Assert.*;

public class ArraySearcherTest {

    private ArrayCreator<Integer> creator = new ArrayCreator<>();
    private ArraySearcher<Integer> searcher = new ArraySearcher<>();
    private Array<Integer> array;

    @Before
    public void initArray(){
        array = creator.createArray(new Integer[]{1,2,3,4,5,6,7,8});
    }

    @Test
    public void binarySearch() {
        int index = 4;
        int element  = 5;
        assertEquals(index, searcher.binarySearch(array, element));
    }
}