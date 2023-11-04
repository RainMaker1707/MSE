package database;

import features.contact.Contact;
import features.profile.Profile;

import java.util.HashMap;

public enum DataBase {


    INSTANCE();

    final HashMap<String, Profile> set = new HashMap<>();

    public void populate(){
        // create profile
        set.put("Bob", new Profile("Bob", null));
        set.put("Alice", new Profile("Alice", null));
        set.put("WhiteHat", new Profile("WhiteHat", null));
        set.put("BlackHat", new Profile("BlackHat", null));

        // populate contact list
        set.get("Bob").addContact(new Contact(set.get("Alice")));
        set.get("Bob").addContact(new Contact(set.get("WhiteHat")));
        set.get("Alice").addContact(new Contact(set.get("Bob")));
        set.get("BlackHat").addContact(new Contact(set.get("Alice")));
    }

    public Profile getUser(String username){
        return set.get(username);
    }

    public void addUser(Profile profile){
        set.put(profile.getName(), profile);
    }

}
