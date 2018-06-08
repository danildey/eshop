package com.epam.preprod.eshop.task5;

import com.epam.preprod.eshop.task5.filter.AbstractFilter;
import com.epam.preprod.eshop.task5.reader.FileReaderWrapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class Demo {
    static final String FILE_PATH = "src/main/resources/test.txt";
    static final String SEARCH_DIR = "src/main/java/com/epam/preprod/eshop";

    public static void main(String[] args) throws IOException {
        FileReaderWrapper fileReaderWrapper = new FileReaderWrapper(FILE_PATH);
        for (int i = 0; i < fileReaderWrapper.limit(); i++) {
            System.out.println(fileReaderWrapper.readLine(i));
        }

        Path searchPath = Paths.get(SEARCH_DIR);

        FilterInitializer initializer = new FilterInitializer();
        initializer.init();
        AbstractFilter fileFilter = initializer.getFileFilter();
        if (Objects.nonNull(fileFilter)) {
            try (Stream<File> stream = Files.walk(searchPath).map(Path::toFile)) {
                fileFilter.doFilter(stream).forEach(path -> {
                    System.out.println(path);
                });
            }
        }
    }
}
