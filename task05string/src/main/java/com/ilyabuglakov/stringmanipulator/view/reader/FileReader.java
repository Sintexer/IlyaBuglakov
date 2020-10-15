package com.ilyabuglakov.stringmanipulator.view.reader;

import com.ilyabuglakov.stringmanipulator.exception.ReadException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public String readString(String path) throws IOException, ReadException {
        String result;
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            result = lines.collect(Collectors.joining("\n"));
            if(result.isEmpty())
                throw new ReadException("File is empty");
        }
        return result;
    }

}
