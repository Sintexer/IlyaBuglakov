package com.ilyabuglakov.raise.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Base class for all database entities
 */
@Data
@EqualsAndHashCode
public class Entity {

    private Integer id;

}
