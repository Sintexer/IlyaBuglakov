package src.com.ilyabuglakov.arraymanipulator.controller;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

public class SortController<T> {
    Map<CommandName, UnaryOperator<Array<T>>> sorts = new HashMap<>();

    public void addSort(CommandName commandName, UnaryOperator<Array<T>> sort){
        sorts.put(commandName, sort);
    }

    public Set<CommandName> getCommandNamesList(){
        return sorts.keySet();
    }

    public UnaryOperator<Array<T>> getSort(CommandName commandName){
        return sorts.get(commandName);
    }
}
