package database;

import features.FeatureBehavior;
import features.themes.Dark;
import features.themes.Light;

import java.util.HashMap;

public enum Features {

    INSTANCE();

    HashMap<String, FeatureBehavior> features = new HashMap<>();

    public void populate(){
        features.put("contact", new FeatureBehavior("contact", "mandatory"));
        features.put("list", new FeatureBehavior("list", "mandatory"));
        features.put("conversation", new FeatureBehavior("conversation", "mandatory"));
        features.put("group", new FeatureBehavior("group", "mandatory"));
        features.put("message", new FeatureBehavior("message", "mandatory"));
        features.put("text", new FeatureBehavior("text", "mandatory"));
        features.put("profile", new FeatureBehavior("profile", "mandatory"));

        features.put("block", new FeatureBehavior("block", "optional"));

        features.put("dark", new FeatureBehavior("dark", "alternative"));
        features.put("light", new FeatureBehavior("light", "alternative"));
        features.get("dark").activate();
        features.get("dark").addAlternativeFeature("light");
        features.get("light").addAlternativeFeature("dark");
        features.get("light").activate();
    }

    public HashMap<String, FeatureBehavior> get(){
        return features;
    }

    public FeatureBehavior get(String name){
        return features.get(name);
    }

}
