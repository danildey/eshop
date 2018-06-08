package com.epam.preprod.eshop.service.implementation;

import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Order;
import com.epam.preprod.eshop.repository.OrderRepository;
import com.epam.preprod.eshop.service.OrderService;

import java.time.LocalDateTime;
import java.util.NavigableMap;

public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long buyAll(LocalDateTime date, Basket basket) {
        return repository.buyAll(date, basket);
    }

    @Override
    public Long buyAll(Basket basket) {
        repository.buyAll(basket);
        return basket.getPrice();
    }

    @Override
    public NavigableMap<LocalDateTime, Order> getOrders(LocalDateTime from, LocalDateTime to) {
        return repository.getOrder(from, to);
    }

    @Override
    public Order getOrder(LocalDateTime date) {
        return repository.getOrder(date);
    }

    @Override
    public NavigableMap<LocalDateTime, Order> getAllOrders() {
        return repository.getAllOrders();
    }
}
