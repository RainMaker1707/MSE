package context;

import database.ContextsDB;

public class Meeting extends Context{
    public Meeting(){
        super("Meeting", ContextsDB.INSTANCE.get("meeting"));
    }
}
