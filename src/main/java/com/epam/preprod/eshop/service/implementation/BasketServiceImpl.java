package com.epam.preprod.eshop.service.implementation;

import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.service.BasketService;

import java.util.Map;

public class BasketServiceImpl implements BasketService {
    private Basket basket;

    public BasketServiceImpl(Basket basket) {
        this.basket = new Basket();
    }

    @Override
    public Basket getBasket() {
        return basket;
    }

    @Override
    public Integer putIntoBasket(Product product, int quantity) {
        return basket.putIntoBasket(product, quantity);
    }

    @Override
    public Map<Product, Integer> getCachedProduct() {
        return basket.getCached();
    }

    @Override
    public boolean removeFromBasket(long productId) {
        return basket.removeFromBasket(productId);
    }

    @Override
    public void clearBasket() {
        basket.clear();
    }


}
