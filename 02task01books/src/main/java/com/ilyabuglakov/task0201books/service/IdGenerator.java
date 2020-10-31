package com.ilyabuglakov.task0201books.service;

public class IdGenerator {
    private static long current = 0;
    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public long next() {
        return current++;
    }

    public static IdGenerator getInstance() {
        return instance;
    }
}
