package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.entity.Order;
import com.epam.preprod.eshop.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NavigableMap;
import java.util.Objects;

import static com.epam.preprod.eshop.message.Messages.DATE_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.ORDERS_MESSAGE;

public class AllOrdersView implements View<NavigableMap<LocalDateTime, Order>> {
    DataOutput output;
    DateTimeFormatter formatter;

    public AllOrdersView(DataOutput output) {
        this.output = output;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    public void show(NavigableMap<LocalDateTime, Order> orders) {
        if (Objects.isNull(orders) || orders.isEmpty()) {
            output.print("No one Order.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(ORDERS_MESSAGE);

            orders.forEach((k, v) -> {
                sb.append(DATE_MESSAGE);
                sb.append(k.format(formatter));
                sb.append(System.lineSeparator());
                sb.append(v);
            });
            output.print(sb.toString());
        }
    }


}
