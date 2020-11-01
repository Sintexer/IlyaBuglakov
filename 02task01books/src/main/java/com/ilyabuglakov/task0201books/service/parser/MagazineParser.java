package com.ilyabuglakov.task0201books.service.parser;

import com.ilyabuglakov.task0201books.bean.MagazineType;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.model.magazine.MagazineBuilder;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * MagazineParser class used to create Magazine object from its String representation
 * Example of String, containing magazine:
 * Magazine[nauka i tech|16|defen|2020|science]
 * pattern:
 * getPrefix()[name|pages|publishingHouse|type]
 */
public class MagazineParser {

    private final int MIN_MAGAZINESTRING_LENGTH = 16;

    /**
     * This method will return Optional.empty() if information is wrong or corrupted and
     * information in String can't be used to create Book object.
     *
     * @param magazineString - String, containing information, used for Magazine object creation
     * @return Magazine, created from info in given String
     */
    public Optional<Magazine> parse(String magazineString) {
        if (magazineString.length() < MIN_MAGAZINESTRING_LENGTH) {
            ;
            return Optional.empty();
        }
        MagazineBuilder magazineBuilder = new MagazineBuilder();
        try {
            magazineString = magazineString.substring(Magazine.getPrefix().length() + 1, magazineString.length() - 1);
            List<String> fields = new ArrayList<>(Arrays.asList(magazineString.split("\\|")));
            if (fields.size() < 5)
                return Optional.empty();
            magazineBuilder.setName(fields.get(0));
            magazineBuilder.setNumberOfPages(Integer.parseInt(fields.get(1)));
            magazineBuilder.setPublishingHouse(fields.get(2));
            magazineBuilder.setYearOfPublishing(Year.of(Integer.parseInt(fields.get(3))));
            magazineBuilder.setType(MagazineType.valueOf(fields.get(4).toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(magazineBuilder.build());
    }

}
