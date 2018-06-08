package com.epam.preprod.eshop.service;

import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Order;

import java.time.LocalDateTime;
import java.util.NavigableMap;

public interface OrderService {
    Long buyAll(LocalDateTime date, Basket basket);

    Long buyAll(Basket basket);

    NavigableMap<LocalDateTime, Order> getOrders(LocalDateTime from, LocalDateTime to);

    Order getOrder(LocalDateTime date);

    NavigableMap<LocalDateTime, Order> getAllOrders();


}
