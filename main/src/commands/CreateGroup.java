package commands;

import java.util.List;

import behaviour.ContextBehavior;
import database.DataBase;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Group;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;

public class CreateGroup extends Command {
    public CreateGroup(List<ContextBehavior> contexts, String command){
        super("creategroup", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if (LoggedIn.INSTANCE.get() == null) {
            error("No user logged in");
        } else if (args.size() < 2) {
            error("Provide a group name and at least one contact to create a group");
        } else {
            String groupName = args.get(0);
            Contact member = LoggedIn.INSTANCE.get().getContactList().getContact(args.get(1));
            if (member == null) {
                error("Contact " + args.get(1) + " not found in your contact list");
            } else if (DataBase.INSTANCE.getGroup(groupName) != null) {
                error("Group with name " + groupName + " already exists");
            } else {
                Group newGroup = new Group(groupName, LoggedIn.INSTANCE.get(), member);

                for (int i = 1; i < args.size(); i++) {
                    String contactName = args.get(i);
                    Contact contact = DataBase.INSTANCE.getUser(contactName);

                    if (contact == null) {
                        error("No user with name " + contactName);
                    } else {
                        newGroup.addToGroup(contact);
                    }
                }

                // Add the new group to the user's profile
                LoggedIn.INSTANCE.get().addGroup(newGroup);
                feedback("Group " + groupName + " created successfully");
            }
        }
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }

    @Override
    public void help() {
        
    }
    
}
