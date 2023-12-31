package commands;

import behaviour.ContextBehavior;
import constant.Colors;

import java.util.List;

public class CommandFactory {
    public Command createCommand(String command, List<ContextBehavior> contexts){
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
            case "addgroup" -> new AddGroup(contexts, command);
            case "creategroup" -> new CreateGroup(contexts, command);
            case "groups" -> new GroupCmd(contexts, command);
            case "delgroup" -> new DelGroup(contexts, command); // TODO:
            case "delmember" -> new DelMember(contexts, command); // TODO:
            case "groupmember" -> new GroupMember(contexts, command); // TODO:
            case "hide" -> new Hide(contexts, command);
            case "hidden" -> new Hidden(contexts, command);
            case "register" -> new Register(contexts, command);
            default -> {
                System.out.println(Colors.ANSI_RED + "Command error: " + cmd + " is not a command!" + Colors.currentColor);
                yield null;
            }
        };
    }
}
