package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import static com.epam.preprod.eshop.message.Messages.NO_COMMAND_MESSAGE;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.STRING_VIEW;

public class NoCommand extends Command {

    public NoCommand(FacadeService facadeService, InputInteraction console, ViewController controller) {
        super(facadeService, console, controller);
    }

    @Override
    public void execute() {
        controller.showView(STRING_VIEW, NO_COMMAND_MESSAGE);
        controller.showView(MENU_VIEW, null);
    }
}
