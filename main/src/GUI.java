import commands.Activate;
import commands.Add;
import commands.Command;
import commands.Login;
import constant.Colors;
import database.Features;
import database.LoggedIn;
import features.contact.Contact;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import java.util.List;


public class GUI extends JFrame{

    final int TOTAL_HEIGHT = 540;
    final int TOTAL_WIDTH = 960;
    final String TITLE = "SmartMessagingSystem";
    SmartMessagingSystem sms;
    JPanel mainPanel;
    Color backgroundColor;
    Color textColor;

    public GUI(){
        sms = new SmartMessagingSystem();

        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(TOTAL_WIDTH, TOTAL_HEIGHT);

        // Create the main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        // Set the menu bar
        createWelcomeMenu();
    }

    private void createWelcomeMenu(){
        setJMenuBar(createMenuBar());
        mainPanel.setOpaque(false);
        if(sms.getFeatures().get("dark").isActivated()) {
            backgroundColor = Color.DARK_GRAY;
            textColor = Color.WHITE;
        } else {
            backgroundColor = Color.WHITE;
            textColor = Color.BLACK;
        }
        getContentPane().setBackground(backgroundColor);
        // Create components for the main panel
        JLabel label = new JLabel("Welcome in SmartMessagingSystem");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton loginButton = new JButton("Login");
        loginButton.setSize(320, 100);
        loginButton.addActionListener(e->MenuLogin(mainPanel, label, buttonPanel));

        JButton closeButton = new JButton("Close");
        closeButton.setSize(320, 100);
        closeButton.addActionListener(e->System.exit(0));

        // Add components to the main panel
        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(loginButton, BorderLayout.WEST);
        buttonPanel.add(closeButton, BorderLayout.EAST);

        // Add the main panel to the frame
        add(mainPanel);
        // Set the frame to be visible
        setVisible(true);
    }

    private void reset(){
        setTitle(TITLE);
        mainPanel.removeAll();
        createWelcomeMenu();
    }

    private void MenuLogin(JPanel panel, JLabel label, JPanel panelButton){
        panel.remove(panelButton);
        label.setText("");

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10,150,10,150));
        panel.add(innerPanel);

        JTextField logInput = new JTextField(32);
        logInput.setMaximumSize(new Dimension(TOTAL_WIDTH/4, 30));
        logInput.setBorder(new LineBorder(Color.BLACK, 2));
        innerPanel.add(logInput);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(TOTAL_WIDTH/4, 50));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        innerPanel.add(buttonPanel);

        JButton login = new JButton("LoggIn");
        login.addActionListener(e->{
            Command log = new Login(sms.getContexts(), "login " + logInput.getText());
            log.run();
            if(LoggedIn.INSTANCE.isLoggedIn()) loggedInScreen(label);
            else System.out.println("Unknown user");
        });



        buttonPanel.add(login);
        buttonPanel.add(Box.createHorizontalGlue());

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e->reset());
        buttonPanel.add(cancel);
    }


    private void loggedInScreen(JLabel label){
        setTitle(TITLE + " | " + LoggedIn.INSTANCE.get().getName());
        label.setText("LoggedIn as " + LoggedIn.INSTANCE.get().getName());
        mainPanel.removeAll();
        JPanel contactPanel = new JPanel();
        contactPanel.setBackground(Color.lightGray);
        createContactList(contactPanel);
        JPanel menuPanel = new JPanel();
        menuPanel.add(label);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        mainPanel.add(contactPanel, BorderLayout.EAST);
        SwingUtilities.updateComponentTreeUI(this);
        this.getJMenuBar().add(createSettingsMenu());
    }


    private void createContactList(JPanel contactPanel) {
        JLabel subLabel = new JLabel("Contacts List");
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactPanel.add(subLabel);
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.PAGE_AXIS));
        contactPanel.add(Box.createRigidArea(new Dimension(100, 30)));

        List<Contact> contacts = LoggedIn.INSTANCE.get().getContactList().getContacts();
        for(Contact contact: contacts){
            JLabel label = new JLabel("• "+contact.getName() +"\n");
            //label.setBorder(new LineBorder(Color.BLUE, 1));
            label.setBorder(new CompoundBorder(null, new EmptyBorder(10,10,10,10)));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            contactPanel.add(label);
        }
        JTextField contactSearch = new JTextField(32);
        contactSearch.setBorder(new LineBorder(Color.BLACK, 1));
        contactSearch.setMaximumSize(new Dimension(150, 50));
        contactPanel.add(Box.createVerticalGlue());
        contactPanel.add(contactSearch);

        JPanel spacer = new JPanel();
        spacer.setMaximumSize(new Dimension(TOTAL_WIDTH, 50));
        spacer.setOpaque(false);
        contactPanel.add(spacer);

        JButton addContact = new JButton("Add Contact");
        addContact.addActionListener(e->addContactAction(contactPanel, contactSearch));
        addContact.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactPanel.add(addContact);
        contactPanel.add(Box.createRigidArea(new Dimension(100, 20)));
    }


    private void addContactAction(JPanel panel, JTextField field) {
        System.out.println("Add contact clicked: " + field.getText());
        int oldListSize = LoggedIn.INSTANCE.get().getContactList().getContacts().size();
        new Add(sms.getContexts(), "add " + field.getText()).run();
        int newListSize = LoggedIn.INSTANCE.get().getContactList().getContacts().size();
        if(oldListSize < newListSize) {
            panel.removeAll();
            createContactList(panel);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createContextMenu());
        menuBar.add(createFeaturesMenu());
        if(LoggedIn.INSTANCE.isLoggedIn()) menuBar.add(createSettingsMenu());
        return menuBar;
    }


    private JMenu createContextMenu(){
        JMenu menu = new JMenu("Contexts");
        JMenuItem day = new JMenuItem("Day");
        JMenuItem night = new JMenuItem("Night");
        if(sms.getFeatures().get("light").isActivated()) {
            day.setBackground(Color.GRAY);
        }else{
            day.addActionListener(e -> {
                new Activate(sms.getContexts(), "activate feature light").run();
                backgroundColor = Color.WHITE;
                textColor = Color.BLACK;
                night.setBackground(Color.WHITE);
                day.setBackground(Color.GRAY);
                setJMenuBar(createMenuBar());
                getContentPane().setBackground(backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);
            });
        }
        menu.add(day);
        if(sms.getFeatures().get("dark").isActivated()) {
            night.setBackground(Color.GRAY);
        }else{
            night.addActionListener(e -> {
                new Activate(sms.getContexts(), "activate feature dark").run();
                backgroundColor = Color.DARK_GRAY;
                textColor = Color.WHITE;
                night.setBackground(Color.BLACK);
                day.setBackground(Color.DARK_GRAY);
                setJMenuBar(createMenuBar());
                getContentPane().setBackground(backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);
            });
        }
        menu.add(night);
        JMenuItem wifi = new JMenuItem("Wifi");
        menu.add(wifi);
        JMenuItem cellular = new JMenuItem("Cellular");
        menu.add(cellular);
        return menu;
    }


    private JMenu createFeaturesMenu(){
        JMenu menu = new JMenu("Features");
        return menu;
    }


    private JMenu createSettingsMenu(){
        JMenu settings = new JMenu("Settings");
        JMenuItem logout = new JMenuItem("LogOut");
        logout.addActionListener(e->{
            LoggedIn.INSTANCE.logout();
            reset();
        });
        settings.add(logout);
        return settings;
    }


    public static void main(String[] args){
        new GUI();
        System.out.print(Colors.RESET);
    }
}