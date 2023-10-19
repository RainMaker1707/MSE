package features.contact;


import features.profile.Profile;

import java.util.List;
import java.util.ArrayList;

public class ContactList{
    private final Profile owner ;
    List<Contact> contactList;
    List<Contact> blocked;
    public ContactList(Profile profile){
        this.owner = profile;
        this.contactList = new ArrayList<>();
        this.blocked = new ArrayList<>();
        // TODO read DB to restore contact list for a certain profile
    }

    public void addContact(Contact contact){
        this.contactList.add(contact);
    }
    public void removeContact(Contact contact){
        this.contactList.remove(contact);
    }
    public void blockContact(Contact contact){
        this.contactList.remove(contact);
        this.blocked.add(contact);
    }

    public void unblockContact(Contact contact){
        this.contactList.add(contact);
        this.blocked.remove(contact);
    }

    public List<Status> getContactsStatus(){
        return this.contactList.stream().map(Contact::getStatus).toList();
    }

    public List<String> getContactsName(){
        return this.contactList.stream().map(Contact::getName).toList();
    }

    @Override
    public String toString() {
        StringBuilder contactList = new StringBuilder();
        contactList.append("[");
        for(String name: this.getContactsName()){
            contactList.append(name);
            contactList.append(", ");
        }
        if(contactList.length()>2)contactList.delete(contactList.length() - 2, contactList.length());
        contactList.append("]");
        return contactList.toString();
    }
}
