package smartMessagingSystem;

import context.*;
import commands.*;
import database.DataBase;


import database.Features;
import features.Feature;
import features.FeatureBehavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


class SmartMessagingSystem{

    private List<Context> contexts;
    private HashMap<String, FeatureBehavior> features;

    public SmartMessagingSystem(){
        DataBase.INSTANCE.populate();
        Features.INSTANCE.populate();
        this.defaultContext();
        this.features = Features.INSTANCE.get();
    }

    public void defaultContext(){
        contexts = new ArrayList<>();
        contexts.add(new Driving());
        contexts.add(new Meeting());
    }

    public HashMap<String, FeatureBehavior> getFeatures() {
        return features;
    }

    private List<Context> getContexts() {
        return this.contexts;
    }


    // TODO: really send messages
    public static void main(String[] args) {

        System.out.println("Welcome in SMS\n");
        SmartMessagingSystem sms = new SmartMessagingSystem();
        System.out.println();

        // Scanner for command at runtime
        System.out.println("Insert command to start conversation or (un)active context");
        Scanner scanner = new Scanner(System.in);
        CommandFactory cmdFactory = new CommandFactory();
        while(true){
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
            if(command.equals("exit")) break;
            Command cmd = cmdFactory.createCommand(command, sms.getContexts());
            if(cmd != null){ // Return null if command is not known
                cmd.run();
            }
        }
        System.out.println("Bye");
    }
}