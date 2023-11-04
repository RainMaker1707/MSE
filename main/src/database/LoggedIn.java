package database;

import constant.Colors;
import features.contact.Contact;
import features.profile.Profile;

public enum LoggedIn {

    INSTANCE();

    Contact loggedIn = null;

    public void setLoggedIn(Contact contact){
        if(loggedIn != null)
            System.out.println(Colors.ANSI_RED + "A user is already logged in: " + loggedIn.getName() + "!"+Colors.ANSI_RESET);
        else{
            loggedIn = contact;
        }
    }

    public void logout(){
        if(loggedIn != null){
            System.out.println(Colors.ANSI_BLUE + "Now disconnected from " + loggedIn.getName() + " account." + Colors.ANSI_RESET);
            loggedIn = null;
        }else{
            System.out.println(Colors.ANSI_RED + "No user connected yet!"+Colors.ANSI_RESET);
        }
    }

    public Contact get(){
        return this.loggedIn;
    }

}
