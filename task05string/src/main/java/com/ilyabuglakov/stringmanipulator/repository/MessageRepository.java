package com.ilyabuglakov.stringmanipulator.repository;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.beans.LocaleName;
import com.ilyabuglakov.stringmanipulator.beans.MessageId;

import java.util.HashMap;
import java.util.Map;

public class MessageRepository {
    private Map<Class<? extends Enum<?>>, Map<Enum<?>, String>> classProperties = new HashMap<>();

    public MessageRepository() {
        addEnum(MessageId.class, "message");
        addEnum(CommandName.class, "command");
        addEnum(LocaleName.class, "locale");
    }

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
