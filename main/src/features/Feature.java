package features;

public abstract class Feature {
    String name;
    boolean activated;

    public Feature(String name){
        this.name = name;
        this.activated = false;
    }

    public void activate(){
        this.activated = true;
    }

    public void deactivate(){
        this.activated = false;
    }
}
