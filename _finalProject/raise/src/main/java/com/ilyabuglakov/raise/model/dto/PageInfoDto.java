package com.ilyabuglakov.raise.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInfoDto {
    private int currentPage;
    private int itemsAmount;
    private int itemsPerPage;
    private int maxPage;
    private boolean isIllegal = false;

}
