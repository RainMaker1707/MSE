package GUI;

import commands.Activate;
import database.LoggedIn;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.awt.*;

import GUI.Frame;

public class MenuBar extends JMenuBar {

    SmartMessagingSystem sms;

    public MenuBar(SmartMessagingSystem sms){
        this.sms = sms;
        add(createContextMenu());
        add(createFeaturesMenu());
        if(LoggedIn.INSTANCE.isLoggedIn()) add(createSettingsMenu());
        SwingUtilities.updateComponentTreeUI(Frame.frame);
    }

    private JMenuBar createMenuBar(){
        return new MenuBar(sms);
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
                // TODO: repair this
                Constants.backgroundColor = Color.WHITE;
                Constants.textColor = Color.BLACK;
                night.setBackground(Color.WHITE);
                day.setBackground(Color.GRAY);
                Frame.frame.setJMenuBar(createMenuBar());
                Frame.frame.getContentPane().setBackground(Constants.backgroundColor);
                SwingUtilities.updateComponentTreeUI(this);
            });
        }
        menu.add(day);
        if(sms.getFeatures().get("dark").isActivated()) {
            night.setBackground(Color.GRAY);
        }else{
            night.addActionListener(e -> {
                new Activate(sms.getContexts(), "activate feature dark").run();
                // TODO: repair this
                Constants.backgroundColor = Color.DARK_GRAY;
                Constants.textColor = Color.WHITE;
                night.setBackground(Color.BLACK);
                day.setBackground(Color.DARK_GRAY);
                Frame.frame.setJMenuBar(createMenuBar());
                Frame.frame.getContentPane().setBackground(Constants.backgroundColor);
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
            if(LoggedIn.INSTANCE.get() == null){
                Frame.frame.setTitle(Constants.TITLE);
                Frame.frame.remove(Frame.lastPanel);
                Frame.frame.setJMenuBar(new MenuBar(sms));
                Frame.frame.add(new WelcomeMenu(sms));
                SwingUtilities.updateComponentTreeUI(Frame.frame);
            }
        });
        settings.add(logout);
        return settings;
    }
}
