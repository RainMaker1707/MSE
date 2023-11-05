package features;

public interface Feature {

    void setMandatory(boolean value);
    void setAlternative(boolean value);
    void setOptional(boolean value);
    void activate();
    void deactivate();
    boolean getMandatory();
    boolean getAlternative();
    boolean getOptional();
    String getName();
    public boolean isActivated();
}
