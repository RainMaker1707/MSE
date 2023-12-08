package commands;

import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.contact.Contact;
import features.profile.Profile;

import javax.swing.*;
import java.util.List;

public class Remove extends Command{
    public Remove(List<Context> contexts, String command){
        super("remove", contexts, command);
    }
    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(LoggedIn.INSTANCE.get() == null) error("No user logged in");
        else if(args.isEmpty()) error("Provide at least one contact to remove");
        else{
            for(String arg: args) {
                if(DataBase.INSTANCE.getUser(arg) == null) error("No user with name " + arg);
                else if(!LoggedIn.INSTANCE.get().getContactList().getContacts().stream()
                        .map(Contact::getName).toList().contains(arg)) error(arg + " is not in your contact list");
                else {
                    Profile user = LoggedIn.INSTANCE.get();
                    List<Contact> contacts = user.getContactList().getContacts();
                    int index = contacts.stream().map(Contact::getName).toList().indexOf(arg);
                    user.removeContact(contacts.get(index));
                    feedback("User " + arg + " has been removed from "
                            + LoggedIn.INSTANCE.get().getName() + " contacts list"
                    );
                }
            }
        }
    }

    @Override
    public JPanel gui() {
        return null;
    }

    @Override
    public void help(){

    }
}
