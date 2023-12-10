package commands;

import behaviour.ContextBehavior;
import context.Context;
import database.Features;
import behaviour.FeatureBehavior;
import features.themes.Dark;
import features.themes.Light;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Deactivate extends Command {
    public Deactivate(List<ContextBehavior> contexts, String command){
        super("deactivate", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(true);
        if(args.size() < 2) {
            error("need at least 2 arguments: context/feature and then the context or feature name to activate");
        }
        String key = args.get(0);
        args.remove(0);
        if(key.equals("context")) {
            for(String arg: args) {
                int index = contexts.stream().map(ContextBehavior::getName).toList().indexOf(arg);
                if(index != -1){
                    contexts.get(index).deactivate();
                }else Context.error(arg + " not found!");
            }
        }else if(key.equals("feature")){
            for(String arg: args) {
                FeatureBehavior behavior = Features.INSTANCE.get(arg);
                // TODO list alternative to check if activated and deactivate them
                if(behavior.getAlternative() || behavior.getOptional()){
                    if(behavior.isActivated()){
                        behavior.deactivate();
                        if(behavior.getName().equals("light")) Dark.activate();
                        if(behavior.getName().equals("dark")) Light.activate();
                        feedback(arg + " is now deactivated." );
                    }else error(arg + " is already deactivated!");
                }else if(behavior.getMandatory()) error("Mandatory features can't be deactivated");
            }
        }else error("Not recognized argument: " + key);
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }

    @Override
    public void help(){

    }
}
