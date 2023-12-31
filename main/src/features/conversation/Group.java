package features.conversation;


import features.contact.Contact;
import features.message.Message;
import features.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class Group extends Conversation{
    private Contact owner;
    private List<Contact> members;
    private String groupName;
    List<Message> groupConversation;


    public Group(){}

    public Group(String groupName,Contact owner, Contact c2){
        super(owner, c2, true);
        this.groupName = groupName;
        this.owner = owner;
        this.members = new ArrayList<>();
        this.groupConversation = new ArrayList<>();
        this.addToGroup(owner);
        if(c2 != null) this.addToGroup(c2);
    }

    public String getGroupName() {
        return this.groupName;
    }

    public Contact getOwner(){
        return this.owner;
    }

    public List<Contact> getMembers(){
        return this.members;
    }

    public void addToGroup(Contact c){
        members.add(c);
        c.addGroup(this);
    }

    public void removeToGroup(Contact c){
        //TODO implement roles to check if requester has enough right
        members.remove(c);
        c.removeGroup(this);
    }

    public void quitGroup(Contact c){
        this.members.remove(c);
        c.removeGroup(this);
    }

    @Override
    public void sendMessage(Message message, Notification notification){
        this.groupConversation.add(message);
        this.notifications.add(notification);
        message.send();
        notification.send();
    }

    @Override
    public List<Message> getMessages() {
        return this.groupConversation;
    }

    public boolean delete(Contact requester){
        if(requester.getName().equals(owner.getName())){
            for(Contact contact: members){
                contact.removeGroup(this);
            }
            return true;
        }
        return false;
    }

}
