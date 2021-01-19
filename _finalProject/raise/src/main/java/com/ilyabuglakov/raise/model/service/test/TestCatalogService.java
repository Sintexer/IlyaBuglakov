package com.ilyabuglakov.raise.model.service.test;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestCatalogService {

    public static int getMaxPage(int testAmount, int testsPerPage){
        return (int)Math.ceil(testAmount/testsPerPage);
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
