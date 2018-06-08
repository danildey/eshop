package com.epam.preprod.eshop.network.networkcommand;

import com.epam.preprod.eshop.network.exchange.Request;

import java.io.IOException;

public interface NetworkCommand {
    String execute(Request request) throws IOException;
}
