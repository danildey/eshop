package com.epam.preprod.eshop.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.epam.preprod.eshop.message.Messages.PRODUCT_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.QUANTITY_MESSAGE;

public class Basket implements Serializable {

    private Map<Product, Integer> basket;
    private Cache cached;
    private long price;

    public Basket() {
        basket = new HashMap<>();
        cached = new Cache();
    }

    public Basket(Map<Product, Integer> basketMap) {
        basket = basketMap;
    }

    public Map<Product, Integer> getBasketMap() {

        return new HashMap(basket);
    }

    public Integer putIntoBasket(Product product, int quantity) {
        cached.put(product, quantity);
        price += product.getPrice() * quantity;
        if (basket.containsKey(product)) {
            int newQuantity = basket.get(product) + quantity;
            return basket.put(product, newQuantity);
        } else {
            return basket.put(product, quantity);
        }
    }

    public boolean removeFromBasket(Long productId) {
        return basket.keySet().removeIf(p -> p.getId() == productId);
    }

    public long getPrice() {
        return price;
    }

    public Cache getCached() {
        return cached;
    }

    public boolean isEmpty() {
        return basket.isEmpty();
    }

    public void clear() {
        basket.clear();
        price = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        basket.forEach((k, v) -> {
            sb.append(PRODUCT_MESSAGE).append(k);
            sb.append(QUANTITY_MESSAGE).append(v);
            sb.append("\n");
        });
        return sb.toString();
    }
}
