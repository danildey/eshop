package com.epam.preprod.eshop.tools.productcreator.creator;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.tools.productcreator.Creator;

import static com.epam.preprod.eshop.message.Messages.ENTER_PRODUCT_DESCRIPTION;
import static com.epam.preprod.eshop.message.Messages.ENTER_PRODUCT_ID;
import static com.epam.preprod.eshop.message.Messages.ENTER_PRODUCT_NAME;
import static com.epam.preprod.eshop.message.Messages.ENTER_PRODUCT_PRICE;

public abstract class ProductCreator<T extends Product> implements Creator<T> {
    protected InputInteraction interaction;

    public ProductCreator(InputInteraction interaction) {
        this.interaction = interaction;
    }

    protected void init(T product) {
        product.setId(interaction.readLong(ENTER_PRODUCT_ID));
        product.setPrice(interaction.readLong(ENTER_PRODUCT_PRICE));
        product.setDescription(interaction.readString(ENTER_PRODUCT_DESCRIPTION));
        product.setName(interaction.readString(ENTER_PRODUCT_NAME));
    }
}
