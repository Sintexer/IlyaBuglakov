package com.ilyabuglakov.raise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Question extends Entity {
    private String content;
    private Set<Answer> answers;
    private long testId;
}
