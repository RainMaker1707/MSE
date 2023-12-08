package commands;

import context.Context;
import database.LoggedIn;
import features.contact.Contact;

import javax.swing.*;
import java.util.List;

public class ContactCmd extends Command {
    public ContactCmd(List<Context> contexts, String command) {
        super("contacts", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(!args.isEmpty()){
            error("No arguments needed!");
            return;
        }
        Contact user = LoggedIn.INSTANCE.get();
        if(user == null){
            error("No user logged in!");
            return;
        }
        List<Contact> contacts = user.getContactList().getContacts();
        StringBuilder text = new StringBuilder();
        text.append("[");
        for(Contact c: contacts){
            text.append(c.getName());
            text.append(", ");
        }
        if(text.length()>2){
            text.delete(text.length()-2, text.length());
            text.append("]");
            feedback(user.getName() + "'s contacts: " + text);
        }else feedback(user.getName() + " has no contact yet");

    }

    @Override
    public JPanel gui() {
        return null;
    }

    @Override
    public void help() {

    }
}
