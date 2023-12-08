import GUI.Frame;
import GUI.MenuBar;
import GUI.WelcomeMenu;
import commands.Command;
import commands.CommandFactory;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.Scanner;



public class Main {
    static boolean gui = false;

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

        SmartMessagingSystem sms = new SmartMessagingSystem();

        if(Main.gui){
            Frame.createFrame();
            Frame.frame.setJMenuBar(new MenuBar(sms));
            Frame.lastPanel = new WelcomeMenu(sms);
            Frame.frame.add(Frame.lastPanel);
            SwingUtilities.updateComponentTreeUI(Frame.frame);
        }

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
            if(cmd != null) {
                cmd.run();
                if(Frame.frame != null) {
                    Frame.frame.remove(Frame.lastPanel);
                    Frame.frame.setJMenuBar(new MenuBar(sms));
                    Frame.frame.add(cmd.gui(sms));
                    SwingUtilities.updateComponentTreeUI(Frame.frame);
                }
            }
        }
        System.out.println("Bye");
        System.exit(0);
    }
}
