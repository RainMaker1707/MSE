package context;

import behaviour.ContextBehavior;
import behaviour.FeatureBehavior;
import constant.Colors;


public abstract class Context {
    String name;
    boolean activated;

    ContextBehavior behavior;

    public Context(String name, ContextBehavior behavior){
        this.name = name.toLowerCase();
        this.activated = false;
        this.behavior = behavior;
    }

    public String getName(){
        return this.name;
    }

    public boolean isActivated(){
        return this.activated;
    }

    public static void error(String err){
        System.out.println(Colors.ANSI_RED + "Context error: " + err + Colors.currentColor);
    }

    public void feedback(String feedback){
        System.out.println(Colors.ANSI_BLUE + "Context: " + this.getName() + " " + feedback + Colors.currentColor);
    }

}
