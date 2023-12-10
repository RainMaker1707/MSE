package GUI;

import database.LoggedIn;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;

public class LoggedInMenu extends JPanel {
    private final SmartMessagingSystem sms;

    private final MessagePanel msgPanel;
    public LoggedInMenu(SmartMessagingSystem sms){
        this.sms = sms;
        this.msgPanel = getMessagePanel();
        Frame.lastPanel = this;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(getGroupPanel());
        add(msgPanel);
        add(getContactList());
        Frame.frame.add(this);
        Frame.frame.setTitle(Constants.TITLE + " | " + LoggedIn.INSTANCE.get().getName());
    }

    private JPanel getContactList() { return new ContactListPanel(sms, msgPanel);}
    private JPanel getGroupPanel() {return new GroupPanel(sms, msgPanel);}
    private MessagePanel getMessagePanel() {return new MessagePanel(sms);}
}
