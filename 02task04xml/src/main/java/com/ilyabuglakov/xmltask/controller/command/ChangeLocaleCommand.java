package com.ilyabuglakov.xmltask.controller.command;


import com.ilyabuglakov.xmltask.controller.ApplicationController;
import com.ilyabuglakov.xmltask.model.LocaleName;
import com.ilyabuglakov.xmltask.model.MessageName;
import com.ilyabuglakov.xmltask.storage.LocaleMap;
import com.ilyabuglakov.xmltask.view.ConsoleView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This command used to choose and change application's locale
 */
public class ChangeLocaleCommand implements Command {

    private static Logger logger = LogManager.getLogger(ChangeLocaleCommand.class);

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageName.CHOOSE_LOCALE);
        List<LocaleName> options = new ArrayList<>(Arrays.asList(LocaleName.RU_RU,
                LocaleName.EN_US));
        view.showEnumeratedMessages(options);
        int choice = view.getInt(1, options.size());
        --choice;
        view.setLocale(LocaleMap.getInstance().getLocale(options.get(choice)));
        String localeName = options.get(choice).name();
        logger.info(() -> "Changed to locale " + localeName);
    }

}
