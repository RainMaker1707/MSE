package database;

import behaviour.FeatureBehavior;
import features.contact.BlockContact;
import features.contact.Contact;
import features.contact.ContactList;
import features.conversation.Conversation;
import features.conversation.Group;
import features.message.Message;
import features.message.TextMessage;
import features.notification.Notification;
import features.notification.Silent;
import features.notification.Sound;
import features.notification.Vibrant;
import features.profile.Profile;
import features.themes.Dark;
import features.themes.Light;

import java.util.HashMap;

public enum Features {

    INSTANCE();

    final HashMap<String, FeatureBehavior> features = new HashMap<>();

    public void populate(){
        features.put("contact", new FeatureBehavior("contact", "mandatory"));
        features.get("contact").addClass(new Contact());

        features.put("list", new FeatureBehavior("list", "mandatory"));
        features.get("list").addClass(new ContactList());

        features.put("conversation", new FeatureBehavior("conversation", "mandatory"));
        features.get("conversation").addClass(new Conversation());

        features.put("group", new FeatureBehavior("group", "mandatory"));
        features.get("group").addClass(new Group());

        features.put("message", new FeatureBehavior("message", "mandatory"));
        features.get("message").addClass(new Message());

        features.put("text", new FeatureBehavior("text", "mandatory"));
        features.get("text").addClass(new TextMessage());

        features.put("profile", new FeatureBehavior("profile", "mandatory"));
        features.get("profile").addClass(new Profile());

        features.put("notification", new FeatureBehavior("notification", "mandatory"));
        features.get("notification").addClass(new Notification());

        features.put("block", new FeatureBehavior("block", "optional"));
        features.get("block").addClass(new BlockContact());

        features.put("hide", new FeatureBehavior("hide", "optional"));
        features.get("hide").addClass(new BlockContact());

        features.put("dark", new FeatureBehavior("dark", "alternative"));
        features.get("dark").addClass(new Dark());
        features.put("light", new FeatureBehavior("light", "alternative"));
        features.get("light").addClass(new Light());
        features.get("dark").addAlternativeFeature("light");
        features.get("light").addAlternativeFeature("dark");

        features.put("silent", new FeatureBehavior("silent", "alternative"));
        features.get("silent").addClass(new Silent());
        features.put("vibrant", new FeatureBehavior("vibrant", "alternative"));
        features.get("vibrant").addClass(new Vibrant());
        features.put("sound", new FeatureBehavior("sound", "alternative"));
        features.get("sound").addClass(new Sound());

        features.get("silent").addAlternativeFeature("sound");
        features.get("silent").addAlternativeFeature("vibrant");

        features.get("vibrant").addAlternativeFeature("silent");
        features.get("vibrant").addAlternativeFeature("sound");

        features.get("sound").addAlternativeFeature("silent");
        features.get("sound").addAlternativeFeature("vibrant");

    }

    public HashMap<String, FeatureBehavior> get(){
        return features;
    }

    public FeatureBehavior get(String name){
        return features.get(name);
    }

}
