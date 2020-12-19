package com.ilyabuglakov.raise.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestComment extends Entity {
    private String content;
    private LocalDateTime timestamp;
    private long testId;
    private long userId;
}
