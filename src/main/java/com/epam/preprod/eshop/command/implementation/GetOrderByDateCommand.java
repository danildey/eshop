package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import java.time.LocalDateTime;

import static com.epam.preprod.eshop.message.Messages.ENTER_DATE_MESSAGE;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.ORDER_VIEW;

public class GetOrderByDateCommand extends Command {

    public GetOrderByDateCommand(FacadeService facadeService, InputInteraction console, ViewController controller) {
        super(facadeService, console, controller);
    }

    @Override
    public void execute() {
        LocalDateTime date = console.readLocaleDate(ENTER_DATE_MESSAGE);
        controller.showView(ORDER_VIEW, facadeService.getOrder(date));
        controller.showView(MENU_VIEW, null);
    }
}
