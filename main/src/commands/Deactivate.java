package commands;

import context.Context;

import java.util.List;

public class Deactivate extends Command {

    public Deactivate(List<Context> contexts, String command){
        super("deactivate", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(true);
        for(String arg: args) {
            int index = contexts.stream().map(Context::getName).toList().indexOf(arg);
            if(index != -1){
                contexts.get(index).deactivate();
            }else Context.error(arg + " not found!");
        }
    }

    @Override
    public void help(){

    }
}
