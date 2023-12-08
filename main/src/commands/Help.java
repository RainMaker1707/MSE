package commands;

import context.Context;

import javax.swing.*;
import java.util.List;

public class Help extends Command{
    public Help(String name, List<Context> contexts, String command){
        super(name, contexts, command);
    }
    @Override
    public void run() {

    }

    @Override
    public JPanel gui() {
        return null;
    }

    @Override
    public void help(){

    }
}
