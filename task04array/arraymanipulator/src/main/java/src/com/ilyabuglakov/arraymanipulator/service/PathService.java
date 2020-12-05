package src.com.ilyabuglakov.arraymanipulator.service;

/**
 * Path service helps to generate correct file path during runtime.
 */
public class PathService {

    private static PathService instance = new PathService();
    private String RESOURCE_PATH = "data/";
    private String FULL_PATH;
    private ClassLoader classLoader = this.getClass().getClassLoader();

    private PathService() {
        FULL_PATH = classLoader.getResource(RESOURCE_PATH).getPath();
    }

    public static PathService getInstance() {
        return instance;
    }

    public String getResourcePath(String fileName) {
        return FULL_PATH + fileName;
    }

}
