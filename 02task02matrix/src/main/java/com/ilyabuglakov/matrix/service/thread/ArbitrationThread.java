package com.ilyabuglakov.matrix.service.thread;

import com.ilyabuglakov.matrix.model.Matrix;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * ArbitrationThread fills matrix main diagonal with the hep of Arbitration.
 * Arbitration gives the index in matrix for Thread to fill
 */
public class ArbitrationThread extends Thread {
    Logger logger = Logger.getLogger(this.getClass());

    private final int content;
    private final Arbitration arbitration;

    public ArbitrationThread(int content, Arbitration arbitration) {
        this.content = content;
        this.arbitration = arbitration;
    }

    /**
     * When started, filling as much diagonal cells,as can
     */
    @Override
    public void run() {
        //TODO return index to collection if error
        final int matrixSize = Matrix.getInstance().size();
        Integer index;
        do {
            index = arbitration.get();
            if (index != -1) {
                boolean changed = false;
                changed = Matrix.getInstance().getCell(index, index).getState().changeContent(content);
                if (changed) {
                    logger.info("Changed cell[" + index + "][" + index + "] with integer " + content);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("ArbitrationThread " + this.getId() + " was interrupted");
                }
            }
        } while (index != -1);
    }
}
