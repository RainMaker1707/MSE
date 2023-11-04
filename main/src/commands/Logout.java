package commands;

import constant.Colors;
import context.Context;
import database.DataBase;
import database.LoggedIn;

import java.util.ArrayList;
import java.util.List;

public class Logout extends Command{
    public Logout(String name, List<Context> contexts, String command){
        super(name, contexts, command);
    }
    @Override
    public void run() {
        List<String> args = this.getArguments();
        if(args.size() > 1)
            System.out.println(Colors.ANSI_RED + "Login error: cannot login multiple users" + Colors.ANSI_RESET);
        else if(args.isEmpty())
            System.out.println(Colors.ANSI_RED + "Login error: need a username to login" + Colors.ANSI_RESET);
        else{
            LoggedIn.INSTANCE.logout();
        }
    }

    @Override
    public List<String> getArguments() {
        List<String> arguments = new ArrayList<>();
        for (String s : command.split(" ")) if (!s.equalsIgnoreCase(this.getKeyword())) arguments.add(s);
        return arguments;
    }

    @Override
    public void error(String err) {

    }

    @Override
    public void feedback(String feedback) {

    }

    @Override
    public void help() {

    }
}
