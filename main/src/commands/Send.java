package commands;

import context.Context;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Conversation;
import features.message.Message;
import features.message.TextMessage;
import features.profile.Profile;
import org.w3c.dom.Text;

import java.util.List;

public class Send extends Command{

    public Send(List<Context> contexts, String command){
        super("send", contexts, command);
    }

    @Override
    public void run() {
        List<String> args = this.getArguments(false);
        if(args.size() < 2) error("Send need two arguments. An username and a message (at least one word)");
        else{
            Contact user = LoggedIn.INSTANCE.get();
            List<Contact> contacts = user.getContactList().getContacts();
            Contact recipient = null;
            for(Contact contact: contacts) {
                if(contact.getName().equals(args.get(0))) recipient = contact;
            }
            if(recipient == null) error("Contact list doesn't have this contact: " + args.get(0));
            else{
                String text = createMessage(args);
                Conversation conversation = getConversation(user, recipient);
                TextMessage msg = new TextMessage(user, recipient, text, conversation);
                if(conversation != null ) {
                    conversation.sendMessage(msg);
                    feedback("Message sent");
                }
                else error("No conversation to send message");
            }
        }
    }

    public String createMessage(List<String> args){
        args.remove(0);
        StringBuilder text = new StringBuilder();
        for(String arg: args) {
            if(arg.equals(args.get(0)) || arg.equals(args.get(args.size()-1)))
                arg = arg.replace("\"", "").replace("'", "");
            text.append(arg).append(" ");
        }
        return text.toString();
    }

    public Conversation getConversation(Contact user, Contact recipient){
        Conversation conversation = null;
        if(!user.getConversations().stream().map(Conversation::getContact1).toList().contains(recipient)
                && !user.getConversations().stream().map(Conversation::getContact2).toList().contains(recipient))
        {
            // create conversation
            conversation = new Conversation(user, recipient);
            user.addConversation(conversation);
            recipient.addConversation(conversation);
        }else {
            // conversation already exist
            for(Conversation conv: user.getConversations()){
                if(conv.getContact1().equals(recipient) || conv.getContact2().equals(recipient)){
                    conversation = conv;
                    break;
                }
            }
        }
        return conversation;
    }

    @Override
    public void help() {

    }
}
