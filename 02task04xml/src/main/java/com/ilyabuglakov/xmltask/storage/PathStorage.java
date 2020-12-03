package com.ilyabuglakov.xmltask.storage;

import com.ilyabuglakov.xmltask.service.PathService;

public class PathStorage {
    public static final PathStorage instance = new PathStorage();
    private static final String inputFileName = "gems.xml";
    private static final String xsdFileName = "gems.xsd";

    private PathStorage() {
    }

    public static PathStorage getInstance() {
        return instance;
    }

    public String getInputPath() {
        return PathService.getInstance().getResourcePath(inputFileName);
    }

    public String getXsdPath() {
        return PathService.getInstance().getResourcePath(xsdFileName);
    }
}
