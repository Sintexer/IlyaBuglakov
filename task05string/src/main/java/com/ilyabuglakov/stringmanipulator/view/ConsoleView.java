package com.ilyabuglakov.stringmanipulator.view;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.beans.LocaleName;
import com.ilyabuglakov.stringmanipulator.controller.IOController;
import com.ilyabuglakov.stringmanipulator.controller.MessageController;
import com.ilyabuglakov.stringmanipulator.service.OutputDecorator;
import com.ilyabuglakov.stringmanipulator.view.menu.Menu;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ConsoleView {
    private static ConsoleView instance = new ConsoleView();
    private MessageController messageController = new MessageController();
    private IOController ioController = new IOController();
    private Menu menu = Menu.getInstance();

    private ConsoleView() {
    }

    public static ConsoleView getInstance() {
        return instance;
    }

    public void showMessage(Enum<?> id) {
        ioController.show(messageController.getMessage(id));
    }

    public void showMenu() {
        ioController.show(OutputDecorator.toEnumeratedString(
                getMessagesList(menu.getOptions())));
    }

    public <T> void show(T o) {
        ioController.show(o);
    }

    public int getInt() {
        return ioController.readInt();
    }

    public int getInt(int leftBound) {
        return ioController.readInt(leftBound);
    }

    public int getInt(int leftBound, int rightBound) {
        return ioController.readInt(leftBound, rightBound);
    }

    public CommandName getCommand() {
        showMenu();
        List<CommandName> options = menu.getOptions();
        int choice = ioController.readInt(1, options.size());
        return options.get(choice - 1);
    }

    public String getString() {
        return ioController.readString();
    }

    public List<String> getMessagesList(Collection<? extends Enum<?>> ids) {
        return messageController.getMessages(ids);
    }

    public void showEnumeratedMessages(Collection<? extends Enum<?>> ids) {
        show(OutputDecorator.toEnumeratedString(getMessagesList(ids)));
    }

    public void setLocale(Locale locale){
        messageController.setLocale(locale);
    }
}
