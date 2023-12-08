package commands;

import java.util.List;

import context.Context;
import database.LoggedIn;
import features.contact.Contact;
import features.conversation.Group;

public class GroupCmd extends Command {
    public GroupCmd(List<Context> contexts, String command){
        super("groups", contexts, command);
    }

    @Override
    public void run() {
        Contact user = LoggedIn.INSTANCE.get();
        if(user == null){
            error("no user logged in");
        } else if(!this.getArguments(true).isEmpty()){
            error("doesn't need argument");
        } else {
            StringBuilder text = new StringBuilder();
            text.append(user.getName());
            text.append("'s groups: [");
            boolean flag = false;
            for(Group group: user.getGroups().getGroups()){
                text.append(group.getGroupName());
                text.append(", ");
                flag = true;
            }
            if(flag){
                text.delete(text.length()-2, text.length());
                text.append("]");
            }else{
                text.delete(text.length()-1, text.length());
                text.append("No group yet");
            }
            feedback(text.toString());
        }
        
    }

    @Override
    public void help() {

    }
}
