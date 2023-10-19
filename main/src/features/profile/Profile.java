package features.profile;


import features.contact.Contact;
import features.contact.ContactList;

import java.awt.*;

public class Profile {
    private final Image picture;
    private final String name;
    private final ContactList contactList;

    public Profile(String name, Image picture){
        this.name = name;
        this.picture = picture;
        this.contactList = new ContactList(this);
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
}
