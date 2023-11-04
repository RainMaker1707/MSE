package features.profile;


import features.contact.Contact;
import features.contact.ContactList;
import features.conversation.Conversation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import features.Feature;

public class Profile extends Feature{
    private final Image picture;
    private final String name;
    private final ContactList contactList;

    private final List<Conversation> conversations;

    public Profile(String name, Image picture){
        super("Profile");
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
        return this.contactList;
    }

    public void addContact(Contact contact){
        this.contactList.addContact(contact);
    }

    public void removeContact(Contact contact){
        this.contactList.removeContact(contact);
    }

    public void blockContact(Contact contact){
        this.contactList.blockContact(contact);
    }

    public void addConversation(Conversation c){
        this.conversations.add(c);
    }

    public List<Conversation> getConversations(){
        return this.conversations;
    }
}
