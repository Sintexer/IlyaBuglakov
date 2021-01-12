package com.ilyabuglakov.raise.domain.structure.columns;

public enum RolePermissionsColumns {
    ROLE_ID,
    PERMISSION;

    String getName(){
        return name().toLowerCase();
    }
}
