package com.ilyabuglakov.task0201books.storage;

import com.ilyabuglakov.task0201books.bean.SpecificationName;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.book.BookComparator;

import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;

/**
 * BookComparatorMap stores all comparators for books
 */
public class BookComparatorMap {
    private static final BookComparatorMap INSTANCE = new BookComparatorMap();
    private EnumMap<SpecificationName, Comparator<Book>> comparators =
            new EnumMap<>(SpecificationName.class);


    private BookComparatorMap() {
        add(SpecificationName.BY_AUTHORS, BookComparator.comparingAuthors());
        add(SpecificationName.BY_ALL, new BookComparator());
    }

    public static BookComparatorMap getInstance() {
        return INSTANCE;
    }

    private void add(SpecificationName specificationName, Comparator<Book> comparator) {
        comparators.put(specificationName, comparator);
    }

    public Collection<SpecificationName> getSpecificationsNames() {
        return comparators.keySet();
    }

    public Comparator<Book> get(SpecificationName name) {
        return comparators.get(name);
    }
}