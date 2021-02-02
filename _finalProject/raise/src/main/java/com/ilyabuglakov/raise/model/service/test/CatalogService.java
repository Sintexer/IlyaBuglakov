package com.ilyabuglakov.raise.model.service.test;

import com.ilyabuglakov.raise.model.dto.PageInfoDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CatalogService {

    public static int getMaxPage(int testAmount, int itemsPerPage) {
        testAmount = Math.max(testAmount, 1);
        return (int) Math.ceil((double) (testAmount) / itemsPerPage);
    }

    public static int getPageNumber(String stringPageNumber) {
        int page = 1;
        if (stringPageNumber != null && !stringPageNumber.isEmpty()) {
            try {
                page = Integer.parseInt(stringPageNumber);
            }catch (NumberFormatException e){
                return -1;
            }
        }
        return page;
    }

    public static PageInfoDto getPageInfo(String currentPage, int itemsAmount, int itemsPerPage) {
        try {
            return getPageInfo(getPageNumber(currentPage), itemsAmount, itemsPerPage);
        } catch (NumberFormatException e) {
            return PageInfoDto.builder().build();
        }
    }

    public static PageInfoDto getPageInfo(int currentPage, int itemsAmount, int itemsPerPage) {
        int maxPage = getMaxPage(itemsAmount, itemsPerPage);
        return PageInfoDto.builder()
                .currentPage(currentPage)
                .itemsAmount(itemsAmount)
                .itemsPerPage(itemsPerPage)
                .maxPage(maxPage)
                .build();
    }
}
