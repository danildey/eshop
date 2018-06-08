package com.epam.preprod.eshop.network.networkcommand.tcp;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.networkcommand.TcpCommand;
import com.epam.preprod.eshop.service.FacadeService;

public class GetProductCountTcpCommand extends TcpCommand {

    public GetProductCountTcpCommand(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    public String execute(Request request) {
        int count = facadeService.getAllProducts().size();
        return String.valueOf(count);
    }
}
