package com.ilyabuglakov.raise.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class Test extends Entity {
    private String testName;
    private int difficulty;
    private Set<Question> questions;
}
