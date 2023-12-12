package commands;

import behaviour.ContextBehavior;
import constant.Colors;
import database.LoggedIn;
import database.DataBase;
import smartMessagingSystem.SmartMessagingSystem;
import database.Features;
import features.contact.Contact;
import features.profile.Profile;

import javax.swing.*;
import java.util.List;

public class Register extends Command {
    public Register(List<ContextBehavior> contexts, String command){
        super("register", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(args.size() > 1) error("cannot register multiple users");
        else if(args.isEmpty()) error("need a username to register");
        else{
            String username = args.get(0);
            if(DataBase.INSTANCE.getUser(username) != null){
                error("user already exists. Use another username !");
            } else {
                Contact newUser = new Contact(username, null);
                DataBase.INSTANCE.setUser(username, newUser);
                feedback(username + " has been successfully registered");
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
