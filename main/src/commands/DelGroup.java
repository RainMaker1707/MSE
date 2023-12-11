package commands;

import behaviour.ContextBehavior;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class DelGroup extends Command{
    public DelGroup(List<ContextBehavior> contexts, String command) {
        super("delGroup", contexts, command);
    }

    @Override
    public void run() {

    }

    @Override
    public void help() {

    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }
}
