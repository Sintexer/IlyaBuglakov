package com.ilyabuglakov.raise.model.dto;

import com.ilyabuglakov.raise.domain.TestCategory;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSearchParametersDto {
    private String testName;
    private TestCategory category;
    private int page;
    private int limit;
    private TestStatus status;

    public boolean hasSearchParameters() {
        return category != null || testName!=null && !testName.isEmpty() ;
    }
}
