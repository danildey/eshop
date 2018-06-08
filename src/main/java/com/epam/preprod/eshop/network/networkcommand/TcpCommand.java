package com.epam.preprod.eshop.network.networkcommand;

import com.epam.preprod.eshop.service.FacadeService;

public abstract class TcpCommand implements NetworkCommand {
    protected FacadeService facadeService;

    public TcpCommand(FacadeService facadeService) {
        this.facadeService = facadeService;
    }

}
