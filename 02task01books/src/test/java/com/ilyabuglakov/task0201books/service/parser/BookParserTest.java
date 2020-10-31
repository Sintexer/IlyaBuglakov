package com.ilyabuglakov.task0201books.service.parser;

import com.ilyabuglakov.task0201books.model.book.Book;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.testng.Assert.*;
public class BookParserTest {

    @Test
    public void testParse() {
        Book book = Book.of("name", 100, "pub", Year.of(2020), new HashSet<>(Arrays.asList("al", "ben", "nul")));
        BookParser parser = new BookParser();
        String content = "Book{name|100|pub|2020|al,ben,nul}";
        assertEquals(Optional.of(book), parser.parse(content));
    }

    @Test
    public void testEmptyParse() {
        Book book = Book.of("", 100, "", Year.of(2020), new HashSet<>());
        BookParser parser = new BookParser();
        String content = "Book{|100||2020|}";
        assertEquals(Optional.empty(), parser.parse(content));
    }
}