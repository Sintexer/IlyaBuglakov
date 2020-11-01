package com.ilyabuglakov.task0201books.service.parser;

import com.ilyabuglakov.task0201books.model.book.Book;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.testng.Assert.*;
public class BookParserTest {

    @DataProvider(name = "validationData")
    public Object[][] createValidationData(){
        return  new Object[][]{
                {
                        "Book{name|100|pub|2020|al,ben,nul}",
                        Optional.of(Book.of("name", 100, "pub", Year.of(2020),
                                new HashSet<>(Arrays.asList("al", "ben", "nul"))))
                },
                {
                        "Book{|100||2020|}",
                        Optional.empty()
                },
                {
                        "Book{}",
                        Optional.empty()
                },
                {
                        "Book{y|1|y|1|y}",
                        Optional.of(Book.of("y", 1, "y", Year.of(1), new HashSet<>(Arrays.asList("y"))))
                }
        };
    }

    @Test(description = "Test of parsing String with book info",
            dataProvider = "validationData")
    public void testParse(String info, Optional<Book> parsedBook){
        BookParser parser = new BookParser();
        assertEquals(parsedBook, parser.parse(info));
    }


}