package features.contact;

import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class HiddenContact extends Feature{
    FeatureBehavior behavior = Features.INSTANCE.get("hide");

    Contact owner;

    public HiddenContact(){}
    public HiddenContact(Contact owner) {
        this.owner = owner;
    }

    public void hidContact(Contact contact){
        owner.addHidden(contact);
    }

    public void unhidContact(Contact contact){
        owner.removeHidden(contact);
    }

    @Override
    public void activate() {

    }
}
