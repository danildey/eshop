package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import static com.epam.preprod.eshop.view.ViewCommands.ALL_PRODUCTS_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;

public class ShowAllProductsCommand extends Command {

    public ShowAllProductsCommand(FacadeService facadeService, InputInteraction console, ViewController controller) {
        super(facadeService, console, controller);
    }

    @Override
    public void execute() {
        controller.showView(ALL_PRODUCTS_VIEW, facadeService.getAllProducts());
        controller.showView(MENU_VIEW, null);
    }
}
