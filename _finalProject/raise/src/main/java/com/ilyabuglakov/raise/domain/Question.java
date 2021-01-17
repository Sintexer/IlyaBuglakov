package com.ilyabuglakov.raise.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Question extends Entity {
    private String name;
    private String content;
    private Set<Answer> answers;
    private Test test;

    public String toString(){
        return "Question " + content;
    }
}
