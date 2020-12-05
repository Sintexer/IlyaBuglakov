package src.com.ilyabuglakov.arraymanipulator.service;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayModificator;

public class ArrayModificatorTest {
    
    private ArrayCreator creator = new ArrayCreator();
    private ArrayModificator modificator = new ArrayModificator();
    private Array<Integer> array;

    @Before
    public void initArray(){
        array = creator.createArray(new Integer[]{1,2,3,4,5,6,7,8});
    }

    @Test
    public void setValue() {
        int index = 4;
        int value = -3454;
        array.set(index, value);
        assertEquals(value, (int)array.get(index));
    }

    @Test
    public void swapValues() {
        int firstIndex =1, secondIndex = 4;
        Integer first = array.get(firstIndex), second = array.get(secondIndex);
        modificator.swapValues(array, firstIndex, secondIndex);
        assertEquals(array.get(firstIndex), second);
        assertEquals(array.get(secondIndex), first);
    }
}