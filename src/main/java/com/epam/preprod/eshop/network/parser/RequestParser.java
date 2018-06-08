package com.epam.preprod.eshop.network.parser;

import com.epam.preprod.eshop.network.exchange.Request;

import java.io.BufferedReader;
import java.io.IOException;

public interface RequestParser {
    Request parseRequest(BufferedReader in) throws IOException;
}
