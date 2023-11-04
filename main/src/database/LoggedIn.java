package database;

import constant.Colors;
import features.profile.Profile;

public enum LoggedIn {

    INSTANCE();

    Profile loggedIn = null;

    public void setLoggedIn(Profile profile){
        if(loggedIn != null)
            System.out.println(Colors.ANSI_RED + "A user is already logged in: " + loggedIn.getName() + "!"+Colors.ANSI_RESET);
        else{
            loggedIn = profile;
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

}
