package com.ilyabuglakov.task0201books.dal.specification.publication;

import com.ilyabuglakov.task0201books.bean.MagazineType;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.service.validator.IntValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Year;

import static org.testng.Assert.*;

public class SameNameSpecificationTest {


    @DataProvider(name = "validationData")
    public Object[][] createValidationData(){
        SameNameSpecification specification = new SameNameSpecification();
        specification.setName("y");
        return  new Object[][]{
                {specification, Magazine.of("y", 1, "y", Year.of(1), MagazineType.SCIENCE), true},
                {specification, Magazine.of("ya", 1, "y", Year.of(1), MagazineType.SCIENCE), false},
                {specification, Magazine.of("r", 1, "y", Year.of(1), MagazineType.SCIENCE), false},
                {specification, Magazine.of("yyyyy", 1, "y", Year.of(1), MagazineType.SCIENCE), false},
                {specification, Magazine.of("y", 100, "asf", Year.of(1), MagazineType.MEDICINE), true},
        };
    }

    @Test(description = "Test of SameNameSpecification",
            dataProvider = "validationData")
    public void testIsSatisfiedBy(SameNameSpecification specification, Publication publication, boolean expexted){
        assertEquals(specification.isSatisfiedBy(publication), expexted);
    }

}