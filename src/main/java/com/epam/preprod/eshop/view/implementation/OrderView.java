package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.entity.Order;
import com.epam.preprod.eshop.view.View;

import java.util.Objects;

import static com.epam.preprod.eshop.message.Messages.ORDERS_MESSAGE;

public class OrderView implements View<Order> {
    DataOutput output;

    public OrderView(DataOutput output) {
        this.output = output;
    }

    @Override
    public void show(Order order) {
        if (Objects.isNull(order) || order.isEmpty()) {
            output.print("No one product in Order.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(ORDERS_MESSAGE);
            sb.append(order.toString());
            output.print(sb.toString());
        }
    }
}
