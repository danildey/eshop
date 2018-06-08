package com.epam.preprod.eshop.tools.productcreator.creator;

import com.epam.preprod.eshop.entity.PrintedBook;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

import static com.epam.preprod.eshop.message.Messages.ENTER_PRINTED_BOOK_BACK;
import static com.epam.preprod.eshop.message.Messages.ENTER_PRINTED_BOOK_PAGES;

public abstract class PrintedBookCreator<T extends PrintedBook> extends BookCreator<T> {
    public PrintedBookCreator(InputInteraction interaction) {
        super(interaction);
    }

    @Override
    protected void init(T product) {
        super.init(product);
        product.setPages(interaction.readInteger(ENTER_PRINTED_BOOK_PAGES));
        product.setHardBack(interaction.readBoolean(ENTER_PRINTED_BOOK_BACK));
    }
}
