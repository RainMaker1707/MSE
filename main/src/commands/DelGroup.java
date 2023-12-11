package commands;

import GUI.LoginMenu;
import behaviour.ContextBehavior;
import database.LoggedIn;
import features.conversation.Group;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class DelGroup extends Command{
    public DelGroup(List<ContextBehavior> contexts, String command) {
        super("delGroup", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(LoggedIn.INSTANCE.get() == null){
            error("No user logged in");
            return;
        }
        if(args.isEmpty()){
            error("delGroup command need one argument: group name");
            return;
        }
        if(LoggedIn.INSTANCE.get().getGroups().getGroups().isEmpty()){
            error("User " +  LoggedIn.INSTANCE.get().getName() + " has no group yet");
            return;
        }
        Group group = null;
        for(Group g: LoggedIn.INSTANCE.get().getGroups().getGroups()){
            if(g.getGroupName().equals(args.get(0))){
                group = g;
                break;
            }
        }
        if(group == null){
            error("No group with name " + args.get(0) + " found in your group list");
            return;
        }
        if(group.delete(LoggedIn.INSTANCE.get())) feedback("Delete group " + group.getGroupName());
        else error("You have not enough right to delete this group");
    }

    @Override
    public void help() {

    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return new LoginMenu(sms);
    }
}
