package commands;

import constant.Colors;
import context.Context;

import java.util.List;

public class CommandFactory {
    public Command createCommand(String command, List<Context> contexts){
        /* TODO:
            add features in place of null
            finish commands
        */
        String cmd = command.split(" ")[0];
        return switch (cmd.toLowerCase()) {
            case "activate" -> new Activate(contexts,  command);
            case "deactivate" -> new Deactivate(contexts,  command);
            case "contacts" -> new ContactCmd(contexts, command);
            case "login" -> new Login(contexts, command);
            case "logout" -> new Logout(contexts, command);
            case "add" -> new Add(contexts, command);
            case "remove" -> new Remove(contexts, command);
            case "block" -> new Block(contexts, command);
            case "send" -> new Send(contexts, command);
            case "conversations" -> new ConversationCmd(contexts, command);
            case "messages" -> new MessageCmd(contexts, command);
            default -> {
                System.out.println(Colors.ANSI_RED + "Command error: " + cmd + " is not a command!" + Colors.currentColor);
                yield null;
            }
        };
    }
}
