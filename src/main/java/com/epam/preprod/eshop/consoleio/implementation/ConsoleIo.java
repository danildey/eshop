package com.epam.preprod.eshop.consoleio.implementation;

import com.epam.preprod.eshop.consoleio.DataInput;
import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.consoleio.FacadeDataIo;

import java.io.InputStream;

public class ConsoleIo implements FacadeDataIo {
    DataInput input;
    DataOutput output;

    public ConsoleIo(DataInput input, DataOutput output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public InputStream in() {
        return input.in();
    }

    @Override
    public void print(String s) {
        output.print(s);
    }
}
