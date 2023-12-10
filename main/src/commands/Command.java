package commands;

import behaviour.ContextBehavior;
import constant.Colors;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    final List<ContextBehavior> contexts;
    private final String keyword;
    final String command;

    public Command(String name, List<ContextBehavior> contexts, String command){
        this.contexts = contexts;
        this.keyword = name;
        this.command = command;
    }

    public abstract void run();
    public List<String> getArguments(boolean lowerCase) {
        List<String> arguments = new ArrayList<>();
        for(String s: command.split(" ")) {
            if(!s.equalsIgnoreCase(this.getKeyword())) {
                if(lowerCase) arguments.add(s.toLowerCase());
                else arguments.add(s);
            }
        }
        return arguments;
    }
    public void error(String err){
        System.out.println(Colors.ANSI_RED + "Command Error: " + this.getKeyword() + " --> " + err + Colors.currentColor);
    }
    public void feedback(String feedback){
        System.out.println(Colors.ANSI_BLUE + feedback + Colors.currentColor);
    }

    public abstract void help();

    public abstract JPanel gui(SmartMessagingSystem sms);

    public String getKeyword(){
        return this.keyword;
    }
}
