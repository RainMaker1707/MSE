package database;

import behaviour.ContextBehavior;

import java.util.HashMap;

public enum ContextsDB {

    INSTANCE;

    final HashMap<String, ContextBehavior> set = new HashMap<>();

    public void populate(){
        set.put("driving", new ContextBehavior("driving", "optional"));
        set.put("meeting", new ContextBehavior("meeting", "optional"));

        set.put("day", new ContextBehavior("day", "alternative"));
        set.put("night", new ContextBehavior("night", "alternative"));

        set.get("day").addAlternativeFeature("night");
        set.get("night").addAlternativeFeature("day");

        set.put("flight", new ContextBehavior("flight", "alternative"));
        set.put("silent", new ContextBehavior("silent", "alternative"));
        set.put("sound", new ContextBehavior("sound", "alternative"));
        set.put("vibrate", new ContextBehavior("vibrate", "alternative"));

        set.get("flight").addAlternativeFeature("vibrate");
        set.get("flight").addAlternativeFeature("silent");
        set.get("flight").addAlternativeFeature("sound");

        set.get("silent").addAlternativeFeature("flight");
        set.get("silent").addAlternativeFeature("vibrant");
        set.get("silent").addAlternativeFeature("sound");

        set.get("sound").addAlternativeFeature("flight");
        set.get("sound").addAlternativeFeature("silent");
        set.get("sound").addAlternativeFeature("vibrant");

        set.get("vibrant").addAlternativeFeature("flight");
        set.get("vibrant").addAlternativeFeature("silent");
        set.get("vibrant").addAlternativeFeature("sound");

        set.put("mobiledata", new ContextBehavior("mobiledata", "alternative"));
        set.put("wifi", new ContextBehavior("wifi", "alternative"));

        set.get("mobiledata").addAlternativeFeature("wifi");
        set.get("wifi").addAlternativeFeature("mobiledata");


        set.put("computer", new ContextBehavior("computer", "alternative"));
        set.put("phone", new ContextBehavior("phone", "alternative"));
        set.put("tablet", new ContextBehavior("tablet", "alternative"));

        set.get("computer").addAlternativeFeature("phone");
        set.get("computer").addAlternativeFeature("tablet");

        set.get("phone").addAlternativeFeature("computer");
        set.get("phone").addAlternativeFeature("tablet");

        set.get("tablet").addAlternativeFeature("computer");
        set.get("tablet").addAlternativeFeature("phone");

        set.put("subscribe", new ContextBehavior("subscribe", "optional"));
    }

    public HashMap<String, ContextBehavior> get(){
        return set;
    }

    public ContextBehavior get(String name){
        return set.get(name);
    }

}
