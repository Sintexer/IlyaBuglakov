package src.com.ilyabuglakov.arraymanipulator.view;

import src.com.ilyabuglakov.arraymanipulator.controller.CommandName;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionsRepository {

    Map<ArrayType, List<CommandName>> optionsForType = new HashMap<>();

    public void addOption(ArrayType type, CommandName commandName){
        optionsForType.putIfAbsent(type, new ArrayList<>());
        optionsForType.get(type).add(commandName);
    }

    public List<CommandName> getOptionsForType(ArrayType type){
        return optionsForType.get(type);
    }

}
