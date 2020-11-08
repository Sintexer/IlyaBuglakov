package com.ilyabuglakov.matrix;

import com.ilyabuglakov.matrix.controller.ApplicationController;

public class Runner {
    public static void main(String[] args) {
//        Row row = new Row();
//        row.setCells(Arrays.asList(Cell.of(1), Cell.of(2)));
//        System.out.println(row);
        ApplicationController.getInstance().start();
    }
}
