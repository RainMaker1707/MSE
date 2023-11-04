package commands;

import constant.Colors;
import context.Context;

import java.util.List;

public class CommandFactory {
    public Command createCommand(String command, List<Context> contexts){
        // TODO: add features in place of null
        // TODO: finish commands
        String cmd = command.split(" ")[0];
        return switch (cmd.toLowerCase()) {
            case "activate" -> new Activate(contexts,  command);
            case "deactivate" -> new Deactivate(contexts,  command);
            case "add" -> new Add(contexts, command);
            case "block" -> {
                System.out.println("BLOCK command triggered");
                yield null;
            }
            case "remove" -> {
                System.out.println("REMOVE command triggered");
                yield null;
            }
            case "login" -> new Login(contexts, command);
            case "logout" -> new Logout(contexts, command);
            case "send" -> {
                System.out.println("SEND command triggered");
                yield null;
            }
            case "new" -> {
                System.out.println("NEW command triggered");
                yield null;
            }
            default -> {
                System.out.println(Colors.ANSI_RED + "Command error: " + cmd + " is not a command!" + Colors.ANSI_RESET);
                yield null;
            }
        };
    }
}
