package com.ilyabuglakov.task0201books.dal.specification.publication;

import com.ilyabuglakov.task0201books.bean.MagazineType;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import org.junit.Before;
import org.testng.annotations.Test;

import java.time.Year;

import static org.testng.Assert.*;

public class SameIdSpecificationTest {

    private SameIdSpecification specification = new SameIdSpecification();
    private final long ID = 1L;
    private Publication publication = Magazine.of("y", 1, "y", Year.of(1), MagazineType.SCIENCE);

    @Before
    public void prepareSpecification(){
        specification.setId(ID);
    }

    @Test
    public void testIsSatisfiedBy() {
        assertEquals(specification.isSatisfiedBy(publication), true);
    }
}