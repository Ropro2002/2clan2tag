package com.robdragon234.clantags.impl.managers;

import com.robdragon234.clantags.api.command.Command;
import com.robdragon234.clantags.api.util.ChatUtil;
import com.robdragon234.clantags.impl.command.InfoCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<Command> commandList = new ArrayList<>();
    private String prefix = "!";

    public CommandManager() {
        commandList.add(new InfoCommand());
    }

    public void executeCommand(String input) {
        String commandName = input.contains(" ") ? input.split(" ")[0] : input;
        Command command = getCommand(commandName);
        if(command == null) {
            ChatUtil.message("Command not found");
            return;
        }
        String[] args = input.contains(" ") ? input.substring(input.indexOf(" ") + 1).split(" ") : new String[0];
        command.call(args);
    }

    public Command getCommand(String command) {
        for(Command command2 : getCommandList()) {
            if(command2.getName().equalsIgnoreCase(command)) return command2;
        }
        return null;
    }

    public String getPrefix() { return this.prefix; }

    public void setPrefix(String prefix) { this.prefix = prefix; }

    public List<Command> getCommandList() {
        return this.commandList;
    }
}
