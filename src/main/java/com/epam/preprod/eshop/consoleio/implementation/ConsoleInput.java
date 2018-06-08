package com.epam.preprod.eshop.consoleio.implementation;

import com.epam.preprod.eshop.consoleio.DataInput;

import java.io.InputStream;

public class ConsoleInput implements DataInput {
    @Override
    public InputStream in() {
        return System.in;
    }
}
