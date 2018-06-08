package com.epam.preprod.eshop.command;

import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.view.ViewController;

public abstract class Command {
    protected InputInteraction console;
    protected ViewController controller;
    protected FacadeService facadeService;

    public Command(FacadeService facadeService, InputInteraction console, ViewController controller) {
        this.console = console;
        this.controller = controller;
        this.facadeService = facadeService;
    }

    public abstract void execute();
}
