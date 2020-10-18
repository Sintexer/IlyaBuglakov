package com.ilyabuglakov.stringmanipulator.repository;

import com.ilyabuglakov.stringmanipulator.beans.LocaleName;

import java.util.EnumMap;
import java.util.Locale;

/**
 * This repository connects LocaleName enum with specific Locale objects.
 * Class implemented as singleton.
 */
public class LocaleRepository {
    private static LocaleRepository instance = new LocaleRepository();
    private EnumMap<LocaleName, Locale> locales = new EnumMap<>(LocaleName.class);

    private LocaleRepository() {
        Locale locale = new Locale("ru_RU");
        locales.put(LocaleName.RU_RU, locale);
        locales.put(LocaleName.EN_US, new Locale("en_US"));
    }

    /**
     * Returns instance of singleton class
     *
     * @return LocaleRepository instance
     */
    public static LocaleRepository getInstance() {
        return instance;
    }

    /**
     * Returns specific Locale entity, associated with LocaleName
     *
     * @param name - enum name of locale
     * @return Locale locale associated with specific LocaleName
     */
    public Locale getLocaleForName(LocaleName name) {
        return locales.get(name);
    }
}
