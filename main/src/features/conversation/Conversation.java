package features.conversation;

import database.Features;
import features.Feature;
import features.FeatureBehavior;
import features.contact.Contact;
import features.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Conversation{
    Contact contact1;
    Contact contact2;
    List<Message> conversation;

    FeatureBehavior behavior = Features.INSTANCE.get("conversation");

    public Conversation(Contact c1, Contact c2){
        this.contact1 = c1;
        this.contact2 = c2;
        this.conversation = new ArrayList<>();
        c1.addConversation(this);
        c2.addConversation(this);
    }

    public void sendMessage(Message message){
        this.conversation.add(message);
        message.send();
    }

    public Contact getContact1(){
        return this.contact1;
    }
    public Contact getContact2(){
        return this.contact2;
    }

    public void removeMessage(Message message){
        //TODO implement logic to check if request comes from the sender
        this.conversation.remove(message);
    }

    public List<Message> getMessages() {
        return this.conversation;
    }

    public void updateStatus() {
        for(Message m: this.getMessages()) m.receive();
    }
}
