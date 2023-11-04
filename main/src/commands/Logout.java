package commands;

import constant.Colors;
import context.Context;
import database.LoggedIn;

import java.util.ArrayList;
import java.util.List;

public class Logout extends Command{
    public Logout(List<Context> contexts, String command){
        super("Logout", contexts, command);
    }
    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(!args.isEmpty()) error("doesn't need argument" + Colors.ANSI_RESET);
        else LoggedIn.INSTANCE.logout();
    }

    @Override
    public void help() {

    }
}
