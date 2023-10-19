package features.contact;


import features.profile.*;
import java.awt.*;

public class Contact extends Profile{

    private Status status;

    public Contact(String name, Image picture){
        super(name, picture);
        this.status = Status.offline;
    }

    public Status getStatus() {
        return this.status;
    }

    public void changeStatus() {
        if(this.status == Status.online) this.status = Status.offline;
        else this.status = Status.online;
    }
}
