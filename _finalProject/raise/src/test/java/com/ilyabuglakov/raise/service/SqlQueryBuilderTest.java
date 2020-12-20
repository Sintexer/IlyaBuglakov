package com.ilyabuglakov.raise.service;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Log4j2
public class SqlQueryBuilderTest {

    SqlQueryBuilder sqlQueryBuilder;

    @BeforeMethod
    private void configure(){
        sqlQueryBuilder = new SqlQueryBuilder("usr");
    }

    @Test
    public void testInsert(){
        String expected = "INSERT INTO usr (name, surname, email) VALUES ('Ilya', 'Buglakov', 'ilboogl@gmail.com')";
        sqlQueryBuilder.addField("name", "Ilya");
        sqlQueryBuilder.addField("surname", "Buglakov");
        sqlQueryBuilder.addField("email", "ilboogl@gmail.com");
        String actual = sqlQueryBuilder.buildInsertQuery();
        log.info(actual);
        assertEquals(actual, expected);
    }

    @Test
    public void testSelectAll(){
        String expected = "SELECT * FROM usr";
        String actual = sqlQueryBuilder.buildSelectQuery();
        log.info(actual);
        assertEquals(actual, expected);
    }

    @Test
    public void testSelectAllWhere(){
        String expected = "SELECT * FROM usr WHERE id='0', name='Ilya'";
        sqlQueryBuilder.addWhere("id", 0);
        sqlQueryBuilder.addWhere("name", "Ilya");
        String actual = sqlQueryBuilder.buildSelectQuery();
        log.info(actual);
        assertEquals(actual, expected);
    }

    @Test
    public void testSelectId(){
        String expected = "SELECT (id) FROM usr WHERE id='0', name='Ilya'";
        sqlQueryBuilder.addField("id");
        sqlQueryBuilder.addWhere("id", 0);
        sqlQueryBuilder.addWhere("name", "Ilya");
        String actual = sqlQueryBuilder.buildSelectQuery();
        log.info(actual);
        assertEquals(actual, expected);
    }

    @Test
    public void testUpdate(){
        String expected = "UPDATE usr SET name='i', surname='blabla' WHERE id='0'";
        sqlQueryBuilder.addField("name", "i");
        sqlQueryBuilder.addField("surname", "blabla");
        sqlQueryBuilder.addWhere("id", 0);
        String actual = sqlQueryBuilder.buildUpdateQuery();
        log.info(actual);
        assertEquals(actual, expected);
    }

}