package commands;

import context.Context;
import features.Feature;

import java.util.ArrayList;
import java.util.List;

public class Activate implements Command {

    private final List<Feature> features;
    private final List<Context> contexts;
    private final String keyword;
    private final String command;

    public Activate(String name, List<Context> contexts, List<Feature> features, String command){
        this.contexts = contexts;
        this.features = features;
        this.keyword = name;
        this.command = command;
    }

    @Override
    public void run() {
        List<String> args = this.getArguments();
        if(args.get(0).equals("context")){
            for(Context context: contexts) if(context.getName().equalsIgnoreCase(args.get(1))) {
                context.activate();
            }
        }
    }

    @Override
    public List<String> getArguments() {
        List<String> arguments = new ArrayList<>();
        for(String s: command.split(" ")) if(!s.equals(this.keyword)) arguments.add(s);
        return arguments;
    }

    @Override
    public void error(String err) {

    }

    @Override
    public void feedback(String feedback) {

    }
}
