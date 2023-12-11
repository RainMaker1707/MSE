package GUI;

import commands.Add;
import commands.MessageCmd;
import commands.Remove;
import database.LoggedIn;
import features.contact.Contact;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

public class ContactListPanel extends JPanel {

    private final SmartMessagingSystem sms;
    private final JTextField field;
    private final MessagePanel msgPanel;

    /**
        DO NOT UPDATE Frame.lastFrame here
    **/
    public ContactListPanel(SmartMessagingSystem sms, MessagePanel msgPanel) {
        this.field = new JTextField(32);
        this.sms = sms;
        this.msgPanel = msgPanel;

        setBackground(Color.LIGHT_GRAY);


        setMinimumSize(new Dimension(100, Constants.TOTAL_HEIGHT));
        setPreferredSize(new Dimension(200, Constants.TOTAL_HEIGHT));
        setMaximumSize(new Dimension(Constants.TOTAL_WIDTH/3, Constants.TOTAL_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        refresh();
    }

    private void refresh(){
        field.setText("");
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(createLabel());
        add(Box.createRigidArea(new Dimension(0, 30)));

        add(fillContactList());

        add(Box.createVerticalGlue());

        add(createFieldPanel());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(createBtnPanel());
        add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private JLabel createLabel(){
        JLabel label = new JLabel("Contacts list");
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
        label.setAlignmentX(CENTER_ALIGNMENT);
        return label;
    }

    private JPanel fillContactList() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setMinimumSize(new Dimension(100, 0));
        panel.setMaximumSize(new Dimension(Constants.TOTAL_WIDTH/3, 300));
        panel.setOpaque(false);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
        innerPanel.setOpaque(false);

        panel.add(Box.createRigidArea(new Dimension(25,0)));
        panel.add(innerPanel);
        panel.add(Box.createRigidArea(new Dimension(25,0)));

        for(Contact contact: LoggedIn.INSTANCE.get().getContactList().getContacts()){
            JLabel label = new JLabel("• "+contact.getName());
            label.setAlignmentX(LEFT_ALIGNMENT);
            label.setAlignmentY(TOP_ALIGNMENT);
            label.addMouseListener(getMouseListener(label, contact));
            innerPanel.add(label);
        }
        return panel;
    }

    private JPanel createFieldPanel(){
        field.setMaximumSize(new Dimension(150, 30));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(25, 0)));
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(25, 0)));
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createBtnPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        createButton(panel);
        panel.setOpaque(false);
        return panel;
    }

    private void createButton(JPanel panel){

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e->addBtnAction());

        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(e->removeBtnAction());

        panel.add(Box.createHorizontalGlue());
        panel.add(addBtn);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(removeBtn);
        panel.add(Box.createHorizontalGlue());
    }

    private void addBtnAction(){
        int oldSize = LoggedIn.INSTANCE.get().getContactList().getContacts().size();
        new Add(sms.getContexts(), "add " + field.getText()).run();
        int newSize = LoggedIn.INSTANCE.get().getContactList().getContacts().size();
        if(oldSize < newSize) {
            this.removeAll();
            this.refresh();
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    private void removeBtnAction(){
        int oldSize = LoggedIn.INSTANCE.get().getContactList().getContacts().size();
        new Remove(sms.getContexts(), "remove " + field.getText()).run();
        int newSize = LoggedIn.INSTANCE.get().getContactList().getContacts().size();
        if(oldSize > newSize) {
            this.removeAll();
            this.refresh();
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    private MouseListener getMouseListener(JLabel label, Contact contact){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MessageCmd(sms.getContexts(), "messages " + contact.getName()).run();
                msgPanel.showMessages(contact);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.BLUE);
                SwingUtilities.updateComponentTreeUI(label);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.BLACK);
                SwingUtilities.updateComponentTreeUI(label);
            }
        };
    }
}
