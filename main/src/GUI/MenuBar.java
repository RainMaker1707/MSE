package GUI;

import behaviour.ContextBehavior;
import commands.Activate;
import commands.Login;
import commands.Logout;
import database.ContextsDB;
import database.Features;
import database.LoggedIn;
import features.conversation.Group;
import features.notification.Notification;
import features.notification.NotificationState;
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
        if(LoggedIn.INSTANCE.isLoggedIn()) {
            add(Box.createHorizontalGlue());
            add(createNotificationsMenu());
            add(createSettingsMenu());
        }
        SwingUtilities.updateComponentTreeUI(Frame.frame);
    }

    private JMenuBar createMenuBar(){
        return new MenuBar(sms);
    }

    private void refresh(){
        Frame.frame.setJMenuBar(createMenuBar());
        SwingUtilities.updateComponentTreeUI(this);
    }


    private JMenu createContextMenu(){
        JMenu menu = new JMenu("Contexts");
        for(String context: ContextsDB.INSTANCE.get().keySet().stream().toList()){
            JMenuItem item = new JMenuItem(context);
            if(ContextsDB.INSTANCE.get(context).isActivated()){
                item.setText("✔️ " + item.getText());
                item.addActionListener(e-> {
                    ContextsDB.INSTANCE.get(context).deactivate();
                    refresh();
                });
            } else{
                item.setText(item.getText());
                item.addActionListener(e-> {
                    ContextsDB.INSTANCE.get(context).activate();
                    refresh();
                });
            }

            menu.add(item);
        }
        return menu;
    }


    private JMenu createFeaturesMenu(){
        JMenu menu = new JMenu("Features");
        for(String feature: Features.INSTANCE.get().keySet().stream().toList()){
            JMenuItem item = new JMenuItem(feature);
            if(Features.INSTANCE.get(feature).isActivated()) item.setBackground(Color.GREEN);
            else item.setBackground(Color.RED);
            item.setText(item.getText());
            menu.add(item);
        }
        return menu;
    }


    private JMenu createSettingsMenu(){
        JMenu settings = new JMenu("⚙️");
        JMenuItem logout = new JMenuItem("LogOut");
        logout.addActionListener(e->{
            new Logout(sms.getContexts(), "logout").run();
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

    private JMenu createNotificationsMenu() {
        JMenu menu = new JMenu("🔔");
        for(Notification notif :LoggedIn.INSTANCE.get().getNotifications()){
            JMenuItem item;
            if(notif.isGroup()) item = new JMenuItem("new message in " + ((Group)notif.getConversation()).getGroupName());
            else item = new JMenuItem("new message from " + notif.getSender().getName());
            menu.add(item);
        }
        return menu;
    }
}
