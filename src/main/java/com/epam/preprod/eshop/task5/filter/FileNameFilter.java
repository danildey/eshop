package com.epam.preprod.eshop.task5.filter;

import java.io.File;
import java.util.stream.Stream;

public class FileNameFilter extends AbstractFilter {
    private String filename;

    public FileNameFilter(AbstractFilter filter, String filename) {
        super.next = filter;
        this.filename = filename;
    }

    @Override
    public Stream<File> doFilter(Stream<File> fileStream) {
        return checkNext(fileStream.filter(file -> {
            return file.getName().replaceFirst("[.][^.]+$", "").startsWith(filename);
        }));
    }

}
