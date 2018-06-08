package com.epam.preprod.eshop.task7.factory;

import com.epam.preprod.eshop.task7.TextBookInterface;

public interface TextBookFactory {
    TextBookInterface createTextBook(TextBookInterface textBookInterface) throws IllegalAccessException;
}
