package com.ilyabuglakov.task0201books.service.file;

import com.ilyabuglakov.task0201books.model.publication.Publication;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class used to write Publication subclasses to file.
 */
public class FilePublicationWriter implements AutoCloseable {

    private FileWriter writer;

    /**
     * Opens output file.
     *
     * @param path - path of the output file.
     * @throws IOException - if fine cant be accessed.
     */
    public FilePublicationWriter(String path) throws IOException {
        writer = new FileWriter(path);
    }

    /**
     * Writes String, created from provided publication, to text file.
     *
     * @param publication - publication object o write in file.
     * @throws IOException - if file can't be accessed.
     */
    public void write(Publication publication) throws IOException {
        writer.write(publication.toString() + "\n");
    }

    /**
     * Closes file.
     *
     * @throws IOException if file cannot be closed.
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }

}
