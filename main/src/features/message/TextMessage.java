package features.message;

import features.contact.Contact;
import features.conversation.Conversation;

public class TextMessage extends Message {
    String content;

    public TextMessage(){super();}
    public TextMessage(Contact sender, Contact receiver, String content, Conversation conversation){
        super(sender, receiver, conversation);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String toString(){
        return this.sender.getName() + " send to " + this.receiver.getName()
                + ": " + this.content + " -> " + this.state;
    }
}
