package com.ilyabuglakov.task0201books.service.file;

import com.ilyabuglakov.task0201books.exception.ParseException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.service.parser.BookParser;
import com.ilyabuglakov.task0201books.service.parser.PublicationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Pattern of File with Books:
 * [Book.toString()]
 * [Book.toString()]
 * ...
 * [Book.toString()]
 * Each Book should be on its own line
 * Book.toString() pattern:
 * [name|numberOfPages|author,author,...,author|publishingHouse|yearOfPublishing]
 */
public class FilePublicationReader implements AutoCloseable {

    Scanner reader;
    PublicationParser parser = new PublicationParser();

    public FilePublicationReader(String path) throws FileNotFoundException {
        reader = new Scanner(new FileReader(new File(path)));
    }

    public boolean hasNext() {
        return reader.hasNextLine();
    }

    public Optional<? extends Publication> next() {
        return parser.parse(reader.nextLine());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
