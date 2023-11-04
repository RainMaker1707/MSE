package smartMessagingSystem;

import context.*;
import commands.*;
import context.time.Time;
import database.DataBase;
import features.*;
import features.profile.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class SmartMessagingSystem{

    List<Context> contexts;

    public SmartMessagingSystem(){
        DataBase.INSTANCE.populate();
        this.defaultContext();
    }

    public void defaultContext(){
        contexts = new ArrayList<>();
        contexts.add(new Driving());
        contexts.add(new Meeting());
    }

    private List<Context> getContexts() {
        return this.contexts;
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
            String command = scanner.nextLine().trim();
            if(command.equals("exit")) break;
            Command cmd = cmdFactory.createCommand(command, sms.getContexts());
            if(cmd != null){
                cmd.run();
            }
        }
        System.out.println("Bye");
    }
}