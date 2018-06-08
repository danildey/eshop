package com.epam.preprod.eshop.consoleio.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;

public class ConsolePrinter implements DataOutput {

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}
