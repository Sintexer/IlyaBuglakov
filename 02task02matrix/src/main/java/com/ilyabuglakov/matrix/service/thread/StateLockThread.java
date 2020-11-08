package com.ilyabuglakov.matrix.service.thread;

import com.ilyabuglakov.matrix.model.Matrix;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * StateLockThread locks Matrix Cell before changing its content. Requires special
 * CellState - EmptyLockState - to work properly.
 */
public class StateLockThread extends Thread {
    Logger logger = Logger.getLogger(this.getClass());

    private final int content;

    public StateLockThread(int content) {
        this.content = content;
    }

    /**
     * When started, trying to fill as many Matrix main diagonal cells as can.
     * Synchronisation via EmptyLockState.
     */
    @Override
    public void run() {
        final int matrixSize = Matrix.getInstance().size();
        for (int i = 0; i < matrixSize; ++i) {
            boolean changed = false;
            changed = Matrix.getInstance().getCell(i, i).getState().changeContent(content);
            if (changed) {
                logger.info("Changed cell[" + i + "][" + i + "] with integer " + content);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.error("StateLockThread " + this.getId() + " was interrupted");
            }
        }
    }
}
