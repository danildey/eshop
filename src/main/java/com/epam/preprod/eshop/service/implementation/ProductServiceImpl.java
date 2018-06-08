package com.epam.preprod.eshop.service.implementation;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.repository.ProductRepository;
import com.epam.preprod.eshop.service.ProductService;

import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public Integer removeProduct(Product product) {
        return repository.removeProduct(product);
    }

    @Override
    public Product getProduct(long productId) {
        return repository.getProduct(productId);
    }

    @Override
    public Integer addProduct(Product product, int quantity) {
        return repository.addProduct(product, quantity);
    }

    @Override
    public Integer addProduct(Product product) {
        return repository.addProduct(product);
    }


}
