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
        set.get("day").addLinkedFeature(Features.INSTANCE.get("light"));
        set.get("night").addLinkedFeature(Features.INSTANCE.get("dark"));

        set.get("day").addAlternativeContext("night");
        set.get("night").addAlternativeContext("day");
        set.get("night").activate();

        set.put("flight", new ContextBehavior("flight", "alternative"));
        set.put("silent", new ContextBehavior("silent", "alternative"));
        set.put("sound", new ContextBehavior("sound", "alternative"));
        set.put("vibrate", new ContextBehavior("vibrate", "alternative"));

        set.get("flight").addAlternativeContext("vibrate");
        set.get("flight").addAlternativeContext("silent");
        set.get("flight").addAlternativeContext("sound");

        set.get("silent").addAlternativeContext("flight");
        set.get("silent").addAlternativeContext("vibrate");
        set.get("silent").addAlternativeContext("sound");

        set.get("sound").addAlternativeContext("flight");
        set.get("sound").addAlternativeContext("silent");
        set.get("sound").addAlternativeContext("vibrate");

        set.get("vibrate").addAlternativeContext("flight");
        set.get("vibrate").addAlternativeContext("silent");
        set.get("vibrate").addAlternativeContext("sound");

        set.put("mobile_data", new ContextBehavior("mobile_data", "alternative"));
        set.put("wifi", new ContextBehavior("wifi", "alternative"));

        set.get("mobile_data").addAlternativeContext("wifi");
        set.get("wifi").addAlternativeContext("mobile_data");


        set.put("computer", new ContextBehavior("computer", "alternative"));
        set.put("phone", new ContextBehavior("phone", "alternative"));
        set.put("tablet", new ContextBehavior("tablet", "alternative"));

        set.get("computer").addAlternativeContext("phone");
        set.get("computer").addAlternativeContext("tablet");

        set.get("phone").addAlternativeContext("computer");
        set.get("phone").addAlternativeContext("tablet");

        set.get("tablet").addAlternativeContext("computer");
        set.get("tablet").addAlternativeContext("phone");

        set.put("subscribe", new ContextBehavior("subscribe", "optional"));
        set.get("subscribe").addLinkedFeature(Features.INSTANCE.get("block"));
    }

    public HashMap<String, ContextBehavior> get(){
        return set;
    }

    public ContextBehavior get(String name){
        return set.get(name);
    }

}
