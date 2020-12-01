package com.ilyabuglakov.xmltask.storage;


import com.ilyabuglakov.xmltask.model.LocaleName;

import java.util.EnumMap;
import java.util.Locale;

public class LocaleMap {
    private static LocaleMap instance = new LocaleMap();
    private static EnumMap<LocaleName, Locale> locales = new EnumMap<>(LocaleName.class);

    static {
        locales.put(LocaleName.RU_RU, new Locale("ru_RU"));
        locales.put(LocaleName.EN_US, new Locale("en_US"));
    }

    private LocaleMap() {
    }

    /**
     * Returns instance of singleton class
     *
     * @return LocaleMap instance
     */
    public static LocaleMap getInstance() {
        return instance;
    }

    /**
     * Returns Locale associated with LocaleName
     *
     * @param localeName enum name of loclale
     * @return Locale, associated with enum name
     */
    public Locale getLocale(LocaleName localeName) {
        return locales.get(localeName);
    }
}
