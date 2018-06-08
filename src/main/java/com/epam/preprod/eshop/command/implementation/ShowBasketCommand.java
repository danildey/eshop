package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import static com.epam.preprod.eshop.view.ViewCommands.BASKET_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;

public class ShowBasketCommand extends Command {

    public ShowBasketCommand(FacadeService facadeService, InputInteraction console, ViewController controller) {
        super(facadeService, console, controller);
    }

    @Override
    public void execute() {
        controller.showView(BASKET_VIEW, facadeService.getBasket());
        controller.showView(MENU_VIEW, null);
    }
}
