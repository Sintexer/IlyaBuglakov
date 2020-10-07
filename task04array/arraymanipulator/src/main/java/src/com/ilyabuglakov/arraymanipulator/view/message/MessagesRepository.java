package src.com.ilyabuglakov.arraymanipulator.view.message;

import src.com.ilyabuglakov.arraymanipulator.controller.CommandName;

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

    private MessagesRepository(){
        messages.put(MessageId.NO_ARRAY, "console.info.noarray");
        messages.put(MessageId.INPUT_INT, "console.input.int");
        messages.put(MessageId.INPUT_ARRAY_SIZE, "console.input.arraysize");
        messages.put(MessageId.FILL_ARRAY, "menu.command.fillarray.capture");
        messages.put(MessageId.INPUT_ARRAY, "console.input.intarray");
        messages.put(MessageId.INT_RANDOM_BOUND, "console.input.int.randombound");

        commandMessages.put(CommandName.FILL_ARRAY, "menu.command.fillarray");
        commandMessages.put(CommandName.INPUT_ARRAY, "menu.command.fillarray.inputarray");
        commandMessages.put(CommandName.RANDOM_ARRAY, "menu.command.fillarray.random");
        commandMessages.put(CommandName.SHOW_ARRAY, "menu.command.showarray");
        commandMessages.put(CommandName.INDEX_OF, "menu.command.indexof");
        commandMessages.put(CommandName.FIND_MAX, "menu.command.findmax");
        commandMessages.put(CommandName.FIND_MIN, "menu.command.findmin");
        commandMessages.put(CommandName.EXIT, "menu.command.exit");
    }

    public void setLocale(Locale locale){
        rb = ResourceBundle.getBundle(PROPERTY_PATH, locale);
    }

    public static MessagesRepository getInstance(){
        return repository;
    }

    public String getMessage(MessageId id){
        return rb.getString(messages.get(id));
    }

    public String getMessage(CommandName id){
        return rb.getString(commandMessages.get(id));
    }

    public List<String> getMessageList(Collection<? extends CommandName> commands){
        List<String> messageList = new ArrayList<>(commands.size());
        for(var command: commands)
            messageList.add(getMessage(command));
        return messageList;
    }

}
