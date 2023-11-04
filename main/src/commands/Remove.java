package commands;

import context.Context;

import java.util.List;

public class Remove extends Command{
    public Remove(String name, List<Context> contexts, String command){
        super(name, contexts, command);
    }
    @Override
    public void run() {

    }

    @Override
    public void help(){

    }
}
