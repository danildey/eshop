package com.epam.preprod.eshop.tools.productcreator;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.message.Messages;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

import java.util.Map;

public class ProductCreatorsContainer {
    private Map<Integer, Creator<? extends Product>> creators;
    private InputInteraction interaction;

    public ProductCreatorsContainer(Map<Integer, Creator<? extends Product>> creators, InputInteraction interaction) {
        this.interaction = interaction;
        this.creators = creators;
    }

    public Creator<? extends Product> getCreator(int index) {
        return creators.get(index);
    }

    public Integer getQuantity() {
        return interaction.readInteger(Messages.ENTER_QUANTITY_MESSAGE);
    }
}