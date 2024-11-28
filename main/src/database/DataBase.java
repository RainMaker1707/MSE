package database;

import features.contact.Contact;
import features.conversation.Group;

import java.util.HashMap;

public enum DataBase {


    INSTANCE();

    final HashMap<String, Contact> set = new HashMap<>();
    final HashMap<String, Group> setGroup = new HashMap<>();

    public void populate(){
        // create profile
        set.put("Bob", new Contact("Bob", null));
        set.put("Alice", new Contact("Alice", null));
        set.put("WhiteHat", new Contact("WhiteHat", null));
        set.put("BlackHat", new Contact("BlackHat", null));

        set.get("Bob").addContact(set.get("Alice"));
        set.get("Bob").addContact(set.get("WhiteHat"));
        set.get("WhiteHat").addContact(set.get("Bob"));
        set.get("Alice").addContact(set.get("Bob"));

        setGroup.put("Friends", new Group("Friends", set.get("Bob"), set.get("Alice")));
    }

    public Contact getUser(String username){
        return set.get(username);
    }

    public void setUser(String username, Contact contact) {
        set.put(username, contact);
    }

    public Group getGroup(String groupname) {
        return setGroup.get(groupname);
    }

}
