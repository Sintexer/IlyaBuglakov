package com.ilyabuglakov.task0201books.service.file;

import com.ilyabuglakov.task0201books.model.book.Book;

import java.io.FileWriter;
import java.io.IOException;

public class FilePublicationWriter implements AutoCloseable {

    private FileWriter writer;

    public FilePublicationWriter(String path) throws IOException {
        writer = new FileWriter(path);
    }

    public void writeBook(Book book) throws IOException {
        writer.write(book.toString() + "\n");
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

}
