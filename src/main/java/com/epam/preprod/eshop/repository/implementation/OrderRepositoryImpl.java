package com.epam.preprod.eshop.repository.implementation;

import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Order;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.repository.OrderRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

public class OrderRepositoryImpl implements OrderRepository {
    private NavigableMap<LocalDateTime, Order> orders;
    private long totalPrice;

    public OrderRepositoryImpl() {
        this.orders = new TreeMap<>();
        this.totalPrice = 0;
    }

    @Override
    public Long buyAll(Basket basket) {
        return buyAll(LocalDateTime.now(), basket);
    }

    @Override
    public Long buyAll(LocalDateTime date, Basket basket) {
        if (!basket.isEmpty()) {
            if (orders.containsKey(date)) {
                Map<Product, Integer> ordersMap = orders.get(date).getProducts();
                Map<Product, Integer> basketMap = basket.getBasketMap();
                basketMap.forEach((k, v) -> ordersMap.merge(k, v, Integer::sum));
            } else {
                Order order = new Order(basket.getBasketMap(), basket.getPrice());
                orders.put(date, order);
                return totalPrice += basket.getPrice();
            }
        }
        return null;
    }

    @Override
    public NavigableMap<LocalDateTime, Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrder(LocalDateTime date) {
        LocalDateTime d1 = orders.floorKey(date);
        LocalDateTime d2 = orders.ceilingKey(date);

        if (Objects.equals(d1, d2)) return orders.get(date);
        if (Objects.isNull(d1)) return orders.get(d2);
        if (Objects.isNull(d2)) return orders.get(d1);

        Duration du1 = Duration.between(date, d2);
        Duration du2 = Duration.between(date, d1);

        return du1.compareTo(du2) > 0 ? orders.get(d1) : orders.get(d2);
    }

    @Override
    public NavigableMap<LocalDateTime, Order> getOrder(LocalDateTime from, LocalDateTime to) {
        NavigableMap<LocalDateTime, Order> result = orders.subMap(from, true, to, true);
        long price = 0L;
        for (Map.Entry<LocalDateTime, Order> entry : result.entrySet()) {
            price += entry.getValue().getPrice();
        }
        return result;
    }

    private long toSeconds(LocalDateTime dateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = dateTime.atZone(zoneId).toEpochSecond();
        return epoch;
    }
}
