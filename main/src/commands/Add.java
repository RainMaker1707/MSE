package commands;

import GUI.LoggedInMenu;
import behaviour.ContextBehavior;
import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.contact.Contact;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Add extends Command{
    public Add(List<ContextBehavior> contexts, String command){
        super("add", contexts, command);
    }
    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(LoggedIn.INSTANCE.get() == null) error("No user logged in");
        else if(args.isEmpty()) error("Provide at least one contact to add");
        else{
            for(String arg: args) {
                if(DataBase.INSTANCE.getUser(arg) == null) error("No user with name " + arg);
                else if(LoggedIn.INSTANCE.get().getContactList().getContacts().stream()
                        .map(Contact::getName).toList().contains(arg)) error(arg + " already in your contact list");
                else {
                    LoggedIn.INSTANCE.get().addContact(new Contact(DataBase.INSTANCE.getUser(arg)));
                    DataBase.INSTANCE.getUser(arg).addContact(new Contact(LoggedIn.INSTANCE.get()));
                    feedback("User " + arg + " and " + LoggedIn.INSTANCE.get().getName() + " are now contact");
                }
            }
        }
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return new LoggedInMenu(sms);
    }

    @Override
    public void help(){

    }
}
