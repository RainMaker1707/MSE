package smartMessagingSystem;

import context.*;
import commands.*;
import features.contact.*;
import features.conversation.Conversation;
import features.message.*;
import features.profile.Profile;


class SmartMessagingSystem{

    Context context;
    Commands commands;

    public SmartMessagingSystem(Context context, Commands commands){
        this.context = context;
        this.commands = commands;
    }

    //Tests only for now
    /* TODO read input to know which user we are
        create profiles from DB
        really send messages
    */
    public static void main(String[] args) {
        System.out.println("Welcome in SMS\n\n");
        System.out.println("Creating list of contact");
        Contact user = new Contact("Bob", null);
        user.changeStatus(); // Bob is online

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
        whiteHat.changeStatus(); //whiteHat is online
        System.out.println("Contact List white hat is online");
        System.out.println(user.getContactList()+"\n");


        // Test first message
        Conversation conv = new Conversation(user, whiteHat);
        conv.sendMessage(new TextMessage(user, whiteHat, "Hello from bob", conv));
        System.out.println("Conversation 1 history:");
        for(Message message: conv.getMessages()) System.out.println(message);

        // Test offline user don't change the status for received
        Conversation conv2 = new Conversation(user, alice);
        conv2.sendMessage(new TextMessage(user, alice, "Hello alice", conv2));
        System.out.println("\nConversation 2 history (alice offline):");
        for(Message message: conv2.getMessages()) System.out.println(message);
        // Test now change because alice is online
        alice.changeStatus();
        System.out.println("\nConversation 2 history (alice online):");
        for(Message message: conv2.getMessages()) System.out.println(message);
        conv2.sendMessage(new TextMessage(alice, user, "Hello Bob", conv2));
        alice.changeStatus(); // alice is now offline
        conv2.sendMessage(new TextMessage(user, alice, "can you help me to implement a Kruskal", conv2));
        for(Message message: conv2.getMessages()) System.out.println(message);
    }
}