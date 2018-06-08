package com.epam.preprod.eshop.task5.filter;

import java.io.File;
import java.util.stream.Stream;

public abstract class AbstractFilter {
    protected AbstractFilter next;

    public abstract Stream<File> doFilter(Stream<File> fileStream);

    protected Stream<File> checkNext(Stream<File> fileStream) {
        if (next == null) {
            return fileStream;
        }
        return next.doFilter(fileStream);
    }
}
