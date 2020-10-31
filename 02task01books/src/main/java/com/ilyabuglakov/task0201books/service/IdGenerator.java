package com.ilyabuglakov.task0201books.service;

/**
 * IdGenerator will every time return new id for objects in Repository
 */
public class IdGenerator {
    private static long current = 0;
    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public long next() {
        return current++;
    }

    /**
     * Resets counter.
     */
    public void reset(){
        current = 0;
    }

    public static IdGenerator getInstance() {
        return instance;
    }
}
