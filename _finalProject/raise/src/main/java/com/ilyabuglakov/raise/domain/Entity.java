package com.ilyabuglakov.raise.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


/**
 * Base class for all database entities
 */
@Data
@EqualsAndHashCode
public class Entity {

    private long id;

}
