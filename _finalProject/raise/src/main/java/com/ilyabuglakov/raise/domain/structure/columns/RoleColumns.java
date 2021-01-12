package com.ilyabuglakov.raise.domain.structure.columns;

public enum RoleColumns {
    NAME;

    String getName(){
        return name().toLowerCase();
    }
}
