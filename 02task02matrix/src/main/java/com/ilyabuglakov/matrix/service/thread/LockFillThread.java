package com.ilyabuglakov.matrix.service.thread;

import com.ilyabuglakov.matrix.model.Matrix;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This Thread locks general Lock each time, when it tries to acquire access to Matrix Cell
 */
public class LockFillThread extends Thread {

    Logger logger = Logger.getLogger(this.getClass());

    private final int content;
    private final ReentrantLock locker;
    private final CountDownLatch latch;

    public LockFillThread(int content, ReentrantLock locker, CountDownLatch latch) {
        this.content = content;
        this.locker = locker;
        this.latch = latch;
    }

    /**
     * Each Thread locks Locker and changing Cell in Matrix, then unlocking Locker.
     */
    @Override
    public void run() {
        final int matrixSize = Matrix.getInstance().size();
        for (int i = 0; i < matrixSize; ++i) {
            try {
                locker.lock();
                boolean changed = false;
                changed = Matrix.getInstance().getCell(i, i).getState().changeContent(content);
                if (changed) {
                    logger.info("Changed cell[" + i + "][" + i + "] with integer " + content);
                }
            } finally {
                locker.unlock();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("LockFillThread " + this.getId() + " was interrupted");
            }
        }
        latch.countDown();
    }
}
