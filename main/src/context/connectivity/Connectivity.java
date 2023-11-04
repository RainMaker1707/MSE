package context.connectivity;


import context.Context;

public abstract class Connectivity extends Context {

    public Connectivity(){
        super("Connectivity");
        this.activate();
    }
}
