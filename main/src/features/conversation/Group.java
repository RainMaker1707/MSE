package features.conversation;


import features.contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class Group extends Conversation{
    private final Contact owner;
    private final List<Contact> members;
    public Group(Contact owner, Contact c2){
        super(owner, c2);
        this.owner = owner;
        this.members = new ArrayList<>();
    }

    public List<Contact> getMembers(){
        return this.members;
    }

    public void addToGroup(Contact c){
        members.add(c);
    }

    public void removeToGroup(Contact c){
        //TODO implement roles to check if requester has enough right
        members.remove(c);
    }

    public void quitGroup(Contact c){
        this.members.remove(c);
    }

}
