package src.com.ilyabuglakov.arraymanipulator.service.array;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayCreator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayIntFileReader {

    public Array<Integer> readArray(String filepath) throws IOException {
        List<Integer> numbers;
        try(Stream<String> lines = Files.lines(Path.of(filepath))) {
                numbers = lines.flatMap(str -> Arrays.stream(str.split(" ")))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        ArrayCreator<Integer> creator = new ArrayCreator<>();
        return creator.createArray(numbers);
    }

}
