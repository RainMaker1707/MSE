package GUI;

import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;

public class LoggedInMenu extends JPanel {
    private final SmartMessagingSystem sms;

    public LoggedInMenu(SmartMessagingSystem sms){
        this.sms = sms;
        Frame.lastPanel = this;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(getProfile());
        add(getContactList());
        Frame.frame.add(this);
    }

    private JPanel getContactList() { return new ContactListMenu(sms);}
    private JPanel getProfile() {return new ProfileMenu(sms);}
}
