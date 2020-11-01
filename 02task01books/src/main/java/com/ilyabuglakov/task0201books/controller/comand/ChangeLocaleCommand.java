package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.LocaleName;
import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.storage.LocaleMap;
import com.ilyabuglakov.task0201books.view.ConsoleView;
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
}
