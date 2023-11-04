package commands;

import context.Context;
import features.Feature;

import java.util.List;

public class Add extends Command{
    public Add(List<Context> contexts, String command){
        super("add", contexts, command);
    }
    @Override
    public void run() {
        // TODO: add contact IFF a user is loggedIn
    }

    @Override
    public void help(){

    }
}
