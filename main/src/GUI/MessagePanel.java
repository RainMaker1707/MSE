package GUI;

import commands.MessageCmd;
import commands.Send;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Conversation;
import features.conversation.Group;
import features.message.Message;
import features.message.TextMessage;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class MessagePanel extends JPanel {

    private final JTextField field;
    private final SmartMessagingSystem sms;


    public MessagePanel(SmartMessagingSystem sms) {
        // DO NOT UPDATE Frame.lastFrame here
        setLayout(new BorderLayout());
        this.field = new JTextField(32);
        this.sms = sms;
        refresh();
    }

    private void refresh(){
        JLabel label = new JLabel("LoggedIn as " + LoggedIn.INSTANCE.get().getName());
        underlineLabel(label);
        add(centerLabel(label));
    }

    public void showMessages(Contact contact) {
        removeAll();
        add(createInputSend(contact), BorderLayout.PAGE_END);
        SwingUtilities.updateComponentTreeUI(this);
        for(Conversation conv:LoggedIn.INSTANCE.get().getConversations()){
            if(!(conv instanceof Group)) {
                if (conv.getContact1().equals(contact) || conv.getContact2().equals(contact)) {
                    showMessages(conv);
                    break;
                }
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void groupLayer(Group group) {
        removeAll();
        add(createInputSend(group), BorderLayout.PAGE_END);
        SwingUtilities.updateComponentTreeUI(this);
        for(Group grp: LoggedIn.INSTANCE.get().getGroups().getGroups()){
            if (grp.getGroupName().equals(group.getGroupName())) {
                showMessages(group);
                break;
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    public JPanel getMessagesPanel(Conversation conversation, boolean isGroup){
        if(isGroup && conversation instanceof Group group) groupLayer(group);
        field.setText("");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        boolean flag = false;
        for(Message msg: conversation.getMessages()){
            flag =true;
            if(msg instanceof TextMessage m){
                JPanel msgPanel = new JPanel();
                msgPanel.setLayout(new BoxLayout(msgPanel, BoxLayout.LINE_AXIS));
                JLabel content = new JLabel(m.getContent());
                if(m.getSender().equals(LoggedIn.INSTANCE.get())){
                    msgPanel.add(Box.createHorizontalGlue());
                    content.setForeground(Color.BLUE);
                    msgPanel.add(content);
                }else{
                    content.setForeground(Color.GREEN);
                    msgPanel.add(content);
                    msgPanel.add(Box.createHorizontalGlue());
                }
                panel.add(msgPanel);
            }
        }
        if(!flag) {
            JLabel label = new JLabel("No messages yet");
            panel.add(Box.createVerticalGlue());
            panel.add(centerLabel(label));
            panel.add(Box.createVerticalGlue());
        }
        return panel;
    }

    public void showMessages(Conversation conversation){
        add(getMessagesPanel(conversation, false));
    }

    private JPanel createInputSend(Contact contact){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(15, 30)));
        panel.add(field);
        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(e->sendBtnAction(contact));
        panel.add(sendBtn);
        panel.add(Box.createRigidArea(new Dimension(15, 30)));
        return panel;
    }

    private JPanel createInputSend(Group group){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(15, 30)));
        panel.add(field);
        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(e->sendBtnAction(group));
        panel.add(sendBtn);
        panel.add(Box.createRigidArea(new Dimension(15, 30)));
        return panel;
    }

    private void sendBtnAction(Contact contact){
        new Send(sms.getContexts(), "send " + contact.getName() + " " + field.getText()).run();
        showMessages(contact);
    }

    private void sendBtnAction(Group group){
        new Send(sms.getContexts(), "send " + group.getGroupName() + " " + field.getText()).run();
        add(getMessagesPanel(group, true));
    }

    private JPanel centerLabel(JLabel label){
        label.setAlignmentX(CENTER_ALIGNMENT);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(label);
        panel.add(Box.createHorizontalGlue());
        return panel;
    }

    private void underlineLabel(JLabel label){
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
        label.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void showGroup(Group group){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        JPanel contactInGroup = new JPanel();
        contactInGroup.setLayout(new BoxLayout(contactInGroup, BoxLayout.PAGE_AXIS));

        contactInGroup.setMinimumSize(new Dimension(50, Constants.TOTAL_HEIGHT));
        contactInGroup.setPreferredSize(new Dimension(100, Constants.TOTAL_HEIGHT));
        contactInGroup.setMaximumSize(new Dimension(Constants.TOTAL_WIDTH/6, Constants.TOTAL_HEIGHT));

        fillMemberPanel(contactInGroup, group);

        panel.add(Box.createRigidArea(new Dimension(250, 0)));
        panel.add(contactInGroup);
        panel.add(getMessagesPanel(group, true));
        add(panel);
    }

    public void fillMemberPanel(JPanel panel, Group group){
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
    }
}
