package com.ilyabuglakov.task0201books.service.file;

import com.ilyabuglakov.task0201books.model.publication.Publication;
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

    /**
     * Returns true, if there is something left in the file to read.
     *
     * @return true, if can read next object.
     */
    public boolean hasNext() {
        return reader.hasNextLine();
    }

    /**
     * Can return any Publication subclass as Publication reference.
     * Will return Optional.empty(), if line in the file contains wrong
     * parameters and object can be created from provided info.
     *
     * @return Optional of next Publication in the file.
     */
    public Optional<? extends Publication> next() {
        return parser.parse(reader.nextLine());
    }

    /**
     * Closes file.
     *
     * @throws IOException if file cannot be closed.
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
