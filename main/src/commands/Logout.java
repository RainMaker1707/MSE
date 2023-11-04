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
        List<String> args = this.getArguments();
        if(!args.isEmpty())
            System.out.println(Colors.ANSI_RED + "Logout doesn't need argument" + Colors.ANSI_RESET);
        else LoggedIn.INSTANCE.logout();
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
