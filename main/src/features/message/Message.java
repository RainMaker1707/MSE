package features.message;

import database.Features;
import database.LoggedIn;
import behaviour.FeatureBehavior;
import features.Feature;
import features.contact.*;
import features.conversation.Conversation;
import static features.message.MessageState.*;

public class Message extends Feature {
    private Conversation conversation;
    Contact sender;
    Contact receiver;
    MessageState state;
    FeatureBehavior behavior = Features.INSTANCE.get("message");

    public Message(){}

    public Message(Contact sender, Contact receiver, Conversation conversation){
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

    public MessageState getState() {
        return state;
    }

    public void send(){
        this.state = sent;
        this.receive();
    }

    public void receive(){
        if(this.state == sent && this.receiver != null && this.receiver.getStatus() == Status.online)
            this.state = received;
    }

    public void seen(){
        // Only triggered by MessageCmd
        if(this.state == received && this.receiver.equals(LoggedIn.INSTANCE.get()))
            this.state = seen;
    }

    public void delete(Contact contact) {
        if(contact.getProfile() == sender)
            this.conversation.removeMessage(this);
    }

    @Override
    public void activate() {

    }
}
