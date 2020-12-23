package com.ilyabuglakov.raise.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserTestResult extends Entity {
    private long userId;
    private long testId;
    private int result;
}
