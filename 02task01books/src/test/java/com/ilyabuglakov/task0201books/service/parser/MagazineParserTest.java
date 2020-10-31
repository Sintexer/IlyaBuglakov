package com.ilyabuglakov.task0201books.service.parser;

import com.ilyabuglakov.task0201books.bean.MagazineType;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class MagazineParserTest {

    @Test
    public void parse() {
        Magazine magazine = Magazine.of("name", 100, "pub", Year.of(2020), MagazineType.SCIENCE);
        MagazineParser parser = new MagazineParser();
        String content = "Magazine{name|100|pub|2020|science}";
        assertEquals(parser.parse(content), Optional.of(magazine));
    }


}