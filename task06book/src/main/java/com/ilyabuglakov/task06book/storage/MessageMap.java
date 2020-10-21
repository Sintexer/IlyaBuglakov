package com.ilyabuglakov.task06book.storage;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.bean.LocaleName;
import com.ilyabuglakov.task06book.bean.MessageName;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores property Strings, associated with fields of certain enums.
 */
public class MessageMap {
    private Map<Class<? extends Enum<?>>, Map<Enum<?>, String>> classProperties = new HashMap<>();

    public MessageMap() {
        addEnum(MessageName.class, "message");
        addEnum(CommandName.class, "command");
        addEnum(LocaleName.class, "locale");
    }

    /**
     * Adds another enum to map, qnd fills it with property Strings,
     * associated with enum
     * @param enumeration - enum to add
     * @param propertySubName - substring, that precede property String
     * @param <E> - class of enum
     */
    public <E extends Enum<E>> void addEnum(Class<E> enumeration, String propertySubName) {
        Map<Enum<?>, String> properties = new HashMap<>();
        for (E id : enumeration.getEnumConstants()) {
            String propertyName = propertySubName +
                    "." +
                    String.join("", id.name().toLowerCase().split("_"));
            properties.put(id, propertyName);
        }
        classProperties.put(enumeration, properties);
    }

    public String getPropertyName(Enum<?> id) {
        return classProperties.get(id.getClass()).get(id);
    }
}
