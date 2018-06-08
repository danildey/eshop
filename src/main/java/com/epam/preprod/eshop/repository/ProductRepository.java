package com.epam.preprod.eshop.repository;

import com.epam.preprod.eshop.entity.Product;

import java.util.Map;

public interface ProductRepository {

    Map<Product, Integer> getAllProducts();

    Integer removeProduct(Product product);

    Product getProduct(long productId);

    Integer addProduct(Product product, int quantity);

    Integer addProduct(Product product);

}
