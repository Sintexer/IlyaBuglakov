package com.ilyabuglakov.task06book.service.file;

import com.ilyabuglakov.task06book.exception.BookParseException;
import com.ilyabuglakov.task06book.model.book.Book;
import com.ilyabuglakov.task06book.service.BookParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class FileBookReader implements AutoCloseable {

    Scanner reader;
    BookParser parser = new BookParser();

    public FileBookReader(String path) throws FileNotFoundException {
        reader = new Scanner(new FileReader(new File(path)));
    }

    public boolean hasNext(){
        return reader.hasNextLine();
    }

    public Book readBook() throws BookParseException {
        return parser.parse(reader.nextLine());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
