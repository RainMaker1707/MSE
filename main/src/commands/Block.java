package commands;

import context.Context;
import database.Features;
import database.LoggedIn;
import features.FeatureBehavior;
import features.contact.BlockContact;
import features.contact.Contact;

import java.util.List;

public class Block extends Command{
    public Block(List<Context> contexts, String command){
        super("block", contexts, command);
    }
    @Override
    public void run() {
        if(!Features.INSTANCE.get("block").isActivated()) error("Feature block is not activated");
        else if(LoggedIn.INSTANCE.get() == null) error("No user logged in");
        else{
            List<String> args = this.getArguments(false);
            for(String arg: args){
                if(!LoggedIn.INSTANCE.get().getContactList().getContacts().stream()
                        .map(Contact::getName).toList().contains(arg))
                    error(LoggedIn.INSTANCE.get() + " has no contact " + arg);
                else{
                    int toBlockIndex = LoggedIn.INSTANCE.get().getContactList().getContacts().stream()
                            .map(Contact::getName).toList().indexOf(arg);
                    Contact toBlock = LoggedIn.INSTANCE.get().getContactList().getContacts().get(toBlockIndex);
                    BlockContact blocker = new BlockContact(LoggedIn.INSTANCE.get());
                    blocker.blockContact(toBlock);
                    feedback(arg + " has been blocked!");
                }
            }
        }
    }

    @Override
    public void help(){

    }
}