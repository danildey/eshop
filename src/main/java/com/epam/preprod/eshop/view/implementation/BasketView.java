package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.view.View;

import java.util.Objects;

import static com.epam.preprod.eshop.message.Messages.BASKET_MESSAGE;

public class BasketView implements View<Basket> {
    DataOutput output;

    public BasketView(DataOutput output) {
        this.output = output;
    }

    @Override
    public void show(Basket basket) {
        if (Objects.isNull(basket) || basket.isEmpty()) {
            output.print("No one product in Basket.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(BASKET_MESSAGE);
            sb.append(basket.toString());
            output.print(sb.toString());
        }
    }
}
