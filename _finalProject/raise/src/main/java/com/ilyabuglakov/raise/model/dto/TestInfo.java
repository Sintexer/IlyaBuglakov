package com.ilyabuglakov.raise.model.dto;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestInfo {
    private Integer id;
    private User author;
    private String testName;
    private int difficulty;
    private Set<Characteristic> characteristics;
    private String questionNames;
    private int questionsAmount;
}
