package commands;

import behaviour.ContextBehavior;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Conversation;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class ConversationCmd extends Command{

    public ConversationCmd(List<ContextBehavior> contexts, String command){
        super("conversations", contexts, command);
    }

    @Override
    public void run() {
        Contact user = LoggedIn.INSTANCE.get();
        if(user == null){
            error("no user logged in");
            return;
        }
        if(!this.getArguments(true).isEmpty()){
            error("doesn't need argument");
            return;
        }
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
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }

    @Override
    public void help() {

    }
}
