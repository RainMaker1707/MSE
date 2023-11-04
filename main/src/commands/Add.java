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

    }

    @Override
    public List<String> getArguments() {
        return null;
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
