package smartMessagingSystem;

import context.*;
import commands.*;
import database.DataBase;


import database.Features;
import features.FeatureBehavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class SmartMessagingSystem{

    private List<Context> contexts;
    private final HashMap<String, FeatureBehavior> features;

    public SmartMessagingSystem(){
        DataBase.INSTANCE.populate();
        Features.INSTANCE.populate();
        this.defaultContext();
        this.features = Features.INSTANCE.get();
    }

    public void defaultContext(){
        contexts = new ArrayList<>();
        contexts.add(new Driving());
        contexts.add(new Meeting());
    }

    public HashMap<String, FeatureBehavior> getFeatures() {
        return features;
    }

    public List<Context> getContexts() {
        return this.contexts;
    }
}