package features.profile;


import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;
import features.contact.Contact;
import features.contact.ContactList;
import features.conversation.Conversation;
import features.conversation.Group;
import features.conversation.GroupList;
import features.notification.Notification;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Profile extends Feature {
    private  Image picture;
    private  String name;
    private  ContactList contactList;
    private  List<Conversation> conversations;
    private  GroupList groups;
    private  List<Notification> notifications;

    FeatureBehavior behavior = Features.INSTANCE.get("profile");

    public Profile(){}

    public Profile(String name, Image picture){
        this.name = name;
        this.picture = picture;
        this.contactList = new ContactList(this);
        this.conversations = new ArrayList<>();
        this.groups = new GroupList(this);
        this.notifications = new ArrayList<>();
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

    public GroupList getGroups() {
        return this.groups;
    }

    public void addContact(Contact contact){
        contactList.addContact(contact);
    }

    public void addGroup(Group group) {
        this.groups.addGroup(group);
    }

    public void addBlocked(Contact contact){
        contactList.addBlocked(contact);
    }

    public void addHidden(Contact contact) {
        contactList.addHidden(contact);
    }

    public void removeContact(Contact contact){
        contactList.removeContact(contact);
    }
    public void removeBlocked(Contact contact){
        contactList.removeBlocked(contact);
    }
    public void removeHidden(Contact contact) {
        contactList.removeHidden(contact);
    }

    public void removeGroup(Group group) {
        groups.removeGroup(group);
    }
    public void addConversation(Conversation c){
        this.conversations.add(c);
    }
    
    public List<Conversation> getConversations(){
        return this.conversations;
    }

    public List<Notification> getNotifications(){
        return this.notifications;
    }

    public List<Contact> getHiddenContacts() {
        return contactList.getHiddenContact();
    }

    public void addNotification(Notification n) {
        this.notifications.add(n);
    }

    @Override
    public void activate() {

    }
}
