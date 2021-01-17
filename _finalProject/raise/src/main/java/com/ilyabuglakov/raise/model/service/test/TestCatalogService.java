package com.ilyabuglakov.raise.model.service.test;

public class TestCatalogService {

    public static int getMaxPage(int testAmount, int testsPerPage){
        return (int)Math.ceil(testAmount/testsPerPage);
    }

    public static int getPageNumber(String stringPageNumber){
        int page = 0;
        try {
            if (stringPageNumber != null) {
                int tempPageNumber = Integer.parseInt(stringPageNumber);
                if(tempPageNumber>0)
                    page = tempPageNumber-1;
            }
        }catch (NumberFormatException e){}
        return page;
    }
}
