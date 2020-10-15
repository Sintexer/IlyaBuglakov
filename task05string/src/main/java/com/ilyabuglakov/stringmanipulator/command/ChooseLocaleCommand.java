package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.LocaleName;
import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.repository.LocaleRepository;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This command allows user to choose application locale
 */
public class ChooseLocaleCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageId.CHOOSE_LOCALE);
        List<LocaleName> locales = new ArrayList<>(Arrays.asList(LocaleName.values()));

        view.showEnumeratedMessages(locales);
        int choice = controller.getView().getInt(1, locales.size());
        view.setLocale(LocaleRepository.getInstance().getLocaleForName(locales.get(choice-1)));
    }
}
