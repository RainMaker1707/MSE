package commands;

import GUI.Constants;
import GUI.Frame;
import GUI.WelcomeMenu;
import behaviour.ContextBehavior;
import constant.Colors;
import database.LoggedIn;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Logout extends Command{
    public Logout(List<ContextBehavior> contexts, String command){
        super("Logout", contexts, command);
    }
    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(!args.isEmpty()) error("doesn't need argument" + Colors.currentColor);
        else {
            LoggedIn.INSTANCE.get().changeStatus();
            LoggedIn.INSTANCE.get().emptyNotification();
            LoggedIn.INSTANCE.logout();
        }
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        Frame.frame.setTitle(Constants.TITLE);
        return new WelcomeMenu(sms);
    }

    @Override
    public void help() {

    }
}
