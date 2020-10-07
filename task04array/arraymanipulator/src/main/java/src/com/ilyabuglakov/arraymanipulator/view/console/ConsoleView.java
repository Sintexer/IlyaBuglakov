package src.com.ilyabuglakov.arraymanipulator.view.console;

import src.com.ilyabuglakov.arraymanipulator.controller.CommandName;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.IntValidator;
import src.com.ilyabuglakov.arraymanipulator.service.decorator.CollectionDecorator;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessagesRepository;
import src.com.ilyabuglakov.arraymanipulator.view.printer.ConsolePrinter;
import src.com.ilyabuglakov.arraymanipulator.view.printer.Printer;
import src.com.ilyabuglakov.arraymanipulator.view.reader.ConsoleReader;
import src.com.ilyabuglakov.arraymanipulator.view.reader.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ConsoleView {
    private Reader in = ConsoleReader.getInstance();
    private Printer out = ConsolePrinter.getInstance();

    private List<CommandName> options = new ArrayList<>();
    private MessagesRepository messagesRepository = MessagesRepository.getInstance();

    private Locale locale = Locale.US;
    private ResourceBundle rb = ResourceBundle.getBundle("property.text", locale);

    public ConsoleView(){
        options.add(CommandName.FILL_ARRAY);
        options.add(CommandName.SHOW_ARRAY);
        options.add(CommandName.INDEX_OF);
        options.add(CommandName.FIND_MAX);
        options.add(CommandName.FIND_MIN);
        options.add(CommandName.EXIT);

    }

    public CommandName getCommand(){
        out.print(rb.getString("console.input.choose"));
        int choice = readInt(1, CommandName.values().length);
        return options.get(choice-1);
    }


    public Integer[] readIntArray() {
        int size = readSize();
        String readIntArray = rb.getString("console.input.intarray");
        Integer[] array = new Integer[size];
        out.print(readIntArray);
        for (int i = 0; i < size; ++i) {
            array[i] = readInt();
        }
        return array;
    }

    public int readSize() {
        String readSize = rb.getString("console.input.arraysize");
        String wrongSize = rb.getString("console.input.wrongsize");
        out.print(readSize);
        int size = readInt();
        IntValidator validator = new IntValidator();
        while (!validator.isValidSize(size)) {
            out.print(wrongSize);
            size = readSize();
        }
        return size;
    }

    public int readInt(){
        return readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public  int readInt(int leftBound){
        return readInt(leftBound, Integer.MAX_VALUE);
    }

    public int readInt(int leftBound, int rightBound) {
        String wrongInt = rb.getString("console.input.wrongint");
        int val;
        while (true) {
            String input = in.getString();
            try {
                val = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                out.print(wrongInt);
                continue;
            }
            if(val >=leftBound && val<=rightBound) {
                break;
            }
            out.print(wrongInt);
        }
        return val;
    }

    public <T> void  show(T val){
        out.print(val.toString());
    }

    public String getMessage(MessageId id){
        return messagesRepository.getMessage(id);
    }

    public void showMessage(MessageId id){
        show(getMessage(id));
    }

    public String getMessage(CommandName id){
        return messagesRepository.getMessage(id);
    }

    public void showMessage(CommandName id){
        show(getMessage(id));
    }

    public List<String> getMessageList(CommandName... ids){
        return getMessageList(Arrays.asList(ids));
    }

    public List<String> getMessageList(Collection<? extends CommandName> ids){
        return messagesRepository.getMessageList(ids);
    }

    public void showMenu() {
        out.print(CollectionDecorator.toEnumeratedList(messagesRepository.getMessageList(options)));
    }

}
