package commands;

import context.Context;
import database.DataBase;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Conversation;
import features.message.Message;
import features.message.TextMessage;

import java.util.List;

public class MessageCmd extends Command {

    public MessageCmd(List<Context> contexts, String command){
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
            error("Need one argument username");
            return;
        }
        Contact recipient = DataBase.INSTANCE.getUser(args.get(0));
        Conversation conversation = null;
        for(Conversation conv: user.getConversations()){
            if(conv.getContact1().equals(recipient) || conv.getContact2().equals(recipient)){
                conversation = conv;
            }
        }
        if(conversation == null){
            error("no conversation with " + recipient.getName() + " found");
            return;
        }
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
    }

    @Override
    public void help() {

    }
}
