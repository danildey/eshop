package com.epam.preprod.eshop.network.exchange;

import java.util.Map;

public final class Request {
    private final String rowRequest;
    private final String command;
    private final Map<String, String> arguments;

    @Override
    public String toString() {
        return "Request{" +
                "rowRequest='" + rowRequest + '\'' +
                ", command='" + command + '\'' +
                ", arguments=" + arguments +
                '}';
    }

    public String getRowRequest() {
        return rowRequest;
    }

    public String getCommand() {
        return command;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public String getArgument(String argument) {
        return arguments.get(argument);
    }

    public Request(String rowRequest, String command, Map<String, String> arguments) {
        this.rowRequest = rowRequest;
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (rowRequest != null ? !rowRequest.equals(request.rowRequest) : request.rowRequest != null) return false;
        if (command != null ? !command.equals(request.command) : request.command != null) return false;
        return arguments != null ? arguments.equals(request.arguments) : request.arguments == null;
    }

    @Override
    public int hashCode() {
        int result = rowRequest != null ? rowRequest.hashCode() : 0;
        result = 31 * result + (command != null ? command.hashCode() : 0);
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        return result;
    }
}
