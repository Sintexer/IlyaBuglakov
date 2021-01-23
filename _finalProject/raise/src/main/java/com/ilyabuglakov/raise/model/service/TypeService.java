package com.ilyabuglakov.raise.model.service;


public class TypeService {

    private TypeService() {
    }

    public static String getLowered(Enum<?> enumeration) {
        return enumeration.name().replace(" ", "_").toLowerCase();
    }

}
