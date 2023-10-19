package smartMessagingSystem;

import context.*;
import commands.*;
import features.contact.Contact;
import features.message.Message;
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

        // Tests contact list manipulation
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

        // Tests change status
        whiteHat.changeStatus();
        System.out.println("Contact List white hat is online");
        System.out.println(user.getContactList()+"\n");


        // Test first message
        Message msg = new Message(user, whiteHat, "Hello from bob");
        System.out.println(msg);

        msg.receive();
        System.out.println(msg+"\n");

        // Test offline user don't change the status for received
        msg = new Message(user, alice, "Hello alice");
        System.out.println(msg+"\n");
        System.out.println("Normally again sent as alice is offline");
        msg.receive();
        System.out.println(msg+"\n");
        // Test now change because alice is online
        alice.changeStatus();
        System.out.println("Now received because alice is online");
        msg.receive();
        System.out.println(msg);
    }
}