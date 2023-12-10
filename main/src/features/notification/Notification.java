package features.notification;

import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;
import features.contact.*;
import features.conversation.Conversation;
import static features.notification.NotificationState.*;


public class Notification extends Feature {
    private final Conversation conversation;
    Contact sender;
    Contact receiver;
    NotificationState state;
    FeatureBehavior behavior = Features.INSTANCE.get("notification");

    public Notification(){conversation = null;}

    public Notification(Contact sender, Contact receiver, Conversation conversation){
        this.conversation = conversation;
        this.sender = sender;
        this.receiver = receiver;
        this.state = sending;
    }

    public Contact getSender() {
        return this.sender;
    }

    public Contact getReceiver() {
        return this.receiver;
    }

    public NotificationState getState() {
        return state;
    }

    public void send(){
        this.receive();
    }

    public void receive(){
        if(this.receiver.getStatus() == Status.online)
            this.state = received;
    }

    @Override
    public void activate() {

    }
}
