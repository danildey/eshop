package com.epam.preprod.eshop.network.networkcommand.tcp;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.networkcommand.TcpCommand;
import com.epam.preprod.eshop.service.FacadeService;

import java.io.IOException;

public class ErrorTcpCommand extends TcpCommand {

    public ErrorTcpCommand(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    public String execute(Request request) throws IOException {
        return "Wrong Command";
    }
}
