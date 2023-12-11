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
    String state;
    boolean isGroup;
    FeatureBehavior behavior = Features.INSTANCE.get("notification");

    public Notification(){conversation = null;}

    public Notification(Contact sender, Contact receiver, Conversation conversation, boolean isGroup){
        this.conversation = conversation;
        this.sender = sender;
        this.receiver = receiver;
        this.state = "sending";
        this.isGroup = isGroup;
    }

    public Contact getSender() {
        return this.sender;
    }

    public Contact getReceiver() {
        return this.receiver;
    }

    public Conversation getConversation() {
        return this.conversation;
    }

    public String getState() {
        return state;
    }

    public void send(){
        this.receive();
    }

    public void receive(){
        if(this.receiver != null && this.receiver.getStatus() == Status.online)
            this.state = "received";
    }

    public boolean isGroup() {
        if (this.isGroup) {
            return true;
        }
        return false;
    }

    @Override
    public void activate() {

    }
}