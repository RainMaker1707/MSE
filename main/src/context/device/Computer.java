package context.device;

import context.Context;
import database.ContextsDB;

public class Computer extends Context {
    public Computer() {
        super("computer", ContextsDB.INSTANCE.get("computer"));
    }
}
