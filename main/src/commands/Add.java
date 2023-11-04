package commands;

import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.Feature;
import features.contact.Contact;

import java.util.List;

public class Add extends Command{
    public Add(List<Context> contexts, String command){
        super("add", contexts, command);
    }
    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(LoggedIn.INSTANCE.getProfile() == null) error("No user logged in");
        else if(args.isEmpty()) error("Provide at least one contact to add");
        else{
            for(String arg: args) {
                if(DataBase.INSTANCE.getUser(arg) == null) error("No user with name " + arg);
                else if(LoggedIn.INSTANCE.getProfile().getContactList().getContacts().stream()
                        .map(Contact::getName).toList().contains(arg)) error(arg + " already in your contact list");
                else {
                    LoggedIn.INSTANCE.getProfile().addContact(new Contact(DataBase.INSTANCE.getUser(arg)));
                    feedback("User " + arg + " has been added to "
                            + LoggedIn.INSTANCE.getProfile().getName() + " contacts list"
                    );
                }
            }

        }
    }

    @Override
    public void help(){

    }
}
