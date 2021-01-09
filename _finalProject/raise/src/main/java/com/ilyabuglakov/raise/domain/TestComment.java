package com.ilyabuglakov.raise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

//TODO add  objects
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TestComment extends Entity {
    private String content;
    private LocalDateTime timestamp;
    private Test test;
    private User user;
}
