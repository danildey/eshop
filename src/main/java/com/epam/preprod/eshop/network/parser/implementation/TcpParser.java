package com.epam.preprod.eshop.network.parser.implementation;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.parser.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TcpParser implements RequestParser {
    private final String REGEX_COMMAND = "(\\w+)\\s(\\w+)(\\s+)?(=(\\s+)?(\\d+)?)?(.+)?";
    private final String REGEX_ARGUMENT = ".+=(\\s+)?(\\d+)(.+)?";

    @Override
    public Request parseRequest(BufferedReader in) throws IOException {
        String rowRequest = in.readLine();

        String argument = parseArguments(rowRequest);
        String command = parseCommand(rowRequest);

        Map<String, String> arguments = new HashMap<>();

        if (Objects.nonNull(argument)) {
            String parameter = parseParameter(rowRequest);
            arguments.put(parameter, argument);
        }
        return new Request(rowRequest, command, arguments);
    }


    private String parseParameter(String request) {
        if (request.matches(REGEX_COMMAND)) {
            return request.replaceAll(REGEX_COMMAND, "$2");
        } else {
            return null;
        }
    }

    private String parseCommand(String request) {
        if (request.matches(REGEX_COMMAND)) {
            return request.replaceAll(REGEX_COMMAND, "$2");
        } else {
            return null;
        }
    }

    private String parseArguments(String request) {
        if (request.matches(REGEX_ARGUMENT)) {
            return request.replaceAll(REGEX_ARGUMENT, "$2");
        } else {
            return null;
        }
    }
}
