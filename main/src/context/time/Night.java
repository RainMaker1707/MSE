package context.time;

import behaviour.ContextBehavior;
import context.Context;
import database.ContextsDB;

public class Night extends Context {
    public Night() {
        super("night", ContextsDB.INSTANCE.get("night"));
    }
}
