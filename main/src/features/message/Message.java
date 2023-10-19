package features.message;

import features.contact.*;
import features.profile.*;
import static features.message.MessageState.*;

public class Message {
    Profile sender;
    Contact receiver;
    String content;
    MessageState state;

    //TODO add conversation to know in which conversation we are
    public Message(Profile sender, Contact receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.state = sending;
        this.send();
    }

    public Profile getSender() {
        return this.sender;
    }

    public Contact getReceiver() {
        return this.receiver;
    }

    public String getContent() {
        return this.content;
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

    public String toString(){
        return this.sender.getName() + " send to " + this.receiver.getName()
                + ": " + this.content + " -> " + this.state;
    }
}
