package com.epam.preprod.eshop.view.implementation;

import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.view.View;

public class MenuView implements View<Object> {
    DataOutput output;

    public MenuView(DataOutput output) {
        this.output = output;
    }

    @Override
    public void show(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------- Menu : --------------\n");
        sb.append("[1] ShowAllProductsCommand\n");
        sb.append("[2] ShowBasketCommand\n");
        sb.append("[3] ShowLastFiveProductInBasketCommand\n");
        sb.append("[4] PutProductIntoBasketCommand\n");
        sb.append("[5] BuyAllFromBasketCommand\n");
        sb.append("[6] GetOrderByDateCommand\n");
        sb.append("[7] GetOrderByRangeOfDateCommand\n");
        sb.append("[8] CreateProduct\n");
        sb.append("[9] CreateProductWithReflection\n");
        sb.append("[0] ExitCommand\n");
        sb.append("--------------------------------------\n");
        output.print(sb.toString());
    }
}
