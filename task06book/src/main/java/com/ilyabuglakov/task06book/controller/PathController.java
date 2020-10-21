package com.ilyabuglakov.task06book.controller;

public class PathController {
    private static PathController instance = new PathController();
    private ClassLoader classLoader;
    
    private PathController(){
       classLoader = this.getClass().getClassLoader();
    }

    public static PathController getInstance() {
        return instance;
    }

    public String getResourcePath(String relativePath){
        return classLoader.getResource(relativePath).getPath();
    }
}
