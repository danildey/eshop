package com.epam.preprod.eshop.network.networkcommand;

import java.util.Map;

public class NetworkCommandContainer<T extends NetworkCommand> {
    private Map<String, T> commands;

    public NetworkCommandContainer(Map<String, T> commands) {
        this.commands = commands;
    }

    public T getCommand(String command) {
        if (!commands.containsKey(command)) {
            return commands.get("Error");
        }
        return commands.get(command);
    }
}
