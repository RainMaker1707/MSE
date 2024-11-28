package database;

import constant.Colors;
import features.contact.Contact;

public enum LoggedIn {

    INSTANCE();

    Contact loggedIn = null;

    public boolean isLoggedIn() {
        return loggedIn != null;
    }

    public void setLoggedIn(Contact contact){
        if(loggedIn != null)
            System.out.println(Colors.ANSI_RED + "A user is already logged in: " + loggedIn.getName() + "!"+Colors.currentColor);
        else{
            loggedIn = contact;
        }
    }

    public void logout(){
        if(loggedIn != null){
            System.out.println(Colors.ANSI_BLUE + "Now disconnected from " + loggedIn.getName() + " account." + Colors.currentColor);
            loggedIn = null;
        }else{
            System.out.println(Colors.ANSI_RED + "No user connected yet!"+Colors.currentColor);
        }
    }

    public Contact get(){
        return this.loggedIn;
    }

}
