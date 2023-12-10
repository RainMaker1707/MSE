package context.connectivity;

import context.Context;
import database.ContextsDB;

public class MobileData extends Context {
    public MobileData() {
        super("mobiledata", ContextsDB.INSTANCE.get("mobiledata"));
    }
}
