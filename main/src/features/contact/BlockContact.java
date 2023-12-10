package features.contact;


import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class BlockContact extends Feature {
    FeatureBehavior behavior = Features.INSTANCE.get("block");

    Contact owner;
    public BlockContact(Contact owner) {
        this.owner = owner;
    }

    public void blockContact(Contact contact){
        owner.removeContact(contact);
        contact.removeContact(owner);
        owner.addBlocked(contact);
    }

    public void unblockContact(Contact contact){
        owner.removeBlocked(contact);
        owner.addContact(contact);
    }
}
