package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.view.View;

import java.util.Map;
import java.util.Objects;

import static com.epam.preprod.eshop.message.Messages.ALL_PRODUCTS_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.PRODUCT_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.QUANTITY_MESSAGE;

public class AllProductView implements View<Map<Product, Integer>> {

    DataOutput output;

    public AllProductView(DataOutput output) {
        this.output = output;
    }

    @Override
    public void show(Map<Product, Integer> products) {
        if (Objects.isNull(products) || products.isEmpty()) {
            output.print("No one Product in shop.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(ALL_PRODUCTS_MESSAGE);
            products.forEach((k, v) -> {
                sb.append(PRODUCT_MESSAGE).append(k);
                sb.append(QUANTITY_MESSAGE).append(v);
                sb.append(System.lineSeparator());
            });
            output.print(sb.toString());
        }
    }
}
