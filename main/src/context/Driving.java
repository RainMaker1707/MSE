package context;

import database.ContextsDB;

public class Driving extends Context{

    public Driving(){
        super("Driving", ContextsDB.INSTANCE.get("driving"));
    }

}
