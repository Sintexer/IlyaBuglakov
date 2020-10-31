package com.ilyabuglakov.task0201books.model.publication;

import java.time.Year;
import java.util.Objects;

/**
 * Publication is abstract class used for representation of different print editions.
 *
 * Each deriving class should also override getPrefix() method.
 */
public abstract class Publication implements Comparable<Publication> {
    protected final String name;
    protected final int numberOfPages;
    protected final String publishingHouse;
    protected final Year yearOfPublishing;
    protected long id;

    public Publication(String name, int numberOfPages, String publishingHouse, Year yearOfPublishing) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.publishingHouse = publishingHouse;
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public String toString() {
        return new StringBuilder("Publication{").append(name)
                .append("|").append(numberOfPages)
                .append("|").append(publishingHouse)
                .append("|").append(yearOfPublishing)
                .append("}")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return numberOfPages == that.numberOfPages &&
                Objects.equals(name, that.name) &&
                Objects.equals(publishingHouse, that.publishingHouse) &&
                Objects.equals(yearOfPublishing, that.yearOfPublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfPages, publishingHouse, yearOfPublishing);
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public Year getYearOfPublishing() {
        return yearOfPublishing;
    }

    @Override
    public int compareTo(Publication o) {
        return new PublicationComparator().compare(this, o);
    }

    public static String getPrefix(){
        return "Publication";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
