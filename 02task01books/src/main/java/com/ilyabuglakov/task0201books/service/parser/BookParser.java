package com.ilyabuglakov.task0201books.service.parser;

import com.ilyabuglakov.task0201books.exception.ParseException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.book.BookBuilder;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Example of String, containing book:
 * Book[voyna i mir|253|ilya,vasya bul, petya|Kamilfo|2020]
 * pattern:
 * getPrefix()[name|pages|authors|publishingHouse|year]
 */
public class BookParser {

    private final int MIN_BOOKSTRING_LENGTH = 12;

    public Optional<Book> parse(String bookString) {
        if (bookString.length() < MIN_BOOKSTRING_LENGTH) {
            return Optional.empty();
        }
        BookBuilder bookBuilder = new BookBuilder();
        try {
            bookString = bookString.substring(Book.getPrefix().length()+1, bookString.length() - 1);
            List<String> fields = new ArrayList<>(Arrays.asList(bookString.split("\\|")));
            if(fields.size()<5)
                return Optional.empty();
            bookBuilder.setName(fields.get(0));
            bookBuilder.setNumberOfPages(Integer.parseInt(fields.get(1)));
            bookBuilder.setPublishingHouse(fields.get(2));
            bookBuilder.setYearOfPublishing(Year.of(Integer.parseInt(fields.get(3))));
            bookBuilder.setAuthors(new HashSet<>(Arrays.asList(fields.get(4).split(","))));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(bookBuilder.build());
    }

}
