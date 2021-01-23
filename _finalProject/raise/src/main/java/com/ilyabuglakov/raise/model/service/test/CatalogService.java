package com.ilyabuglakov.raise.model.service.test;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CatalogService {

    public static int getMaxPage(int testAmount, int itemsPerPage){
        testAmount = Math.max(testAmount, 1);
        return (int)Math.ceil((double)(testAmount)/itemsPerPage);
    }

    public static int getPageNumber(String stringPageNumber){
        int page = 1;
        try {
            if (stringPageNumber != null) {
                int tempPageNumber = Integer.parseInt(stringPageNumber);
                if(tempPageNumber>0)
                    page = tempPageNumber;
            }
        }catch (NumberFormatException e){
            log.error(e);
        }
        return page;
    }
}
