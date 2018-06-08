package com.epam.preprod.eshop.task5;

import com.epam.preprod.eshop.task5.filter.AbstractFilter;
import com.epam.preprod.eshop.task5.filter.FileExtensionFilter;
import com.epam.preprod.eshop.task5.filter.FileModifiedDateFilter;
import com.epam.preprod.eshop.task5.filter.FileNameFilter;
import com.epam.preprod.eshop.task5.filter.FileSizeFilter;

import java.time.LocalDateTime;

public class FilterInitializer {
    AbstractFilter fileFilter = null;

    public void init() {
        ConsoleInteraction console = new ConsoleInteraction();
        if (console.useFilter("Filter by name ?(0/1)")) {
            String argument = console.readString("Enter file name :");
            fileFilter = new FileNameFilter(fileFilter, argument);
        }
        if (console.useFilter("Filter by extension ?(0/1)")) {
            String argument = console.readExtension("Enter extension ' .extension ':");
            fileFilter = new FileExtensionFilter(fileFilter, argument);
        }
        if (console.useFilter("Filter by size range?(0/1)")) {
            Long from = console.readLong("Enter size from :");
            Long to = console.readLong("Enter size to :");
            fileFilter = new FileSizeFilter(fileFilter, from, to);
        }
        if (console.useFilter("Filter by modified data range ?(0/1)")) {
            LocalDateTime from = console.readLocalDate("Enter data from 'YYYY-MM-DD HH:MM':");
            LocalDateTime to = console.readLocalDate("Enter data to 'YYYY-MM-DD HH:MM':");
            fileFilter = new FileModifiedDateFilter(fileFilter, from, to);
        }
    }


    public AbstractFilter getFileFilter() {
        return fileFilter;
    }
}
