package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.tools.productcreator.Creator;
import com.epam.preprod.eshop.tools.productcreator.ProductCreatorsContainer;
import com.epam.preprod.eshop.view.ViewController;

import java.util.Objects;

import static com.epam.preprod.eshop.message.Messages.CHOOSE_PRODUCT;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;

public class CreateProductCommand extends Command {
    ProductCreatorsContainer productCreators;

    public CreateProductCommand(FacadeService facadeService, InputInteraction console,
                                ViewController controller, ProductCreatorsContainer strategy) {
        super(facadeService, console, controller);
        this.productCreators = strategy;
    }

    @Override
    public void execute() {
        Creator<? extends Product> productCreator = null;
        do {
            int chosenProduct = console.readInteger(CHOOSE_PRODUCT);
            productCreator = productCreators.getCreator(chosenProduct);
        } while (Objects.isNull(productCreator));

        facadeService.addProduct(productCreator.create(), productCreators.getQuantity());
        controller.showView(MENU_VIEW, null);
    }
}