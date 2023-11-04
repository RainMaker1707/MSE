package features.conversation;

import features.Feature;
import features.contact.Contact;
import features.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Conversation extends Feature {
    Contact contact1;
    Contact contact2;
    List<Message> conversation;

    public Conversation(Contact c1, Contact c2){
        super("Conversation");
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
