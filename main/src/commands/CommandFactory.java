package commands;

import constant.Colors;
import context.Context;

import java.util.List;

public class CommandFactory {
    public Command createCommand(String command, List<Context> contexts){
        // TODO: add features in place of null;
        String cmd = command.split(" ")[0];
        switch(cmd){
            case "activate":
                return new Activate(cmd, contexts, null, command);
            case "deactivate":
                return new Deactivate(cmd, contexts, null, command);
            default:
                System.out.println(Colors.ANSI_RED + "Command error: " + cmd + " is not a command!" + Colors.ANSI_RESET);
                return null;
        }
    }
}
