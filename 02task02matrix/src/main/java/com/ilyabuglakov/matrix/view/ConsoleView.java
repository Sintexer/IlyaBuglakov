package com.ilyabuglakov.matrix.view;

import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.controller.IOController;
import com.ilyabuglakov.matrix.controller.MessageController;
import com.ilyabuglakov.matrix.service.console.OutputDecorator;
import com.ilyabuglakov.matrix.view.menu.Menu;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * The application view class, contains IOController, to operate
 * input and output operations via simple high-level interfaces, also
 * contains MessageController, to easily get messages for current application
 * locale, and Menu, that represents the options of application.
 * Implemented as singleton.
 */
public class ConsoleView {
    private MessageController messageController = new MessageController();
    private IOController ioController = new IOController();
    private Menu menu = Menu.getInstance();

    private ConsoleView() {
    }

    private static class SingletonHolder {
        public static final ConsoleView HOLDER_INSTANCE = new ConsoleView();
    }

    /**
     * Returns instance of singleton class
     *
     * @return ConsoleView instance
     */
    public static ConsoleView getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    /**
     * Shows String, associated with Enum id in Message repository to
     * IOController output
     *
     * @param id - enum identification of message
     */
    public void showMessage(Enum<?> id) {
        ioController.show(messageController.getMessage(id));
    }

    /**
     * Shows enumerated menu options to IOController output
     */
    public void showMenu() {
        ioController.show(OutputDecorator.toEnumeratedString(
                getMessagesList(menu.getOptions())));
    }

    /**
     * Shows object to IOController output
     *
     * @param o - object to show
     */
    public <T> void show(T o) {
        ioController.show(o);
    }

    /**
     * @return int, inputted to IOController's input
     */
    public int getInt() {
        return ioController.readInt();
    }

    /**
     * You can specify the left bound of inputted int
     *
     * @return int, higher or equal leftBound, inputted to IOController's input
     */
    public int getInt(int leftBound) {
        return ioController.readInt(leftBound);
    }

    /**
     * You can specify the left and right bounds of inputted int
     *
     * @return int, higher or equal leftBound and lesser or equal rightBound,
     * inputted to IOController's input
     */
    public int getInt(int leftBound, int rightBound) {
        return ioController.readInt(leftBound, rightBound);
    }

    /**
     * @return String, inputted to IOController's input
     */
    public String getString() {
        return ioController.readString();
    }

    /**
     * Returns CommandName, that is associated with menu option,
     * chosen by the user.
     *
     * @return CommandName - chosen menu option
     */
    public CommandName getCommand() {
        showMenu();
        List<CommandName> options = menu.getOptions();
        int choice = ioController.readInt(1, options.size());
        return options.get(choice - 1);
    }

    /**
     * @param ids - identifications of messages enum
     * @return List of current application locale Strings,
     * associated with provided enum id's
     */
    public List<String> getMessagesList(Collection<? extends Enum<?>> ids) {
        return messageController.getMessages(ids);
    }

    /**
     * Shows provided collection as enumerated list to IOController's output
     *
     * @param ids - identifications of messages to show
     */
    public void showEnumeratedMessages(Collection<? extends Enum<?>> ids) {
        show(OutputDecorator.toEnumeratedString(getMessagesList(ids)));
    }

    /**
     * Sets the application current locale
     *
     * @param locale - new current locale
     */
    public void setLocale(Locale locale) {
        messageController.setLocale(locale);
    }
}
