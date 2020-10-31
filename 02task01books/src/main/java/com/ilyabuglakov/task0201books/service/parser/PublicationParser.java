package com.ilyabuglakov.task0201books.service.parser;

import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.model.publication.Publication;

import java.util.Optional;

/**
 * This class will choose correct parses of given String and will return
 * object, created from info in that String
 */
public class PublicationParser {
    private BookParser bookParser = new BookParser();
    private MagazineParser magazineParser = new MagazineParser();

    public Optional<? extends Publication> parse(String info) {
        if(info.startsWith(Book.getPrefix()))
            return bookParser.parse(info);
        else if(info.startsWith(Magazine.getPrefix()))
            return magazineParser.parse(info);
        return Optional.empty();
    }

}
