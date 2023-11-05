package features.profile;


import database.Features;
import features.FeatureBehavior;
import features.contact.Contact;
import features.contact.ContactList;
import features.conversation.Conversation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import features.Feature;

public class Profile{
    private final Image picture;
    private final String name;
    private final ContactList contactList;
    private final List<Conversation> conversations;

    FeatureBehavior behavior = Features.INSTANCE.get("profile");

    public Profile(String name, Image picture){
        this.name = name;
        this.picture = picture;
        this.contactList = new ContactList(this);
        this.conversations = new ArrayList<>();
    }

    public Profile getProfile(){
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Image getPicture() {
        return this.picture;
    }

    public ContactList getContactList() {
        return contactList;
    }

    public void addContact(Contact contact){
        contactList.addContact(contact);
    }

    public void addBlocked(Contact contact){
        contactList.addBlocked(contact);
    }

    public void removeContact(Contact contact){
        contactList.removeContact(contact);
    }
    public void removeBlocked(Contact contact){
        contactList.removeBlocked(contact);
    }
    public void addConversation(Conversation c){
        this.conversations.add(c);
    }

    public List<Conversation> getConversations(){
        return this.conversations;
    }
}
