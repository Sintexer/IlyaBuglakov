package com.ilyabuglakov.matrix.controller.command;

import com.ilyabuglakov.matrix.bean.LocaleName;
import com.ilyabuglakov.matrix.bean.MessageName;
import com.ilyabuglakov.matrix.controller.ApplicationController;
import com.ilyabuglakov.matrix.storage.LocaleMap;
import com.ilyabuglakov.matrix.view.ConsoleView;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This command used to choose and change application's locale
 */
public class ChangeLocaleCommand implements Command {

    Logger logger = Logger.getLogger(this.getClass());

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
        logger.info("Changed to locale " + options.get(choice));
    }

    @Override
    public boolean applicableToEmptyMatrix() {
        return true;
    }
}
