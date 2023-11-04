package context.mode;

import context.Context;

public abstract class Mode extends Context {
    public Mode(){
        super("Mode");
        this.activate();
    }
}
