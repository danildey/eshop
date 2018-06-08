package com.epam.preprod.eshop.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache extends LinkedHashMap {
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > 5;
    }
}
