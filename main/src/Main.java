import GUI.Constants;
import GUI.WelcomeMenu;
import commands.Command;
import commands.CommandFactory;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;



public class Main {
    static boolean gui = false;

    public static JFrame createFrame(){
        JFrame frame = new JFrame();
        frame.setTitle(Constants.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.TOTAL_WIDTH, Constants.TOTAL_HEIGHT);
        frame.setVisible(true);
        return frame;
    }

    public static void main(String[] args) {
        for(String arg: args) {
            switch(arg){
                case "--gui":
                    Main.gui = true;
                    break;
                default:
                    System.out.println("unrecognized argument: " + arg);
            }
        }

        if(Main.gui){
            JFrame frame = createFrame();
            frame.add(new WelcomeMenu());
        }

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
        System.exit(0);
    }
}
