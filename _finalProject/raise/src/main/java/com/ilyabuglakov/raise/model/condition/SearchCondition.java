package com.ilyabuglakov.raise.model.condition;

import lombok.Data;

@Data
public class SearchCondition {
    String key;
    String value;
    CompareType compareType;

    public SearchCondition(String key, String value, CompareType compareType) {
        this.key = key;
        this.value = value;
        this.compareType = compareType;
    }
}
