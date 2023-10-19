package features.contact;


import features.profile.*;
import java.awt.*;

public class Contact {

    private Status status;
    private final Profile profile;

    public Contact(Profile profile){
        this.status = Status.offline;
        this.profile = profile;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getName() {
        return this.profile.getName();
    }

    public Image getPicture() {
        return this.profile.getPicture();
    }

    public void changeStatus() {
        if(this.status == Status.online) this.status = Status.offline;
        else this.status = Status.online;
    }
}
