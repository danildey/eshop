package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.entity.Cache;
import com.epam.preprod.eshop.view.View;

import java.util.Objects;

import static com.epam.preprod.eshop.message.Messages.LAST_5_PRODUCTS_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.PRODUCT_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.QUANTITY_MESSAGE;

public class CachedProductView implements View<Cache> {
    DataOutput output;

    public CachedProductView(DataOutput output) {
        this.output = output;
    }

    @Override
    public void show(Cache cache) {
        if (Objects.isNull(cache) || cache.isEmpty()) {
            output.print("No one product in Cache.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(LAST_5_PRODUCTS_MESSAGE);
            cache.forEach((k, v) -> {
                sb.append(PRODUCT_MESSAGE).append(k);
                sb.append(QUANTITY_MESSAGE).append(v);
                sb.append(System.lineSeparator());
            });
            output.print(sb.toString());
        }

    }
}
