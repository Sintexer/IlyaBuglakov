package com.ilyabuglakov.matrix.controller.command;

import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.bean.MessageName;
import com.ilyabuglakov.matrix.controller.ApplicationController;
import com.ilyabuglakov.matrix.controller.CommandController;
import com.ilyabuglakov.matrix.model.Matrix;
import com.ilyabuglakov.matrix.model.cell.Cell;
import com.ilyabuglakov.matrix.model.cell.state.EmptyLockState;
import com.ilyabuglakov.matrix.service.PathService;
import com.ilyabuglakov.matrix.service.file.ThreadsFileReader;
import com.ilyabuglakov.matrix.service.thread.StateLockThread;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StateLockCommand implements Command {

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
            cell.setState(new EmptyLockState(cell));
        }
        List<StateLockThread> threads = new ArrayList<>();
        for (int i = 0; i < contents.size(); ++i) {
            StateLockThread thread = new StateLockThread(contents.get(i));
            threads.add(thread);
            thread.start();
        }
        try {
            for (StateLockThread thread : threads) {
                thread.join();
            }
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            logger.error("Command was interrupted while waiting his threads", e);
        }
        CommandController.getInstance().executeCommand(CommandName.WRITE_TO_FILE);
    }
}
