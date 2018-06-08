package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;

public class ShowMenuCommand extends Command {

    public ShowMenuCommand(FacadeService facadeService, InputInteraction console, ViewController view) {
        super(facadeService, console, view);
    }

    @Override
    public void execute() {
        controller.showView(MENU_VIEW, null);
    }

}
