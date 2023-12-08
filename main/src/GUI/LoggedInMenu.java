package GUI;

import database.LoggedIn;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;

public class LoggedInMenu extends JPanel {
    private final SmartMessagingSystem sms;

    private final MessagePanel profile;
    public LoggedInMenu(SmartMessagingSystem sms){
        this.sms = sms;
        this.profile = getProfile();
        Frame.lastPanel = this;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(profile);
        add(getContactList());
        Frame.frame.add(this);
        Frame.frame.setTitle(Constants.TITLE + " | " + LoggedIn.INSTANCE.get().getName());
    }

    private JPanel getContactList() { return new ContactListPanel(sms, profile);}
    private MessagePanel getProfile() {return new MessagePanel(sms);}
}
