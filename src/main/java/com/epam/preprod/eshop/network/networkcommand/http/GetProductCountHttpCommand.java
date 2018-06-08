package com.epam.preprod.eshop.network.networkcommand.http;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.networkcommand.HttpCommand;
import com.epam.preprod.eshop.network.util.HttpUtil;
import com.epam.preprod.eshop.service.FacadeService;

public class GetProductCountHttpCommand extends HttpCommand {

    public GetProductCountHttpCommand(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    public String execute(Request request) {
        int count = facadeService.getAllProducts().size();
        String json = String.format("{count: %d}", count);
        return HttpUtil.wrapResponse(json);
    }
}
