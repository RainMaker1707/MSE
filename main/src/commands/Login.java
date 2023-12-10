package commands;

import GUI.LoggedInMenu;
import behaviour.ContextBehavior;
import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.contact.Contact;
import features.notification.Notification;
import features.notification.NotificationState;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Login extends Command{
    public Login(List<ContextBehavior> contexts, String command){
        super("login", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(args.size() > 1) error("cannot login multiple users");
        else if(args.isEmpty()) error("need a username to login");
        else{
            String username = args.get(0);
            if(DataBase.INSTANCE.getUser(username) != null){
                LoggedIn.INSTANCE.setLoggedIn(DataBase.INSTANCE.getUser(username));
                LoggedIn.INSTANCE.get().changeStatus();
                feedback("User successfully logged in");
                //checkNotifications(LoggedIn.INSTANCE.get());
            }else error("unknown user " + username + ". Create user before login!");
        }
    }

    // private void checkNotifications(Contact loggedInUser) {
    //     List<Notification> notifications = loggedInUser.getNotifications();

    //     for (Notification notification : notifications) {
    //         if (notification.getReceiver().equals(loggedInUser) && notification.getState() == NotificationState.received) {
    //             System.out.println("Notification: You have received a message from " + notification.getSender().getName());
    //         }
    //     }
    // }

    @Override
    public void help(){

    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return new LoggedInMenu(sms);
    }
}
