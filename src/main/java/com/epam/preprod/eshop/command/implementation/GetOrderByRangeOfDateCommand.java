package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import java.time.LocalDateTime;

import static com.epam.preprod.eshop.message.Messages.ENTER_DATE_FROM_MESSAGE;
import static com.epam.preprod.eshop.message.Messages.ENTER_DATE_TO_MESSAGE;
import static com.epam.preprod.eshop.view.ViewCommands.ALL_ORDERS_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;

public class GetOrderByRangeOfDateCommand extends Command {

    public GetOrderByRangeOfDateCommand(FacadeService facadeService, InputInteraction console, ViewController controller) {
        super(facadeService, console, controller);
    }

    @Override
    public void execute() {
        LocalDateTime from = null;
        LocalDateTime to = null;
        do {
            from = console.readLocaleDate(ENTER_DATE_FROM_MESSAGE);
            to = console.readLocaleDate(ENTER_DATE_TO_MESSAGE);
        } while (from.compareTo(to) > 0);
        controller.showView(ALL_ORDERS_VIEW, facadeService.getOrders(from, to));
        controller.showView(MENU_VIEW, null);
    }
}
