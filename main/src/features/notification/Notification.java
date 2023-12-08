package features.notification;

import context.Context;
import database.Features;
import database.LoggedIn;
import features.Feature;
import features.FeatureBehavior;
import features.contact.*;
import features.conversation.Conversation;
import static features.notification.NotificationState.*;


public class Notification{
    private final Conversation conversation;
    Contact sender;
    Contact receiver;
    NotificationState state;
    FeatureBehavior behavior = Features.INSTANCE.get("notification");

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
}
