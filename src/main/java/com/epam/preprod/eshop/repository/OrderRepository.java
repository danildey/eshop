package com.epam.preprod.eshop.repository;

import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Order;

import java.time.LocalDateTime;
import java.util.NavigableMap;

public interface OrderRepository {

    Long buyAll(LocalDateTime date, Basket basket);

    Long buyAll(Basket basket);

    NavigableMap<LocalDateTime, Order> getOrder(LocalDateTime from, LocalDateTime to);

    Order getOrder(LocalDateTime date);

    NavigableMap<LocalDateTime, Order> getAllOrders();

}
