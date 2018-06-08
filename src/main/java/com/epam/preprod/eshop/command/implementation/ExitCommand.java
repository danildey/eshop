package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.tools.storage.StorageTool;
import com.epam.preprod.eshop.view.ViewController;

import static com.epam.preprod.eshop.message.Messages.EXIT_MESSAGE;
import static com.epam.preprod.eshop.view.ViewCommands.STRING_VIEW;

public class ExitCommand extends Command {
    private StorageTool storageTool;
    private final String PRODUCT_PATH = "src/main/resources/Products.txt";

    public ExitCommand(FacadeService facadeService, InputInteraction console, ViewController view, StorageTool storageTool) {
        super(facadeService, console, view);
        this.storageTool = storageTool;
    }

    @Override
    public void execute() {
        storageTool.saveState(facadeService.getAllProducts(), PRODUCT_PATH);
        controller.showView(STRING_VIEW, EXIT_MESSAGE);
    }
}
