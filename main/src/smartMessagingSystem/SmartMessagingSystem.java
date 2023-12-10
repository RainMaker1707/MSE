package smartMessagingSystem;

import behaviour.ContextBehavior;
import context.*;
import database.ContextsDB;
import database.DataBase;


import database.Features;
import behaviour.FeatureBehavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SmartMessagingSystem{

    private final HashMap<String, FeatureBehavior> features;

    public SmartMessagingSystem(){
        DataBase.INSTANCE.populate();
        Features.INSTANCE.populate();
        ContextsDB.INSTANCE.populate();
        this.features = Features.INSTANCE.get();
    }


    public HashMap<String, FeatureBehavior> getFeatures() {
        return features;
    }

    public List<ContextBehavior> getContexts() { return ContextsDB.INSTANCE.get().values().stream().toList(); }
}