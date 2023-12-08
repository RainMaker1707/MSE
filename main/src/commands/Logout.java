package commands;

import constant.Colors;
import context.Context;
import database.LoggedIn;

import javax.swing.*;
import java.util.List;

public class Logout extends Command{
    public Logout(List<Context> contexts, String command){
        super("Logout", contexts, command);
    }
    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(!args.isEmpty()) error("doesn't need argument" + Colors.currentColor);
        else {
            LoggedIn.INSTANCE.get().changeStatus();
            LoggedIn.INSTANCE.logout();
        }
    }

    @Override
    public JPanel gui() {
        return null;
    }

    @Override
    public void help() {

    }
}
