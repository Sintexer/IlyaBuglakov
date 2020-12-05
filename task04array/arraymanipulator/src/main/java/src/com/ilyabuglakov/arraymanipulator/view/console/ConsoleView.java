package src.com.ilyabuglakov.arraymanipulator.view.console;

import src.com.ilyabuglakov.arraymanipulator.model.CommandName;
import src.com.ilyabuglakov.arraymanipulator.model.ArrayType;
import src.com.ilyabuglakov.arraymanipulator.service.input.IntValidator;
import src.com.ilyabuglakov.arraymanipulator.service.decorator.CollectionDecorator;
import src.com.ilyabuglakov.arraymanipulator.storage.OptionsRepository;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;
import src.com.ilyabuglakov.arraymanipulator.storage.MessagesRepository;
import src.com.ilyabuglakov.arraymanipulator.service.console.printer.ConsolePrinter;
import src.com.ilyabuglakov.arraymanipulator.service.console.printer.Printer;
import src.com.ilyabuglakov.arraymanipulator.service.console.reader.ConsoleReader;
import src.com.ilyabuglakov.arraymanipulator.service.console.reader.Reader;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleView {
    private Reader in = ConsoleReader.getInstance();
    private Printer out = ConsolePrinter.getInstance();

    private OptionsRepository optionsRepository = new OptionsRepository();
    private MessagesRepository messagesRepository = MessagesRepository.getInstance();
    private ArrayType currentType = ArrayType.ARRAY;

    private Locale locale = Locale.US;
    private ResourceBundle rb = ResourceBundle.getBundle("property.text", locale);

    public ConsoleView() {
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.FILL_ARRAY);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.SHOW_ARRAY);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.SWITCH_TYPE);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.SORT_ARRAY);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.INDEX_OF);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.FIND_MAX);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.FIND_MIN);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.BINARY_SEARCH);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.FIND_SIMPLE_NUMBERS);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.FIND_FIB_NUMBERS);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.FIND_THREE_DIGITS_UNIQUE);
        optionsRepository.addOption(ArrayType.ARRAY, CommandName.EXIT);


        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.FILL_JUGGED_ARRAY);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.SHOW_ARRAY);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.SWITCH_TYPE);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.IS_SQUARE_MATRIX);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.ADD_TO_EACH);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.SUBTRACT_FROM_EACH);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.MULTIPLY_EACH);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.COMPARE_DIMENSIONS);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.TRANSPOSE);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.JUGGED_SORT);
        optionsRepository.addOption(ArrayType.JUGGED_ARRAY, CommandName.EXIT);
    }

    public CommandName getCommand() {
        out.print(rb.getString("console.input.choose"));
        int choice = readInt(1, CommandName.values().length);
        return getOptions(currentType).get(choice - 1);
    }

    private List<CommandName> getOptions(ArrayType type) {
        return optionsRepository.getOptionsForType(type);
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

    public int readInt() {
        return readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int readInt(int leftBound) {
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
            if (val >= leftBound && val <= rightBound) {
                break;
            }
            out.print(wrongInt);
        }
        return val;
    }

    public Integer[] readIntArray() {
        showMessage(MessageId.INPUT_ARRAY_SIZE);
        int size = readInt(1);
        Integer[] intArray = new Integer[size];
        showMessage(MessageId.INPUT_ARRAY);
        for (int i = 0; i < size; ++i) {
            intArray[i] = readInt();
        }
        return intArray;
    }

    public Integer[][] readIntMatrix() {
        showMessage(MessageId.INPUT_ARRAY_SIZE);
        int size = readInt(1);
        Integer[][] intArray = new Integer[size][];
        for (int i = 0; i < size; ++i) {
            intArray[i] = readIntArray();
        }
        return intArray;
    }

    public <T> void show(T val) {
        out.print(val.toString());
    }

    public String getMessage(MessageId id) {
        return messagesRepository.getMessage(id);
    }

    public void showMessage(MessageId id) {
        show(getMessage(id));
    }

    public String getMessage(CommandName id) {
        return messagesRepository.getMessage(id);
    }

    public void showMessage(CommandName id) {
        show(getMessage(id));
    }

    public List<String> getMessageList(CommandName... ids) {
        return getMessageList(Arrays.asList(ids));
    }

    public List<String> getMessageList(Collection<? extends CommandName> ids) {
        return messagesRepository.getCommandList(ids);
    }

    public void setCurrentType(ArrayType currentType) {
        this.currentType = currentType;
    }

    public void showMenu() {
        List<CommandName> options = optionsRepository.getOptionsForType(currentType);
        out.print(CollectionDecorator.toEnumeratedList(messagesRepository.getCommandList(options)));
    }

}
