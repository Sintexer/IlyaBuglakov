package com.ilyabuglakov.raise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserTestResult extends Entity {
    private User user;
    private Test test;
    private int result;
}
