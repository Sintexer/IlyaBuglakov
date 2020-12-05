package src.com.ilyabuglakov.arraymanipulator.service;

import org.junit.Before;
import org.junit.Test;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayService;

import java.util.Comparator;

import static org.junit.Assert.*;

public class ArrayServiceTest {
    private ArrayCreator<Integer> creator = new ArrayCreator<>();
    private ArrayService<Integer> service = new ArrayService<>();
    private Array<Integer> array;
    private Array<Integer> array2;

    @Before
    public void initArray(){
        array = creator.createArray(new Integer[]{1,2,3,4,5,6,7,8});
        array2 = creator.createArray(new Integer[]{9,2,3,4,9,2,3,4});
    }

    @Test
    public void isSortedTest() {
        assertTrue(service.isSorted(array, Comparator.naturalOrder()));
        assertFalse(service.isSorted(array2, Comparator.naturalOrder()));
    }
}