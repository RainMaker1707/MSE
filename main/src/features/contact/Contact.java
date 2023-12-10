package features.contact;


import database.Features;
import behaviour.FeatureBehavior;
import features.conversation.Conversation;
import features.profile.*;

import java.awt.*;

public class Contact extends Profile{

    private Status status;
    FeatureBehavior behavior = Features.INSTANCE.get("contact");

    public Contact(){}

    public Contact(String name, Image picture){
        super(name, picture);
        this.status = Status.offline;
    }

    public Contact(Profile profile){
        super(profile.getName(), profile.getPicture());
        this.status = Status.offline;
    }

    public Status getStatus() {
        return this.status;
    }

    public void changeStatus() {
        if(this.status == Status.online) this.status = Status.offline;
        else this.status = Status.online;
        for(Conversation c: this.getConversations()) c.updateStatus();
    }
}
