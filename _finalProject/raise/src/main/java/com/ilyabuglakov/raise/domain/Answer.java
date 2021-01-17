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
    private Question question;

    @Override
    public String toString() {
        return "Answer{" +
                "content='" + content + '\'' +
                ", correct=" + correct +
                '}';
    }
}
