package smartMessagingSystem;

import context.*;
import commands.*;


class SmartMessagingSystem{

    Context context;
    Commands commands;

    public SmartMessagingSystem(Context context, Commands commands){
        this.context = context;
        this.commands = commands;

    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}