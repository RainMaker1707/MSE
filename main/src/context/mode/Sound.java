package context.mode;


import context.Context;
import database.ContextsDB;

public class Sound extends Context {
    public Sound() {
        super("sound", ContextsDB.INSTANCE.get("sound"));
    }
}
