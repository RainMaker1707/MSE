package GUI;

import database.LoggedIn;
import features.conversation.Group;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class LoggedInMenu extends JPanel {
    private final SmartMessagingSystem sms;

    private final MessagePanel msgPanel;
    public LoggedInMenu(SmartMessagingSystem sms){
        this.sms = sms;
        this.msgPanel = getMessagePanel();
        refresh(false, null);
    }

    public void refresh(boolean groupIsOpen, Group group){
        removeAll();
        Frame.lastPanel = this;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(getGroupPanel());
        if(groupIsOpen){
            System.out.println("OK");
            add(fillMemberPanel(group));
        }
        add(msgPanel);
        add(getContactList());
        Frame.frame.add(this);
        Frame.frame.setTitle(Constants.TITLE + " | " + LoggedIn.INSTANCE.get().getName());
        SwingUtilities.updateComponentTreeUI(Frame.frame);
    }

    private JPanel getContactList() { return new ContactListPanel(sms, msgPanel, this);}
    private JPanel getGroupPanel() {return new GroupPanel(sms, msgPanel, this);}
    private MessagePanel getMessagePanel() {return new MessagePanel(sms, this);}

    public JPanel fillMemberPanel(Group group){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.setMaximumSize(new Dimension(100, Constants.TOTAL_HEIGHT));
        panel.setPreferredSize(new Dimension(100, Constants.TOTAL_HEIGHT));
        panel.setMaximumSize(new Dimension(100, Constants.TOTAL_HEIGHT));

        JLabel label = new JLabel(group.getGroupName() + " members");
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // TODO: fill member
        panel.setBackground(Color.RED);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        return panel;
    }
}
