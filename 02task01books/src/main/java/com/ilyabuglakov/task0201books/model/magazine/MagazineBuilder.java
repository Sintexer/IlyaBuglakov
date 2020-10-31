package com.ilyabuglakov.task0201books.model.magazine;

import com.ilyabuglakov.task0201books.bean.MagazineType;

import java.time.Year;

public class MagazineBuilder {
    private String name = "None";
    private int numberOfPages = 0;
    private MagazineType type = MagazineType.NONE;
    private String publishingHouse = "None";
    private Year yearOfPublishing = Year.of(0);

    public Magazine build() {
        return Magazine.of(name, numberOfPages, publishingHouse, yearOfPublishing, type);
    }

    public MagazineBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MagazineBuilder setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public MagazineBuilder setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public MagazineBuilder setYearOfPublishing(Year yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
        return this;
    }

    public MagazineBuilder setType(MagazineType type) {
        this.type = type;
        return this;
    }
}
