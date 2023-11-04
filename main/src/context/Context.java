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
        this.name = name.toLowerCase();
        this.activated = false;
        // TODO: link real feature
        this.linkedFeatures = new ArrayList<>();
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

    public List<Feature> getLinkedFeatures(){
        return this.linkedFeatures;
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
        System.out.println(Colors.ANSI_BLUE + "Context: " + this.getName() + " " + feedback + Colors.ANSI_RESET);
    }

}
