package com.epam.preprod.eshop.tools.inputinteraction;

import com.epam.preprod.eshop.entity.Product;

import java.time.LocalDateTime;

public interface InputInteraction {
    Integer readInteger(String msg);

    Boolean readBoolean(String msg);

    String readString(String msg);

    Long readLong(String msg);

    LocalDateTime readLocaleDate(String msg);

    Class<? extends Product> readProductClass(String msg);
}
