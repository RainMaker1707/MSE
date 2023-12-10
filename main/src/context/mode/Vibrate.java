package context.mode;


import context.Context;
import database.ContextsDB;

public class Vibrate extends Context {
    public Vibrate() {
        super("vibrate", ContextsDB.INSTANCE.get("vibrate"));
    }
}
