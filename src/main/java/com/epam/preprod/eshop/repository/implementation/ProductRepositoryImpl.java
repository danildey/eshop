package com.epam.preprod.eshop.repository.implementation;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {
    private Map<Product, Integer> products;

    public ProductRepositoryImpl() {
        products = new HashMap<>();
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return new HashMap<>(products);
    }

    @Override
    public Integer removeProduct(Product product) {
        return products.remove(product);
    }

    @Override
    public Product getProduct(long productId) {
        return products.keySet().stream()
                .filter(p -> p.getId() == productId)
                .findFirst().orElse(null);
    }

    @Override
    public Integer addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            int newQuantity = products.get(product) + quantity;
            return products.put(product, newQuantity);
        } else {
            return products.put(product, quantity);
        }
    }

    @Override
    public Integer addProduct(Product product) {
        if (products.containsKey(product)) {
            int newQuantity = products.get(product) + 1;
            return products.put(product, newQuantity);
        } else {
            return products.put(product, 1);
        }
    }
}
