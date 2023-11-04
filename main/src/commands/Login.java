package commands;

import constant.Colors;
import context.Context;
import database.DataBase;
import database.LoggedIn;

import java.util.List;

public class Login extends Command{
    public Login(List<Context> contexts, String command){
        super("login", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(args.size() > 1) error("cannot login multiple users");
        else if(args.isEmpty()) error("need a username to login");
        else{
            String username = args.get(0);
            if(DataBase.INSTANCE.getUser(username) != null){
                LoggedIn.INSTANCE.setLoggedIn(DataBase.INSTANCE.getUser(username));
            }else error("unknown user " + username + ". Create user before login!");
        }
    }

    @Override
    public void help(){

    }
}
