package com.ilyabuglakov.raise.model.service.path;

public class PathService {
    private final String FULL_PATH;

    private PathService() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String RESOURCE_PATH = "config";
        FULL_PATH = classLoader.getResource(RESOURCE_PATH).getPath();
    }

    private static class InstanceHolder{
        public static final PathService INSTANCE = new PathService();
    }

    public static PathService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public String getResourcePath(String fileName) {
        return FULL_PATH + "/" + fileName;
    }



}
