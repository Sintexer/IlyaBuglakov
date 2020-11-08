package com.ilyabuglakov.matrix.storage;

import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.bean.LocaleName;
import com.ilyabuglakov.matrix.bean.MessageName;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores property Strings, associated with fields of certain enums.
 */
public class PropertyStorage {
    private Map<Class<? extends Enum<?>>, Map<Enum<?>, String>> classProperties = new HashMap<>();

    public PropertyStorage() {
        addEnum(MessageName.class, "message");
        addEnum(CommandName.class, "command");
        addEnum(LocaleName.class, "locale");
    }

    /**
     * Adds another enum to map, qnd fills it with property Strings,
     * associated with enum
     *
     * @param enumeration     - enum to add
     * @param propertySubName - substring, that precede property String
     * @param <E>             - class of enum
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

    public String getPropertyString(Enum<?> id) {
        return classProperties.get(id.getClass()).get(id);
    }
}
