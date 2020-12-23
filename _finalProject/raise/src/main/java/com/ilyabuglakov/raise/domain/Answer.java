package com.ilyabuglakov.raise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Answer extends Entity {
    private String content;
    private boolean correct;
    private long questionId;
}
