package com.ilyabuglakov.matrix.service.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Arbitration handles the access to matrix indexes
 */
public class Arbitration {

    private Queue<Integer> indexes;
    private final Semaphore semaphore = new Semaphore(1);
    private final CountDownLatch latch;

    /**
     * @param size  - matrix size
     * @param latch - latch to decrease after each index given to threads
     */
    public Arbitration(int size, CountDownLatch latch) {
        indexes = IntStream.range(0, size).boxed().collect(Collectors.toCollection(LinkedList::new));
        this.latch = latch;
    }

    /**
     * Returns the index in matrix for Thread to work with
     *
     * @return index in matrix
     */
    public Integer get() {
        Integer index = -1;
        try {
            semaphore.acquire();
            if (indexes.isEmpty())
                index = -1;
            else {
                index = indexes.poll();
                latch.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return index;
    }

}
