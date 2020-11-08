package com.ilyabuglakov.matrix.controller.command;

import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.bean.MessageName;
import com.ilyabuglakov.matrix.controller.ApplicationController;
import com.ilyabuglakov.matrix.controller.CommandController;
import com.ilyabuglakov.matrix.model.Matrix;
import com.ilyabuglakov.matrix.model.cell.Cell;
import com.ilyabuglakov.matrix.model.cell.state.EmptyState;
import com.ilyabuglakov.matrix.service.PathService;
import com.ilyabuglakov.matrix.service.file.ThreadsFileReader;
import com.ilyabuglakov.matrix.service.thread.LockFillThread;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class FullLockFillCommand implements Command {

    private static final String FILE_NAME = "threads.txt";
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ThreadsFileReader reader = new ThreadsFileReader();
        List<Integer> contents = reader.readFile(PathService.getInstance().getResourcePath(FILE_NAME));
        if (contents.isEmpty()) {
            ApplicationController.getInstance().getView().showMessage(MessageName.FILE_IS_EMPTY);
            return;
        }
        for (int i = 0; i < Matrix.getInstance().size(); ++i) {
            Cell cell = Matrix.getInstance().getCell(i, i);
            cell.setState(new EmptyState(cell));
        }
        CountDownLatch latch = new CountDownLatch(contents.size());
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < contents.size(); ++i) {
            new LockFillThread(contents.get(i), lock, latch).start();
        }
        try {
            latch.await();
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            logger.error("Command was interrupted while waiting his threads", e);
        }
        CommandController.getInstance().executeCommand(CommandName.WRITE_TO_FILE);
    }
}
