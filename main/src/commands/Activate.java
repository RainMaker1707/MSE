package commands;

import context.Context;

import java.util.List;

public class Activate extends Command{


    public Activate(List<Context> contexts, String command){
        super("activate", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments();
        for(String arg: args) {
            int index = contexts.stream().map(Context::getName).toList().indexOf(arg);
            if(index != -1){
                contexts.get(index).activate();
            }else Context.error(arg + " not found!");
        }
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
