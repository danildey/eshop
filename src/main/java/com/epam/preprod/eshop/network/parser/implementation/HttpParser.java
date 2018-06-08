package com.epam.preprod.eshop.network.parser.implementation;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.parser.RequestParser;
import com.epam.preprod.eshop.network.util.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpParser implements RequestParser {
    private static final String REGEX_ARGUMENT = "(.+)?\\/shop\\/(\\w+)(.+)?=(\\d+)(.+)?";
    private static final String REGEX_COMMAND = "(.+)?\\/shop\\/(\\w+)(.+)?";

    @Override
    public Request parseRequest(BufferedReader in) throws IOException {
        String rowRequest = HttpUtil.readHeaders(in);
        String uri = HttpUtil.getURIFromRequest(rowRequest);

        String command = parseCommand(uri);
        String argument = parseArgument(uri);
        Map<String, String> arguments = new HashMap<>();

        if (Objects.nonNull(argument)) {
            arguments.put(command, argument);
        }
        return new Request(rowRequest, command, arguments);
    }

    private String parseArgument(String uri) {
        if (uri.matches(REGEX_ARGUMENT)) {
            return uri.replaceAll(REGEX_ARGUMENT, "$4");
        } else {
            return null;
        }
    }

    private String parseCommand(String uri) {
        if (uri.matches(REGEX_COMMAND)) {
            return uri.replaceAll(REGEX_COMMAND, "$2");
        } else {
            return null;
        }
    }
}
