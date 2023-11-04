package commands;

import context.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    final List<Context> contexts;
    private final String keyword;
    final String command;

    public Command(String name, List<Context> contexts, String command){
        this.contexts = contexts;
        this.keyword = name;
        this.command = command;
    }

    public abstract void run();
    public List<String> getArguments() {
        List<String> arguments = new ArrayList<>();
        for(String s: command.split(" ")) if(!s.equalsIgnoreCase(this.getKeyword())) arguments.add(s.toLowerCase());
        return arguments;
    }
    public abstract void error(String err);
    public abstract void feedback(String feedback);

    public abstract void help();

    public String getKeyword(){
        return this.keyword;
    }
}
