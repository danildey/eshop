package com.epam.preprod.eshop.tools.productcreator.creator;

import com.epam.preprod.eshop.entity.Book;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

import static com.epam.preprod.eshop.message.Messages.ENTER_BOOK_AUTHOR;
import static com.epam.preprod.eshop.message.Messages.ENTER_BOOK_GENRE;
import static com.epam.preprod.eshop.message.Messages.ENTER_BOOK_ISBN;
import static com.epam.preprod.eshop.message.Messages.ENTER_BOOK_PUBLISHER;

public abstract class BookCreator<T extends Book> extends ProductCreator<T> {
    public BookCreator(InputInteraction interaction) {
        super(interaction);
    }

    @Override
    protected void init(T product) {
        super.init(product);
        product.setIsbn(interaction.readInteger(ENTER_BOOK_ISBN));
        product.setAuthor(interaction.readString(ENTER_BOOK_AUTHOR));
        product.setGenre(interaction.readString(ENTER_BOOK_GENRE));
        product.setPublisher(interaction.readString(ENTER_BOOK_PUBLISHER));
    }
}
