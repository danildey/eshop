package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import static com.epam.preprod.eshop.view.ViewCommands.ALL_ORDERS_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;


public class BuyAllFromBasketCommand extends Command {
    public BuyAllFromBasketCommand(FacadeService facadeService, InputInteraction console, ViewController controller) {
        super(facadeService, console, controller);
    }

    @Override
    public void execute() {
        facadeService.buyAll(facadeService.getBasket());
        controller.showView(ALL_ORDERS_VIEW, facadeService.getAllOrders());
        facadeService.clearBasket();
        controller.showView(MENU_VIEW, null);
    }
}
