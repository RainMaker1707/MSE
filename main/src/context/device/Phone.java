package context.device;

import context.Context;
import database.ContextsDB;

public class Phone extends Context {
    public Phone() {
        super("phone", ContextsDB.INSTANCE.get("phone"));
    }
}
