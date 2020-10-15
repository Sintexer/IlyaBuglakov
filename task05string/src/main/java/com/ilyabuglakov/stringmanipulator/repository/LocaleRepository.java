package com.ilyabuglakov.stringmanipulator.repository;

import com.ilyabuglakov.stringmanipulator.beans.LocaleName;

import java.util.EnumMap;
import java.util.Locale;

public class LocaleRepository {
    private static LocaleRepository instance = new LocaleRepository();
    private EnumMap<LocaleName, Locale> locales = new EnumMap<>(LocaleName.class);

    private LocaleRepository(){
        Locale locale = new Locale("ru_RU");
        locales.put(LocaleName.RU_RU, locale);
        locales.put(LocaleName.EN_US, new Locale("en_US"));
    }

    public static LocaleRepository getInstance() {
        return instance;
    }

    public Locale getLocaleForName(LocaleName name){
        return locales.get(name);
    }
}
