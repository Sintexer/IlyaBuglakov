package com.ilyabuglakov.composite.dal.file;

import com.ilyabuglakov.composite.exception.FileReadException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTextReader implements AutoCloseable {

    private BufferedReader input;
    private final int bufferSize;
    private long charsToRead;

    public BufferedTextReader(String path, int bufferSize) throws FileNotFoundException {
        File file = new File(path);
        input = new BufferedReader(new FileReader(file));
        this.bufferSize = bufferSize;
        charsToRead = file.length();
    }

    public boolean hasNext() {
        return charsToRead > 0;
    }

    public String next() throws IOException, FileReadException {
        char[] buffer = new char[bufferSize];
        int charsReaden = input.read(buffer);
        if (charsReaden == -1) {
            throw new FileReadException("Nothing to read");
        }
        charsToRead -= charsReaden;
        return new String(buffer);
    }

    @Override
    public void close() throws IOException {
        if (input != null) {
            input.close();
        }
    }

}
