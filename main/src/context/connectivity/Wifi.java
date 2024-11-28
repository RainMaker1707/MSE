package context.connectivity;

import context.Context;
import database.ContextsDB;

public class Wifi extends Context {
    public Wifi() {
        super("wifi", ContextsDB.INSTANCE.get("wifi"));
    }
}
