package com.ilyabuglakov.matrix.service.thread;

import com.ilyabuglakov.matrix.model.Matrix;
import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Each Thread initialises with specific start index and threadsAmount.
 * Based on these numbers, threads fill Matrix main diagonal.
 */
public class PreparedThread extends Thread {
    Logger logger = Logger.getLogger(this.getClass());

    private final int content;
    private final Semaphore semaphore;
    private final int startIndex;
    private final int threadsAmount;

    /**
     * Creates new PreparedThread object, based on:
     *
     * @param content       - the integer to place in cells
     * @param semaphore     - synchronisation object
     * @param startIndex    - start index in matrix
     * @param threadsAmount - amount of threads
     */
    public PreparedThread(int content, Semaphore semaphore, int startIndex, int threadsAmount) {
        this.content = content;
        this.semaphore = semaphore;
        this.startIndex = startIndex;
        this.threadsAmount = threadsAmount;
    }

    /**
     * When started, thread tries to fill specific Cells in Matrix diagonal
     */
    @Override
    public void run() {
        final int matrixSize = Matrix.getInstance().size();
        for (int i = startIndex; i < matrixSize; i += threadsAmount) {
            try {
                semaphore.acquire();
                boolean changed = false;
                changed = Matrix.getInstance().getCell(i, i).getState().changeContent(content);
                if (changed) {
                    logger.info("Changed cell[" + i + "][" + i + "] with integer " + content);
                }
            } catch (InterruptedException e) {
                logger.error("PreparedThread " + this.getId() + " was interrupted");
            } finally {
                semaphore.release();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("PreparedThread " + this.getId() + " was interrupted");
            }
        }
    }
}
