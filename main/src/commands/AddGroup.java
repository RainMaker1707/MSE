package commands;

import java.util.List;

import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Group;

import javax.swing.*;

public class AddGroup extends Command {
    public AddGroup(List<Context> contexts, String command){
        super("addgroup", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(LoggedIn.INSTANCE.get() == null) {
            error("No user logged in");
        } else if (args.size() < 2) {
            error("Provide at least a group name and one contact to add");
        } else {
            String groupName = args.get(0);
            if (LoggedIn.INSTANCE.get().getGroups().getGroup(groupName) == null) {
                error("No group with name " + groupName);
            } else if(LoggedIn.INSTANCE.get().getGroups().getGroups().stream().map(Group::getGroupName).toList().contains(groupName)) {
                Group group = LoggedIn.INSTANCE.get().getGroups().getGroups().stream().filter(g -> g.getGroupName().equals(groupName)).findFirst().orElse(null);
                if (group == null) error("Failed to retrieve the group with name " + groupName);
                boolean isMemberAlreadyInGroup = false;

                for (int i = 1; i < args.size(); i++) {
                    String contactName = args.get(i);

                    // Check if the contact exists in the database
                    Contact contact = DataBase.INSTANCE.getUser(contactName);
                    if (contact == null) {
                        error("No user with name " + contactName);
                        break;
                    }
                    for (Contact member : group.getMembers()) {
                        if (member.getName().equals(contactName)) {
                            isMemberAlreadyInGroup = true;
                            break;
                        }
                    }
                    if (isMemberAlreadyInGroup) {
                        error(contactName + " already in the group");
                    } else {
                        group.addToGroup(contact);
                        feedback("User " + contactName + " is now in the group " + group.getGroupName());
                    }
                }
            } else {
                error(groupName + " is not in your group list");
            }
        }
    }

    @Override
    public JPanel gui() {
        return null;
    }

    @Override
    public void help() {
        
    }
    
    
}
