package com.epam.preprod.eshop.tools.productcreator.creator;

import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

import static com.epam.preprod.eshop.message.Messages.ENTER_BOOK_PUBLICATION_DATE;
import static com.epam.preprod.eshop.message.Messages.ENTER_TEXTBOOK_CLASSIFICATION;
import static com.epam.preprod.eshop.message.Messages.ENTER_TEXTBOOK_SUBJECT_AREA;

public class TextBookCreator extends PrintedBookCreator<TextBook> {

    public TextBookCreator(InputInteraction interaction) {
        super(interaction);
    }

    @Override
    public TextBook create() {
        TextBook textBook = new TextBook();
        init(textBook);
        return textBook;
    }

    @Override
    protected void init(TextBook product) {
        super.init(product);
        product.setSubjectArea(interaction.readString(ENTER_TEXTBOOK_SUBJECT_AREA));
        product.setClassification(interaction.readString(ENTER_TEXTBOOK_CLASSIFICATION));
        product.setPublicationDate(interaction.readLocaleDate(ENTER_BOOK_PUBLICATION_DATE));
    }
}
