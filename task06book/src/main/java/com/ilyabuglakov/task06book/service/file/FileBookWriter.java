package com.ilyabuglakov.task06book.service.file;

import com.ilyabuglakov.task06book.model.book.Book;

import java.io.FileWriter;
import java.io.IOException;

public class FileBookWriter implements AutoCloseable {

    private FileWriter writer;

    public FileBookWriter(String path) throws IOException {
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
