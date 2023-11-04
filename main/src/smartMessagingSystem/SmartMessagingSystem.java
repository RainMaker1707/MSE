package smartMessagingSystem;

import context.*;
import commands.*;
import features.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class SmartMessagingSystem{

    List<Context> contexts;
    List<Command> commands;

    public SmartMessagingSystem(){
        this.defaultContext();
    }

    public void defaultContext(){
        contexts = new ArrayList<>();
        contexts.add(new Driving());
        contexts.add(new Meeting());
        contexts.add(new Device());
        contexts.add(new Mode());
        contexts.add(new Connectivity());
        contexts.add(new Time());
    }

    private List<Context> getContexts() {
        return this.contexts;
    }

    public void activateContext(String contextName){
        for(Context context: contexts){
            if(context.getName().equals(contextName)){
                context.activate();
                return;
            }
        }
        Context.error(contextName + " not found!");
    }

    public void deactivateContext(String contextName){
        for(Context context: contexts){
            if(context.getName().equals(contextName)){
                context.deactivate();
                return;
            }
        }
        Context.error(contextName + " not found!");
    }

    public void activateFeature(Feature feature){
        // TODO: link feature to context
        // TODO: activate feature when linked context is activated
    }

    public void deactivateFeature(Feature feature){
        // TODO: link feature to context
        // TODO: deactivate feature when linked context is deactivated
    }


    //Tests only for now
    /* TODO read input to know which user we are
        create profiles from DB
        really send messages
    */
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
            String command = scanner.nextLine();
            if(command.equals("exit")) break;
            Command cmd = cmdFactory.createCommand(command, sms.getContexts());
            if(cmd != null){
                cmd.run();
            }
        }
        System.out.println("Bye");
    }
}