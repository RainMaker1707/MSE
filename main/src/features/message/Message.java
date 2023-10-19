package features.message;

import features.contact.*;
import features.profile.*;
import static features.message.MessageState.*;

public class Message {
    Profile sender;
    Contact receiver;
    MessageState state;

    //TODO add conversation to know in which conversation we are
    public Message(Profile sender, Contact receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.state = sending;
        this.send();
    }

    public Profile getSender() {
        return this.sender;
    }

    public Contact getReceiver() {
        return this.receiver;
    }

    public void send(){
        //TODO send the message to the receiver user
        this.state = sent;
    }

    public void receive(){
        if(this.state == sent && this.receiver.getStatus() == Status.online)
            this.state = received;
    }

    public void seen(){
        // TODO logic when receiver has read the message
    }
}
