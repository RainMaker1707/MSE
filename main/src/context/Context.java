package context;

import features.Feature;
import constant.Colors;

import java.util.ArrayList;
import java.util.List;

public abstract class Context {
    String name;
    boolean activated;
    List<Feature> linkedFeatures;


    public Context(String name){
        this.name = name;
        this.activated = false;
        this.linkedFeatures = new ArrayList<>();
    }

    public Context(String name, List<Feature> linkedFeatures){
        this.name = name;
        this.activated = false;
        this.linkedFeatures = linkedFeatures;
    }

    public void activate(){
        if(!this.isActivated()) {
            this.activated = true;
            feedback("is now activated");
        }
        else error(this.getName() + " is already activated");
    }
    public void deactivate(){
        if(this.isActivated()) {
            this.activated = false;
            feedback("is now deactivated");
        }
        else error(this.getName() + " is already deactivated");
    }

    public String getName(){
        return this.name;
    }

    public boolean isActivated(){
        return this.activated;
    }

    public static void error(String err){
        System.out.println(Colors.ANSI_RED + "Context error: " + err + Colors.ANSI_RESET);
    }

    public void feedback(String feedback){
        System.out.println("Context: " + this.getName() + " " + feedback);
    }

}
