package commands;

import GUI.LoggedInMenu;
import behaviour.ContextBehavior;
import database.DataBase;
import database.LoggedIn;
import smartMessagingSystem.SmartMessagingSystem;
import features.notification.Notification;
import behaviour.FeatureBehavior;
import database.Features;
import features.contact.Contact;
import features.contact.HiddenContact;
import features.conversation.Group;

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
                checkNotifications(LoggedIn.INSTANCE.get());
            }else error("unknown user " + username + ". Create user before login!");
        }
    }

    private void checkNotifications(Contact loggedInUser) {
        List<Notification> notifications = loggedInUser.getNotifications();
        FeatureBehavior behaviorSilent = Features.INSTANCE.get("silent");
        FeatureBehavior behaviorSound = Features.INSTANCE.get("sound");
        FeatureBehavior behaviorVibrant = Features.INSTANCE.get("vibrant");

        for (Notification notification : notifications) {
            if (notification.isGroup()) {
                Group group = (Group) notification.getConversation();
                List<Contact> members = group.getMembers();
                for (Contact member : members) {
                    if (!isHidden(member, loggedInUser) && member.equals(loggedInUser) && notification.getState() == "sending") {
                        notification.receive();
                        behaviorSilent.run();
                        behaviorSound.run();
                        behaviorVibrant.run();
                        feedback("Notification: You have received a message from " + group.getGroupName() + "group");
                    }
                }
            } else {
                if (!isHidden(notification.getSender(), loggedInUser) && notification.getReceiver().equals(loggedInUser) && notification.getState() == "sending") {
                    notification.receive();
                    behaviorSilent.run();
                    behaviorSound.run();
                    behaviorVibrant.run();
                    feedback("Notification: You have received a message from " + notification.getSender().getName());
                }
            }
            
        }
    }


    private boolean isHidden(Contact contact, Contact loggedInUser) {
        List<Contact> hiddenContacts = loggedInUser.getHiddenContacts();
        if (hiddenContacts != null && !hiddenContacts.isEmpty()) {
            return hiddenContacts.contains(contact);
        }
        return false;
    }

    @Override
    public void help(){

    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        if(LoggedIn.INSTANCE.get() != null) return new LoggedInMenu(sms);
        else return null;
    }
}
