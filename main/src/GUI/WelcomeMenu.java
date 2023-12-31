package GUI;

import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class WelcomeMenu extends JPanel {
    private final SmartMessagingSystem sms;

    public WelcomeMenu(SmartMessagingSystem sms){
        this.sms = sms;
        Frame.lastPanel = this;

        setLayout(new BorderLayout());
        setAlignmentX(CENTER_ALIGNMENT);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel label = new JLabel("Welcome in " + Constants.TITLE);
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
        label.setAlignmentX(CENTER_ALIGNMENT);

        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(label, BorderLayout.CENTER);
        labelPanel.add(Box.createHorizontalGlue());

        add(labelPanel, BorderLayout.CENTER);
        add(createButtonsPanel(), BorderLayout.PAGE_END);
    }

    public JPanel createButtonsPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createButtons());
        panel.add(Box.createRigidArea(new Dimension(Constants.TOTAL_WIDTH, 50)));
        return panel;
    }

    public JPanel createButtons() {
        JPanel btnLine = new JPanel();
        btnLine.setLayout(new BoxLayout(btnLine, BoxLayout.LINE_AXIS));

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> loginAction(true));

        JButton createBtn = new JButton("Register");
        createBtn.addActionListener(e->loginAction(false));

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> System.exit(0));

        btnLine.add(Box.createHorizontalGlue());
        btnLine.add(createBtn);
        btnLine.add(Box.createRigidArea(new Dimension(25, 0)));
        btnLine.add(loginBtn);
        btnLine.add(Box.createRigidArea(new Dimension(25, 0)));
        btnLine.add(closeBtn);
        btnLine.add(Box.createHorizontalGlue());

        return btnLine;
    }

    private void loginAction(boolean alreadyCreated) {
        Frame.frame.remove(this);
        Frame.frame.setJMenuBar(new MenuBar(sms));
        Frame.frame.add(new LoginMenu(sms, alreadyCreated));
        SwingUtilities.updateComponentTreeUI(Frame.frame);
    }
}
