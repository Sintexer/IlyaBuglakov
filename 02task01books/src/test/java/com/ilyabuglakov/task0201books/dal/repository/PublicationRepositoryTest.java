package com.ilyabuglakov.task0201books.dal.repository;

import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.HashSet;
import java.util.Optional;

import static org.testng.Assert.*;

public class PublicationRepositoryTest {

    @Test
    public void testAdd() {
        Publication publication = Book.of("y", 1, "y", Year.of(1), new HashSet<>());
        try {
            long id = PublicationRepository.getInstance().add(publication);
            assertEquals(
                PublicationRepository.getInstance().get(id),
                    Optional.of(publication));
        } catch (DaoAddException e) {
            e.printStackTrace();
        }
    }
}