package GUI;

import commands.Login;
import database.LoggedIn;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.awt.*;

public class LoginMenu extends JPanel {
    private final SmartMessagingSystem sms;

    private final JTextField field;

    public LoginMenu(SmartMessagingSystem sms){
        this.sms = sms;
        this. field = new JTextField(32);
        Frame.lastPanel = this;

        setLayout(new BorderLayout());
        setAlignmentX(CENTER_ALIGNMENT);
        add(createPanel(), BorderLayout.CENTER);
    }


    private JPanel createPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(panelLabel());
        panel.add(Box.createRigidArea(new Dimension(Constants.TOTAL_WIDTH, 30)));
        panel.add(inputField());
        panel.add(Box.createRigidArea(new Dimension(Constants.TOTAL_WIDTH, 25)));
        panel.add(buttonPanel());
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel panelLabel(){
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));
        labelPanel.add(Box.createHorizontalGlue());
        JLabel label = new JLabel("Please login to use the app");
        labelPanel.add(label);
        labelPanel.add(Box.createHorizontalGlue());
        return labelPanel;
    }

    private JPanel inputField(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setMaximumSize(new Dimension(Constants.TOTAL_WIDTH, 25));
        panel.add(Box.createRigidArea(new Dimension(Constants.TOTAL_WIDTH/3, 0)));
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(Constants.TOTAL_WIDTH/3, 0)));
        return panel;
    }

    private JPanel buttonPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> loginBtnAction());
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> cancelBtnAction());
        panel.add(Box.createHorizontalGlue());
        panel.add(loginBtn);
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
        panel.add(cancelBtn);
        panel.add(Box.createHorizontalGlue());
        return panel;
    }

    private void cancelBtnAction() {
        Frame.frame.remove(this);
        Frame.frame.add(new WelcomeMenu(sms));
        SwingUtilities.updateComponentTreeUI(Frame.frame);
    }

    private void loginBtnAction() {
        new Login(sms.getContexts(), "login " + field.getText()).run();
        if(LoggedIn.INSTANCE.get() != null){
            Frame.frame.remove(this);
            Frame.frame.setJMenuBar(new MenuBar(sms));
            Frame.frame.add(new LoggedInMenu(sms));
            SwingUtilities.updateComponentTreeUI(Frame.frame);
        }
        // TODO: else error message in GUI
    }
}
