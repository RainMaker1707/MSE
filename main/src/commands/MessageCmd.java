package commands;

import behaviour.ContextBehavior;
import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Conversation;
import features.conversation.Group;
import features.message.Message;
import features.message.TextMessage;
import smartMessagingSystem.SmartMessagingSystem;

import javax.swing.*;
import java.util.List;

public class MessageCmd extends Command {

    public MessageCmd(List<ContextBehavior> contexts, String command){
        super("messages", contexts, command);
    }
    @Override
    public void run() {
        Contact user = LoggedIn.INSTANCE.get();
        if(user==null){
            error("No user logged in");
            return;
        }
        List<String> args = this.getArguments(false);
        if(args.size() != 1){
            error("Need one argument username or groupname");
            return;
        }
        String name = args.get(0);
        Contact recipient = DataBase.INSTANCE.getUser(name);
        Group groupRecipient = DataBase.INSTANCE.getGroup(name);
        Conversation conversation = null;
        for(Conversation conv: user.getConversations()){
            if(conv.getContact1().equals(recipient) || conv.getContact2().equals(recipient)){
                conversation = conv;
            }
        }
        if(conversation == null && groupRecipient == null){
            error("no conversation with Contact or Group found");
            return;
        }
        if (conversation != null) {
            StringBuilder toPrint = new StringBuilder();
            for(Message msg: conversation.getMessages()){
                if(msg instanceof TextMessage m){
                    m.seen();
                    toPrint.append(m.getSender().getName());
                    toPrint.append(": ");
                    toPrint.append(m.getContent());
                    toPrint.append(" --> ");
                    toPrint.append(m.getState());
                    toPrint.append("\n");
                }
            }
            if(toPrint.isEmpty()) toPrint.append("No message yet with ").append(recipient.getName());
            feedback(toPrint.toString());
        } else {
            StringBuilder toPrint = new StringBuilder();
            for(Message msg: groupRecipient.getMessages()){
                if(msg instanceof TextMessage m){
                    m.seen();
                    toPrint.append(m.getSender().getName());
                    toPrint.append(": ");
                    toPrint.append(m.getContent());
                    toPrint.append(" --> ");
                    toPrint.append(m.getState());
                    toPrint.append("\n");
                }
            }
            if(toPrint.isEmpty()) toPrint.append("No message yet with ").append(groupRecipient.getGroupName());
            feedback(toPrint.toString());
        }
    }

    @Override
    public JPanel gui(SmartMessagingSystem sms) {
        return null;
    }

    @Override
    public void help() {

    }
}
