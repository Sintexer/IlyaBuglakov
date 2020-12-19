package com.ilyabuglakov.raise.domain;

import com.ilyabuglakov.raise.domain.type.Role;
import com.ilyabuglakov.raise.domain.type.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Entity {

    private String email;
    private String name;
    private String surname;
    private String password;
    private Role role;
    private Status status;
    private LocalDate registrationDate;

}
