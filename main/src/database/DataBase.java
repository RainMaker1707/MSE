package database;

import features.contact.Contact;
import features.profile.Profile;

import java.util.HashMap;

public enum DataBase {


    INSTANCE();

    final HashMap<String, Contact> set = new HashMap<>();

    public void populate(){
        // create profile
        set.put("Bob", new Contact("Bob", null));
        set.put("Alice", new Contact("Alice", null));
        set.put("WhiteHat", new Contact("WhiteHat", null));
        set.put("BlackHat", new Contact("BlackHat", null));

        set.get("Bob").addContact(set.get("Alice"));
        set.get("Alice").addContact(set.get("Bob"));
    }

    public Contact getUser(String username){
        return set.get(username);
    }

}
