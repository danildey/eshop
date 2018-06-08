package com.epam.preprod.eshop.entity;

import java.io.Serializable;
import java.util.Map;

import static com.epam.preprod.eshop.message.Messages.ORDER_PRICE_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.PRODUCT_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.QUANTITY_MESSAGE;

public class Order implements Serializable {
    private long price;
    private Map<Product, Integer> products;

    public Order(Map<Product, Integer> products, long price) {
        this.price = price;
        this.products = products;
    }

    public long getPrice() {
        return price;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (price != order.price) return false;
        return products.equals(order.products);
    }

    @Override
    public int hashCode() {
        int result = (int) (price ^ (price >>> 32));
        result = 31 * result + products.hashCode();
        return result;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        products.forEach((k, v) -> {
            sb.append(PRODUCT_MESSAGE).append(k);
            sb.append(QUANTITY_MESSAGE).append(v);
            sb.append("\n");
        });
        sb.append(ORDER_PRICE_MESSAGE).append(price).append("\n");
        return sb.toString();
    }
}
