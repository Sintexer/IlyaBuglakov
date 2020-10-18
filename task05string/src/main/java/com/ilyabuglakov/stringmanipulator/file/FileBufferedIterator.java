package com.ilyabuglakov.stringmanipulator.file;

import com.ilyabuglakov.stringmanipulator.exception.ReadException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileBufferedIterator implements AutoCloseable {

    private BufferedReader input;
    private final int bufferSize;
    private long charsToRead;

    public FileBufferedIterator(String path, int bufferSize) throws FileNotFoundException {
        File file = new File(path);
        input = new BufferedReader(new FileReader(file));
        this.bufferSize = bufferSize;
        charsToRead = file.length();
    }

    public boolean hasNext() {
        return charsToRead > 0;
    }

    public String next() throws IOException, ReadException {
        char[] buffer = new char[bufferSize];
        int charsReaden = input.read(buffer);
        if (charsReaden == -1) {
            throw new ReadException("Nothing to read");
        }
        charsToRead -= charsReaden;
        return new String(buffer);
    }

    public void close() throws IOException {
        if (input != null) {
            input.close();
        }
    }

}
