package src.com.ilyabuglakov.arraymanipulator.view.menu;

import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.controller.CommandName;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandMenu {

    private Map<CommandName, Command> optionsMap = new LinkedHashMap<>();
    //private Map<CommandName, String> optionsMap = new LinkedHashMap<>();

//    public CommandMenu() {}
//
//    public void addOption(CommandName commandName, String propertyPath){
//        optionsMap.put(commandName, propertyPath);
//    }
//
//    public String getOptionsList(){
//        return CollectionDecorator.toEnumeratedList(optionsMap.values().stream().map());
//    }

}