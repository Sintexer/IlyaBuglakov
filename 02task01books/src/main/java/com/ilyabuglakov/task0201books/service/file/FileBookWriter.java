package com.ilyabuglakov.task0201books.service.file;

import com.ilyabuglakov.task0201books.model.book.Book;

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
