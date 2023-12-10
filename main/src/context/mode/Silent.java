package context.mode;


import context.Context;
import database.ContextsDB;

public class Silent extends Context {
    public Silent() {
        super("silent", ContextsDB.INSTANCE.get("silent"));
    }
}
