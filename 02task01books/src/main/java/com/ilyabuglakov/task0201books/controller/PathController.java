package com.ilyabuglakov.task0201books.controller;

/**
 * Path controller is used to simply generate path for output and input files
 * at the runtime.
 */
public class PathController {
    private static PathController instance = new PathController();
    private String RESOURCE_PATH = "data/";
    private String FULL_PATH;
    private ClassLoader classLoader = this.getClass().getClassLoader();

    private PathController() {
        FULL_PATH = classLoader.getResource(RESOURCE_PATH).getPath() + "/";
    }

    public static PathController getInstance() {
        return instance;
    }

    public String getResourcePath(String fileName) {
        return FULL_PATH + fileName;
    }
}
