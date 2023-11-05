package features.contact;


import constant.Colors;
import database.Features;
import features.FeatureBehavior;

import java.util.ArrayList;
import java.util.List;

public class BlockContact{
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
