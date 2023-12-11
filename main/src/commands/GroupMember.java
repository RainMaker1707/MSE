package commands;

import behaviour.ContextBehavior;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Group;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class GroupMember extends Command{
    public GroupMember(List<ContextBehavior> contexts, String command) {
        super("groupMember", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(LoggedIn.INSTANCE.get() == null){
            error("No user logged in");
            return;
        }
        if(args.isEmpty()){
            error("groupMember command need one argument: group name");
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
        StringBuilder text = new StringBuilder();
        text.append("[");
        for(Contact contact: group.getMembers()){
            if(contact.getName().equals(LoggedIn.INSTANCE.get().getName())) continue;
            text.append(contact.getName());
            text.append(", ");
        }
        if(text.length()>2){
            text.delete(text.length()-2, text.length());
            text.append("]");
            feedback(group.getGroupName() + ": " + text);
        }else feedback(group.getGroupName() + " has no member yet (except you)");
    }

    @Override
    public void help() {

    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }
}
