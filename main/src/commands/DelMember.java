package commands;

import behaviour.ContextBehavior;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class DelMember extends Command{
    public DelMember(List<ContextBehavior> contexts, String command) {
        super("delMember", contexts, command);
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
