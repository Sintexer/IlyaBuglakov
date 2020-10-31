package com.ilyabuglakov.task0201books.model.magazine;

import com.ilyabuglakov.task0201books.bean.MagazineType;
import com.ilyabuglakov.task0201books.model.publication.Publication;

import java.time.Year;
import java.util.Objects;

public final class Magazine extends Publication {

    private final MagazineType type;

    private Magazine(String name, int numberOfPages, String publishingHouse, Year yearOfPublishing, MagazineType type) {
        super(name, numberOfPages, publishingHouse, yearOfPublishing);
        this.type = type;
    }

    public static Magazine of(String name, int numberOfPages, String publishingHouse,
                              Year yearOfPublishing, MagazineType type){
        return new Magazine(name, numberOfPages, publishingHouse, yearOfPublishing, type);
    }

    public static String getPrefix() {
        return "Magazine";
    }

    @Override
    public String toString() {
        return new StringBuilder(getPrefix())
                .append("{").append(name)
                .append("|").append(numberOfPages)
                .append("|").append(publishingHouse)
                .append("|").append(yearOfPublishing)
                .append("|").append(type.toString().toLowerCase())
                .append("}")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return type == magazine.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    public MagazineType getType() {
        return type;
    }
}
