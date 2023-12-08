import commands.Command;
import commands.CommandFactory;
import smartMessagingSystem.SmartMessagingSystem;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        SmartMessagingSystem sms = new SmartMessagingSystem();
        System.out.println("Welcome in SMS\n");
        System.out.println();

        // Scanner for command at runtime
        System.out.println("Insert command to start conversation or (un)active context");
        Scanner scanner = new Scanner(System.in);
        CommandFactory cmdFactory = new CommandFactory();
        while(true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
            if(command.equals("exit")) break;
            Command cmd = cmdFactory.createCommand(command, sms.getContexts());
            // Return null if command is not known
            if(cmd != null) cmd.run();
        }
        System.out.println("Bye");
    }
}
