package com.epam.preprod.eshop.network.networkcommand.tcp;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.networkcommand.TcpCommand;
import com.epam.preprod.eshop.service.FacadeService;

import java.util.Objects;

public class GetProductByIdTcpCommand extends TcpCommand {


    public GetProductByIdTcpCommand(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    public String execute(Request request) {
        String argument = request.getArgument("item");
        if (Objects.nonNull(argument)){
            Product product = facadeService.getProduct(Long.parseLong(argument));
            if (Objects.nonNull(product)) {
                return String.format("%s | %d", product.getName(), product.getPrice());
            }
        }
        return "Wrong parameter";
    }
}
