package com.ilyabuglakov.task06book.service;

import com.ilyabuglakov.task06book.exception.BookParseException;
import com.ilyabuglakov.task06book.model.book.Book;
import com.ilyabuglakov.task06book.model.book.BookBuilder;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Example of String, containing book:
 * [voyna i mir|253|ilya,vasya bul, petya|Kamilfo|2020]
 * pattern:
 * [name|pages|authors|publishingHouse|year]
 */
public class BookParser {

    private final int MIN_BOOKSTRING_LENGTH = 6;

    public Book parse(String bookString) throws BookParseException {
        if(bookString.length()<MIN_BOOKSTRING_LENGTH){
            throw new BookParseException("Wrong Book String pattern");
        }
        BookBuilder bookBuilder = new BookBuilder();
        bookString = bookString.substring(1, bookString.length()-1);
        List<String> fields = new ArrayList<>(Arrays.asList(bookString.split("\\|")));
        bookBuilder.setName(fields.get(0));
        bookBuilder.setNumberOfPages(Integer.parseInt(fields.get(1)));
        bookBuilder.setAuthors(new HashSet<>(Arrays.asList(fields.get(2).split(","))));
        bookBuilder.setPublishingHouse(fields.get(3));
        bookBuilder.setYearOfPublishing(Year.of(Integer.parseInt(fields.get(4))));
        return bookBuilder.build();
    }

}
