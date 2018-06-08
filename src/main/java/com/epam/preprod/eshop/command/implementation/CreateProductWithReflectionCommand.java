package com.epam.preprod.eshop.command.implementation;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.tools.productcreator.reflectioncreator.ReflectionProductCreator;
import com.epam.preprod.eshop.view.ViewCommands;
import com.epam.preprod.eshop.view.ViewController;

import java.util.Objects;

public class CreateProductWithReflectionCommand extends Command {
    private ReflectionProductCreator reflectionCreator;


    public CreateProductWithReflectionCommand(FacadeService facadeService, InputInteraction console, ViewController controller,
                                              ReflectionProductCreator creator) {
        super(facadeService, console, controller);
        this.reflectionCreator = creator;
    }

    @Override
    public void execute() {
        Product product = reflectionCreator.create();
        if (Objects.nonNull(product)) {
            facadeService.addProduct(product);
        }
        controller.showView(ViewCommands.MENU_VIEW, null);
    }
}
