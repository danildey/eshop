package com.epam.preprod.eshop.task5.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class FileReaderWrapper implements Iterable {
    private List<String> contents;

    public FileReaderWrapper(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        contents = Files.readAllLines(path);
    }

    public String readLine(int line) {
        return contents.get(line);
    }

    public int limit() {
        return contents.size();
    }

    @Override
    public Iterator iterator() {
        return contents.iterator();
    }

}
