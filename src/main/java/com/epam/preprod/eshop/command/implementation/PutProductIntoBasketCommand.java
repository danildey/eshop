package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import java.util.NoSuchElementException;

import static com.epam.preprod.eshop.message.Messages.COMPLETED_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.ENTER_PRODUCT_ID;
import static com.epam.preprod.eshop.message.Messages.ENTER_QUANTITY_MESSAGE;
import static com.epam.preprod.eshop.view.ViewCommands.BASKET_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.STRING_VIEW;

public class PutProductIntoBasketCommand extends Command {

    public PutProductIntoBasketCommand(FacadeService facadeService, InputInteraction console, ViewController controller) {
        super(facadeService, console, controller);
    }

    @Override
    public void execute() {
        Long id = console.readLong(ENTER_PRODUCT_ID);
        try {
            Product product = facadeService.getProduct(id);
            Integer quantity = console.readInteger(ENTER_QUANTITY_MESSAGE);
            facadeService.putIntoBasket(product, quantity);
            controller.showView(STRING_VIEW, COMPLETED_MESSAGE);
            controller.showView(BASKET_VIEW, facadeService.getBasket());
        } catch (NoSuchElementException ex) {
            controller.showView(STRING_VIEW, "Product with ID '" + id + "' does not exist.");
        }
        controller.showView(MENU_VIEW, null);
    }
}
