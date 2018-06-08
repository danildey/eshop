package com.epam.preprod.eshop.task5.filter;

import java.io.File;
import java.util.stream.Stream;

public class FileSizeFilter extends AbstractFilter {
    private long fromSize;
    private long toSize;

    public FileSizeFilter(AbstractFilter filter, long fromSize, long toSize) {
        super.next = filter;
        this.fromSize = fromSize;
        this.toSize = toSize;
    }

    @Override
    public Stream<File> doFilter(Stream<File> fileStream) {
        return checkNext(fileStream.filter(file -> file.length() > fromSize && file.length() < toSize));
    }
}
