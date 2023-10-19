package smartMessagingSystem;

import context.*;
import commands.*;
import features.contact.Contact;
import features.profile.Profile;


class SmartMessagingSystem{

    Context context;
    Commands commands;

    public SmartMessagingSystem(Context context, Commands commands){
        this.context = context;
        this.commands = commands;
    }

    public static void main(String[] args) {
        System.out.println("Welcome in SMS\n\n");
        System.out.println("Creating list of contact");
        Profile user = new Profile("Bob", null);

        Contact alice = new Contact("Alice", null);
        Contact whiteHat = new Contact("White Hat", null);
        Contact blackHat = new Contact("Black Hat", null);

        System.out.println("Contact List before add");
        System.out.println(user.getContactList()+"\n");

        user.addContact(alice);
        user.addContact(whiteHat);
        user.addContact(blackHat);
        System.out.println("Contact List after add 3 contacts");
        System.out.println(user.getContactList()+"\n");

        user.blockContact(blackHat);
        System.out.println("Contact List after blocking Black Hat");
        System.out.println(user.getContactList()+"\n");


        System.out.println(alice.getName());


    }
}