package com.epam.preprod.eshop.service.implementation;

import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Order;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.service.BasketService;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.service.OrderService;
import com.epam.preprod.eshop.service.ProductService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableMap;

public class FacadeServiceImpl implements FacadeService {
    private BasketService basketService;
    private OrderService orderService;
    private ProductService productService;

    public FacadeServiceImpl(BasketService basketService, OrderService orderService, ProductService productService) {
        this.basketService = basketService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public Basket getBasket() {
        return basketService.getBasket();
    }

    @Override
    public Integer putIntoBasket(Product product, int quantity) {
        return basketService.putIntoBasket(product, quantity);
    }

    @Override
    public Map<Product, Integer> getCachedProduct() {
        return basketService.getCachedProduct();
    }

    @Override
    public boolean removeFromBasket(long productId) {
        return basketService.removeFromBasket(productId);
    }

    @Override
    public void clearBasket() {
        basketService.clearBasket();
    }

    @Override
    public Long buyAll(LocalDateTime date, Basket basket) {
        return orderService.buyAll(date, basket);
    }

    @Override
    public Long buyAll(Basket basket) {
        return orderService.buyAll(basket);
    }

    @Override
    public NavigableMap<LocalDateTime, Order> getOrders(LocalDateTime from, LocalDateTime to) {
        return orderService.getOrders(from, to);
    }

    @Override
    public Order getOrder(LocalDateTime date) {
        return orderService.getOrder(date);
    }

    @Override
    public NavigableMap<LocalDateTime, Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public Integer removeProduct(Product product) {
        return productService.removeProduct(product);
    }

    @Override
    public Product getProduct(long productId) {
        return productService.getProduct(productId);
    }

    @Override
    public Integer addProduct(Product product, int quantity) {
        return productService.addProduct(product, quantity);
    }

    @Override
    public Integer addProduct(Product product) {
        return productService.addProduct(product);
    }
}
