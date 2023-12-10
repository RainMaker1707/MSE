package context.mode;

import context.Context;
import database.ContextsDB;

public class Flight extends Context {
    public Flight() {
        super("flight", ContextsDB.INSTANCE.get("flight"));
    }
}
