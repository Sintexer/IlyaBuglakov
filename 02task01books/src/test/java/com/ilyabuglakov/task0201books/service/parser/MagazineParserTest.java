package com.ilyabuglakov.task0201books.service.parser;

import com.ilyabuglakov.task0201books.bean.MagazineType;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class MagazineParserTest {

    @DataProvider(name = "validationData")
    public Object[][] createValidationData(){
        return  new Object[][]{
                {
                        "Magazine{name|100|pub|2020|science}",
                        Optional.of(Magazine.of("name", 100, "pub", Year.of(2020), MagazineType.SCIENCE))
                },
                {
                        "Book{|100||2020|}",
                        Optional.empty()
                },
                {
                        "Book{}",
                        Optional.empty()
                },
                {
                        "Magazine{}",
                        Optional.empty()
                },
                {
                        "Magazine{y|1|y|1|science}",
                        Optional.of(Magazine.of("y", 1, "y", Year.of(1), MagazineType.SCIENCE))
                }
        };
    }

    @Test(description = "Test of parsing String with magazine info",
            dataProvider = "validationData")
    public void testParse(String info, Optional<Magazine> parsedMagazine){
        MagazineParser parser = new MagazineParser();
        assertEquals(parsedMagazine, parser.parse(info));
    }


}