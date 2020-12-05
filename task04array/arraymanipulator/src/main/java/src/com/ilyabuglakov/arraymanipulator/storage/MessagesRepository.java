package src.com.ilyabuglakov.arraymanipulator.storage;

import src.com.ilyabuglakov.arraymanipulator.model.CommandName;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MessagesRepository {
    private static final String PROPERTY_PATH = "property.text";
    private static MessagesRepository repository = new MessagesRepository();
    private Map<MessageId, String> messages = new EnumMap<>(MessageId.class);
    private Map<CommandName, String> commandMessages = new EnumMap<>(CommandName.class);

    private ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_PATH, Locale.US);

    private MessagesRepository() {
        messages.put(MessageId.NO_ARRAY, "console.info.noarray");
        messages.put(MessageId.INPUT_INT, "console.input.int");
        messages.put(MessageId.INPUT_ARRAY_SIZE, "console.input.arraysize");
        messages.put(MessageId.FILL_ARRAY, "menu.command.fillarray.capture");
        messages.put(MessageId.INPUT_ARRAY, "console.input.intarray");
        messages.put(MessageId.INT_RANDOM_BOUND, "console.input.int.randombound");
        messages.put(MessageId.SWITCH_TYPE_CAPTURE, "menu.command.switchtype.capture");
        messages.put(MessageId.SORT_ARRAY, "menu.command.sortarray.capture");
        messages.put(MessageId.ARRAY_IS_NOT_SORTED, "console.info.array.notsorted");
        messages.put(MessageId.FILL_JUGGED_ARRAY, "menu.command.filljuggedarray.caption");
        messages.put(MessageId.CANT_TRANSPOSE, "console.info.canttranspose");
        messages.put(MessageId.JUGGED_SORT, "menu.command.juggedsort.capture");
        messages.put(MessageId.WRONG_PATH, "console.info.array.wrongpath");


        commandMessages.put(CommandName.FILL_ARRAY, "menu.command.fillarray");
        commandMessages.put(CommandName.INPUT_ARRAY, "menu.command.fillarray.inputarray");
        commandMessages.put(CommandName.RANDOM_ARRAY, "menu.command.fillarray.random");
        commandMessages.put(CommandName.SHOW_ARRAY, "menu.command.showarray");
        commandMessages.put(CommandName.INDEX_OF, "menu.command.indexof");
        commandMessages.put(CommandName.FIND_MAX, "menu.command.findmax");
        commandMessages.put(CommandName.FIND_MIN, "menu.command.findmin");
        commandMessages.put(CommandName.SWITCH_TYPE, "menu.command.switchtype");
        commandMessages.put(CommandName.SWITCH_TO_ARRAY, "menu.command.switchtype.array");
        commandMessages.put(CommandName.SWITCH_TO_JUGGED_ARRAY, "menu.command.switchtype.juggedarray");
        commandMessages.put(CommandName.SORT_ARRAY, "menu.command.sortarray");
        commandMessages.put(CommandName.SORT_ARRAY_SHELL, "menu.command.sortarray.shell");
        commandMessages.put(CommandName.SORT_ARRAY_MERGE, "menu.command.sortarray.merge");
        commandMessages.put(CommandName.SORT_ARRAY_INSERTION, "menu.command.sortarray.insertion");
        commandMessages.put(CommandName.BINARY_SEARCH, "menu.command.binarysearch");
        commandMessages.put(CommandName.FIND_FIB_NUMBERS, "menu.command.findfib");
        commandMessages.put(CommandName.FIND_SIMPLE_NUMBERS, "menu.command.findsimplenumbers");
        commandMessages.put(CommandName.FIND_THREE_DIGITS_UNIQUE, "menu.command.findthreedigit");
        commandMessages.put(CommandName.FILL_JUGGED_ARRAY, "menu.command.filljuggedarray");
        commandMessages.put(CommandName.INPUT_JUGGED_ARRAY, "menu.command.fillarray.inputarray");
        commandMessages.put(CommandName.RANDOM_JUGGED_ARRAY, "menu.command.fillarray.random");
        commandMessages.put(CommandName.IS_SQUARE_MATRIX, "menu.command.issquare");
        commandMessages.put(CommandName.ADD_TO_EACH, "menu.command.addtoeach");
        commandMessages.put(CommandName.SUBTRACT_FROM_EACH, "menu.command.subtracteach");
        commandMessages.put(CommandName.MULTIPLY_EACH, "menu.command.multiplyeach");
        commandMessages.put(CommandName.COMPARE_DIMENSIONS, "menu.command.comparedimensions");
        commandMessages.put(CommandName.TRANSPOSE, "menu.command.transpose");
        commandMessages.put(CommandName.JUGGED_SORT, "menu.command.juggedsort");
        commandMessages.put(CommandName.JUGGED_SORT_BY_SUM, "menu.command.juggedsort.bysum");
        commandMessages.put(CommandName.JUGGED_SORT_BY_MAX, "menu.command.juggedsort.bymax");
        commandMessages.put(CommandName.JUGGED_SORT_BY_MIN, "menu.command.juggedsort.bymin");
        commandMessages.put(CommandName.FILE_ARRAY, "menu.command.readfile");
        commandMessages.put(CommandName.FILE_JUGGED_ARRAY, "menu.command.readfile");
        commandMessages.put(CommandName.EXIT, "menu.command.exit");
    }

    public void setLocale(Locale locale) {
        rb = ResourceBundle.getBundle(PROPERTY_PATH, locale);
    }

    public static MessagesRepository getInstance() {
        return repository;
    }

    public String getMessage(MessageId id) {
        return rb.getString(messages.get(id));
    }

    public String getMessage(CommandName id) {
        return rb.getString(commandMessages.get(id));
    }

    public List<String> getCommandList(Collection<? extends CommandName> commands) {
        List<String> messageList = new ArrayList<>(commands.size());
        for (var command : commands)
            messageList.add(getMessage(command));
        return messageList;
    }

    public List<String> getMessageList(Collection<? extends MessageId> messages) {
        List<String> messageList = new ArrayList<>(messages.size());
        for (var message : messages)
            messageList.add(getMessage(message));
        return messageList;
    }

}
