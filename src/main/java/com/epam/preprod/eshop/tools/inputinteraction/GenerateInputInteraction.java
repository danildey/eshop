package com.epam.preprod.eshop.tools.inputinteraction;

import com.epam.preprod.eshop.entity.Product;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

public class GenerateInputInteraction implements InputInteraction {
    private Random random;
    private List<Class<? extends Product>> products;

    public GenerateInputInteraction(Random random, List<Class<? extends Product>> products) {
        this.products = products;
        this.random = random;
    }

    @Override
    public Integer readInteger(String msg) {
        return Math.abs(random.nextInt());
    }

    @Override
    public Boolean readBoolean(String msg) {
        return random.nextBoolean();
    }

    @Override
    public String readString(String msg) {
        return String.valueOf(random.nextInt());
    }

    @Override
    public Long readLong(String msg) {
        return Math.abs(random.nextLong());
    }

    @Override
    public LocalDateTime readLocaleDate(String msg) {
        Instant instant = Instant.ofEpochMilli(random.nextLong());
        LocalDateTime randomDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return randomDate;
    }

    @Override
    public Class<? extends Product> readProductClass(String msg) {
        return products.get(random.nextInt(products.size()));
    }
}