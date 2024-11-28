package commands;

import behaviour.ContextBehavior;
import database.Features;
import database.LoggedIn;
import features.contact.HiddenContact;
import features.contact.Contact;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Hide extends Command {
    public Hide(List<ContextBehavior> contexts, String command){
        super("hide", contexts, command);
    }
    @Override
    public void run() {
        if(!Features.INSTANCE.get("hide").isActivated()) error("Feature hidden is not activated");
        else if(LoggedIn.INSTANCE.get() == null) error("No user logged in");
        else{
            List<String> args = this.getArguments(false);
            for(String arg: args){
                if(!LoggedIn.INSTANCE.get().getContactList().getContacts().stream()
                        .map(Contact::getName).toList().contains(arg))
                    error(LoggedIn.INSTANCE.get() + " has no contact " + arg);
                else{
                    int toHidIndex = LoggedIn.INSTANCE.get().getContactList().getContacts().stream()
                            .map(Contact::getName).toList().indexOf(arg);
                    Contact toHid = LoggedIn.INSTANCE.get().getContactList().getContacts().get(toHidIndex);
                    HiddenContact hidden = new HiddenContact(LoggedIn.INSTANCE.get());
                    hidden.hidContact(toHid);
                    feedback(arg + " has been hidden!");
                }
            }
        }
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }

    @Override
    public void help(){

    }
    
}
