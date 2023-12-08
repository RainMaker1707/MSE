package commands;

import context.Context;
import database.Features;
import features.FeatureBehavior;
import features.themes.Dark;
import features.themes.Light;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Activate extends Command{


    public Activate(List<Context> contexts, String command){
        super("activate", contexts, command);
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
            for (String arg : args) {
                activateContext(arg);
            }
        }else if(key.equals("feature")){
            for(String arg: args) {
                activateFeature(arg);
            }
        }else error("Not recognized argument: " + key);
    }

    public void activateContext(String arg){
        arg = arg.replace(",", "");
        int index = contexts.stream().map(Context::getName).toList().indexOf(arg);
        if (index != -1) {
            contexts.get(index).activate();
        } else Context.error(arg + " not found!");
    }

    public void activateFeature(String arg){
        FeatureBehavior behavior = Features.INSTANCE.get(arg);
        if(behavior == null){
            error(arg + " is not a feature");
            return;
        }
        if(behavior.getMandatory()) error("Mandatory features are always activated");
        else if(behavior.getAlternative() ||behavior.getOptional()){
            if(!behavior.isActivated()){
                behavior.activate();
                if(behavior.getName().equals("light"))Light.activate();
                if(behavior.getName().equals("dark")) Dark.activate();
                feedback(arg + " is now activated.");
            }else error(arg + " is already activated!");
        }
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }

    @Override
    public void help(){

    }
}
