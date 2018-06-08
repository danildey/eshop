package com.epam.preprod.eshop.network.networkcommand;

import com.epam.preprod.eshop.service.FacadeService;

public abstract class HttpCommand implements NetworkCommand {
    protected FacadeService facadeService;

    public HttpCommand(FacadeService facadeService) {
        this.facadeService = facadeService;
    }
}
