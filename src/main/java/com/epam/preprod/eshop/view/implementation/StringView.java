package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.view.View;

public class StringView implements View<String> {
    DataOutput output;

    public StringView(DataOutput dataOutput) {
        this.output = dataOutput;
    }

    @Override
    public void show(String str) {
        output.print(str);
    }
}
