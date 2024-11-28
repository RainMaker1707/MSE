package commands;

import GUI.Frame;
import GUI.LoggedInMenu;
import behaviour.ContextBehavior;
import context.Context;
import database.Features;
import behaviour.FeatureBehavior;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class Activate extends Command{


    public Activate(List<ContextBehavior> contexts, String command){
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
        int index = contexts.stream().map(ContextBehavior::getName).toList().indexOf(arg);
        if (index != -1) {
            contexts.get(index).activate();
            for(FeatureBehavior featureBehavior: contexts.get(index).getLinkedFeaturesBehavior()) featureBehavior.activate();
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
                feedback(arg + " is now activated.");
            }else error(arg + " is already activated!");
        }
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return new LoggedInMenu(sms);
    }

    @Override
    public void help(){
    }
}
