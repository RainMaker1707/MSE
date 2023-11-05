package commands;

import context.Context;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Conversation;

import java.util.List;

public class ConversationCmd extends Command{

    public ConversationCmd(List<Context> contexts, String command){
        super("conversation", contexts, command);
    }

    @Override
    public void run() {
        Contact user = LoggedIn.INSTANCE.get();
        StringBuilder text = new StringBuilder();
        text.append(user.getName());
        text.append("'s conversations: [");
        boolean flag = false;
        for(Conversation conv: user.getConversations()){
            if(user.equals(conv.getContact1())) {
                text.append(conv.getContact2().getName());
                text.append(", ");
                flag = true;
            }else{
                text.append(conv.getContact1().getName());
                text.append(", ");
                flag = true;
            }
        }
        if(flag){
            text.delete(text.length()-2, text.length());
            text.append("]");
        }else{
            text.delete(text.length()-1, text.length());
            text.append("No conversation yet");
        }
        feedback(text.toString());
    }

    @Override
    public void help() {

    }
}
