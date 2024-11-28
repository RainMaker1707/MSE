package context.device;

import context.Context;
import database.ContextsDB;

public class Tablet extends Context {
    public Tablet() {
        super("tablet", ContextsDB.INSTANCE.get("tablet"));
    }
}
