package commands;

import behaviour.ContextBehavior;
import database.LoggedIn;
import features.contact.Contact;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Hidden extends Command{
    public Hidden(List<ContextBehavior> contexts, String command) {
        super("hidden", contexts, command);
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
        List<Contact> contacts = user.getContactList().getHiddenContact();
        StringBuilder text = new StringBuilder();
        text.append("[");
        for(Contact c: contacts){
            text.append(c.getName());
            text.append(", ");
        }
        if(text.length()>2){
            text.delete(text.length()-2, text.length());
            text.append("]");
            feedback(user.getName() + "'s hidden contacts: " + text);
        }else feedback(user.getName() + " has no hidden contact yet");

    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }

    @Override
    public void help() {

    }
}
