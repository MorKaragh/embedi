package ru.alfastrah.embedi.tick.dao.repos;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;

import org.junit.jupiter.api.Test;

public class ClasspathTest {

    @Test
    void checkClasspathFiles() throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> resources = classLoader.getResources("db_schema/migrations");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println("Classpath entry: " + url.getFile());
            if (url.getProtocol().equals("file")) {
                File directory = new File(url.toURI());
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        System.out.println("   - " + file.getName());
                    }
                }
            }
        }
    }
}
