package com.ilyabuglakov.raise.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Answer extends Entity {
    private String content;
    private boolean correct;
    private long questionId;
}
