package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.view.View;

public class ProductView implements View<Product> {
    DataOutput output;

    public ProductView(DataOutput output) {
        this.output = output;
    }

    @Override
    public void show(Product product) {
        output.print(product.toString());
    }
}
