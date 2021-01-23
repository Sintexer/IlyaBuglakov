package com.ilyabuglakov.raise.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
    private Integer id;
    private Set<QuestionDto> questions;
}
