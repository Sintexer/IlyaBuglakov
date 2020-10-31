package com.ilyabuglakov.task0201books;

import com.ilyabuglakov.task0201books.bean.MagazineType;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.PathController;
import com.ilyabuglakov.task0201books.dal.repository.PublicationRepository;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.service.file.FilePublicationReader;
import com.ilyabuglakov.task0201books.service.parser.PublicationParser;

import java.io.FileNotFoundException;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;

public class Runner {
    public static void main(String[] args) {
        String path = PathController.getInstance().getResourcePath("input.txt");
        try {
            FilePublicationReader reader = new FilePublicationReader(path);
            while (reader.hasNext()){
                System.out.println(reader.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
