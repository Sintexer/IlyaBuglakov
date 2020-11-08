package com.ilyabuglakov.matrix.service.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads the threads contents from init file and returns it in Integer List
 */
public class ThreadsFileReader {

    public List<Integer> readFile(String path) {
        List<Integer> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            while (scanner.hasNextInt())
                numbers.add(scanner.nextInt());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numbers;
    }

}
