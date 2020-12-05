package src.com.ilyabuglakov.arraymanipulator.service.juggedarray;

import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayCreator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class JuggedArrayIntReader {

    public JuggedArray<Integer> readJuggedArray(String path) throws IOException {
        JuggedArrayCreator<Integer> juggedArrayCreator = new JuggedArrayCreator<>();
        ArrayCreator<Integer> arrayCreator = new ArrayCreator<>();
        JuggedArray<Integer> matrix;
        try(Stream<String> lines = Files.lines(Path.of(path))){
            Integer[][] arrays = lines.map(string -> string.split(" "))
                    .map(array -> Arrays.stream(array)
                            .map(Integer::parseInt)
                            .toArray(Integer[]::new))
                    .toArray(Integer[][]::new);
            matrix = juggedArrayCreator.createJuggedArray(arrays);
        }
        return matrix;
    }

}
