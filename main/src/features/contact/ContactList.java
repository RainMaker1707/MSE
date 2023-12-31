package features.contact;


import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;
import features.profile.Profile;

import java.util.List;
import java.util.ArrayList;

public class ContactList extends Feature {
    private Profile owner;
    List<Contact> contactList;
    List<Contact> blocked;
    List<Contact> hidden;
    FeatureBehavior behavior = Features.INSTANCE.get("list");

    public ContactList(){}

    public ContactList(Profile profile){
        this.owner = profile;
        this.contactList = new ArrayList<>();
        this.blocked = new ArrayList<>();
        this.hidden = new ArrayList<>();
    }

    public List<Contact> getContacts() {
        return this.contactList;
    }

    public void addContact(Contact contact){
        this.contactList.add(contact);
    }
    public void removeContact(Contact contact){
        this.contactList.remove(contact);
    }
    public void addBlocked(Contact contact){

        this.blocked.add(contact);
    }
    public void removeBlocked(Contact contact){
        this.blocked.remove(contact);
    }
    public void addHidden(Contact contact) {
        this.hidden.add(contact);
    }
    public void removeHidden(Contact contact) {
        this.hidden.remove(contact);
    }
    public List<Status> getContactsStatus(){
        return this.contactList.stream().map(Contact::getStatus).toList();
    }

    public List<String> getContactsName(){
        return this.contactList.stream().map(Contact::getName).toList();
    }

    public Contact getContact(String name) {
        return this.contactList.stream()
                .filter(contact -> contact.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Contact> getHiddenContact() {
        return this.hidden;
    }

    @Override
    public String toString() {
        StringBuilder contactList = new StringBuilder();
        contactList.append("[");
        for(Contact contact: this.getContacts()){
            contactList.append(contact.getName());
            contactList.append(": ");
            contactList.append(contact.getStatus());
            contactList.append(", ");
        }
        if(contactList.length()>2)contactList.delete(contactList.length() - 2, contactList.length());
        contactList.append("]");
        return contactList.toString();
    }

    @Override
    public void activate() {

    }
}
