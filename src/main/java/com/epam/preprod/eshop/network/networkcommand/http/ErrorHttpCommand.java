package com.epam.preprod.eshop.network.networkcommand.http;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.networkcommand.HttpCommand;
import com.epam.preprod.eshop.network.util.HttpUtil;
import com.epam.preprod.eshop.service.FacadeService;

import java.io.IOException;

public class ErrorHttpCommand extends HttpCommand {

    public ErrorHttpCommand(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    public String execute(Request request) throws IOException {
        return HttpUtil.wrapResponse("Wrong Command");
    }
}
