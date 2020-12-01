package com.ilyabuglakov.xmltask;

import com.ilyabuglakov.xmltask.controller.ApplicationController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

    public static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        logger.info("Application started");
        ApplicationController.getInstance().start();
    }
}
