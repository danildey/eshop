package com.epam.preprod.eshop.task5.filter;

import java.io.File;
import java.util.stream.Stream;

public class FileExtensionFilter extends AbstractFilter {
    private String extension;

    public FileExtensionFilter(AbstractFilter filter, String extension) {
        super.next = filter;
        this.extension = extension;
    }

    @Override
    public Stream<File> doFilter(Stream<File> fileStream) {
        return checkNext(fileStream.filter(file -> file.getName().endsWith(extension)));
    }
}
