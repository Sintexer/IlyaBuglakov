package com.ilyabuglakov.task0201books.service.validator;

import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.service.parser.BookParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.testng.Assert.*;

public class IntValidatorTest {

    @DataProvider(name = "validationData")
    public Object[][] createValidationData(){
        return  new Object[][]{
                {"123", true},
                {"0", true},
                {"s", false},
                {"122312122123", false},
                {"1", true},
                {String.valueOf(Integer.MAX_VALUE), true},
                {String.valueOf(Integer.MIN_VALUE), true},
                {"0.01010", false},
                {"__=-=+-", false},
                {"5.2", false},
                {"-122341241243242124", false},

        };
    }

    @Test(description = "Test of validation Integer in String",
            dataProvider = "validationData")
    public void testValidInt(String info, boolean expexted){
        assertEquals(IntValidator.validInt(info), expexted);
    }

}