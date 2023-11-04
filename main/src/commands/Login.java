package commands;

import constant.Colors;
import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.Feature;

import java.util.ArrayList;
import java.util.List;

public class Login extends Command{
    public Login(List<Context> contexts, String command){
        super("login", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments();
        if(args.size() > 1)
            System.out.println(Colors.ANSI_RED + "Login error: cannot login multiple users" + Colors.ANSI_RESET);
        else if(args.isEmpty())
            System.out.println(Colors.ANSI_RED + "Login error: need a username to login" + Colors.ANSI_RESET);
        else{
            String username = args.get(0);
            if(DataBase.INSTANCE.getUser(username) != null){
                LoggedIn.INSTANCE.setLoggedIn(DataBase.INSTANCE.getUser(username));
            }else {
                System.out.println(Colors.ANSI_RED
                        + "Login error: unknown user "
                        + username
                        + ". Create user before login!"
                        + Colors.ANSI_RESET
                );
            }
        }
    }

    @Override
    public List<String> getArguments() {
        List<String> arguments = new ArrayList<>();
        for(String s: command.split(" ")) if(!s.equalsIgnoreCase(this.getKeyword())) arguments.add(s);
        return arguments;
    }

    @Override
    public void error(String err) {

    }

    @Override
    public void feedback(String feedback) {

    }

    @Override
    public void help(){

    }
}
