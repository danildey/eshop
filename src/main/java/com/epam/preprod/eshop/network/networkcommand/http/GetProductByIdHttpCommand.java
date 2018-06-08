package com.epam.preprod.eshop.network.networkcommand.http;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.networkcommand.HttpCommand;
import com.epam.preprod.eshop.network.util.HttpUtil;
import com.epam.preprod.eshop.service.FacadeService;

import java.util.Objects;

public class GetProductByIdHttpCommand extends HttpCommand {

    public GetProductByIdHttpCommand(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    public String execute(Request request) {
        String argument = request.getArgument("item");
        if (Objects.nonNull(argument)){
            Product product = facadeService.getProduct(Long.parseLong(argument));
            if (Objects.nonNull(product)) {
                String json = String.format("{name: %s, price: %d}", product.getName(), product.getPrice());
                return HttpUtil.wrapResponse(json);
            }
        }
        return HttpUtil.wrapResponse("Wrong parameter");
    }
}
