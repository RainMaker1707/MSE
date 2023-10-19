package features.message;

import features.contact.Contact;
import features.profile.Profile;

public class TextMessage extends Message{
    String content;
    public TextMessage(Profile sender, Contact receiver, String content){
        super(sender, receiver);
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
