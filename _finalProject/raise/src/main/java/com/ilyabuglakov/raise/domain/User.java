package com.ilyabuglakov.raise.domain;

import com.ilyabuglakov.raise.domain.type.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class User extends Entity {

    private String email;
    private String name;
    private String surname;
    private String password;
    private Status status;
    private LocalDate registrationDate;

}
