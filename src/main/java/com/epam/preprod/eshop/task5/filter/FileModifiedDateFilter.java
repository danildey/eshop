package com.epam.preprod.eshop.task5.filter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

public class FileModifiedDateFilter extends AbstractFilter {
    private long fromDate;
    private long toDate;

    public FileModifiedDateFilter(AbstractFilter filter, LocalDateTime fromDate, LocalDateTime toDate) {
        super.next = filter;
        this.fromDate = fromDate.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
        this.toDate = toDate.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
    }

    @Override
    public Stream<File> doFilter(Stream<File> fileStream) {
        return checkNext(fileStream.filter(file -> file.lastModified() > fromDate && file.lastModified() < toDate));
    }
}
