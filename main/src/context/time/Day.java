package context.time;

import context.Context;
import database.ContextsDB;

public class Day extends Context {
    public Day() {
        super("day", ContextsDB.INSTANCE.get("day"));
    }
}
