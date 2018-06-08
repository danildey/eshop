package com.epam.preprod.eshop.command;

import java.util.Map;

public class CommandController {
    private Map<Integer, Command> commands;

    public CommandController(Map<Integer, Command> commands) {
        this.commands = commands;
    }

    public Command getCommand(int commandNumber) {
        if (!commands.containsKey(commandNumber)) {
            return commands.get(10);
        }
        return commands.get(commandNumber);
    }


}
