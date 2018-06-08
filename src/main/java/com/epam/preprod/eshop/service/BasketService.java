package com.epam.preprod.eshop.service;

import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Product;

import java.util.Map;

public interface BasketService {
    Basket getBasket();

    Integer putIntoBasket(Product product, int quantity);

    Map<Product, Integer> getCachedProduct();

    boolean removeFromBasket(long productId);

    void clearBasket();
}
