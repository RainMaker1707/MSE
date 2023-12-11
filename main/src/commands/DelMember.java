package commands;

import behaviour.ContextBehavior;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Group;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class DelMember extends Command{
    public DelMember(List<ContextBehavior> contexts, String command) {
        super("delMember", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(LoggedIn.INSTANCE.get() == null){
            error("No user logged in");
            return;
        }
        if(args.size() < 2){
            error("delMember command need two argument: group name and member name");
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
        Contact contact = null;
        for(Contact c: group.getMembers()){
            if(c.getName().equals(args.get(1))) {
                contact = c;
                break;
            }
        }
        if(contact == null){
            error("No member " + args.get(1) + " in group " + group.getGroupName());
            return;
        }
        group.removeToGroup(contact);
        feedback(contact.getName() + " removed from group " + group.getGroupName());
    }

    @Override
    public void help() {

    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }
}
