package features;

import database.Features;

import java.util.HashMap;

public class FeatureBehavior implements Feature{

    private final String name;
    boolean mandatory = true;
    boolean alternative = false;
    boolean optional = false;
    boolean activate = true;

    HashMap<String, FeatureBehavior> alternativesSet;

    public FeatureBehavior(String linkedFeatureName, String type){
        this.name = linkedFeatureName;
        switch(type){
            case "mandatory": {
                this.setMandatory(true);
                this.activate();
                break;
            }
            case "alternative": {
                this.setAlternative(true);
                this.deactivate();
                alternativesSet = new HashMap<>();
                break;
            }
            case "optional": {
                this.setOptional(true);
                this.deactivate();
                break;
            }
            default: throw new IllegalArgumentException("FeatureBehavior type: " + type
                            + "is not in ['mandatory', 'optional', 'alternative']");
        }
    }

    @Override
    public void setMandatory(boolean value) {
        mandatory = value;
        if(mandatory){
            optional = false;
            alternative = false;
        }
    }

    @Override
    public void setAlternative(boolean value) {
        alternative = value;
        if(alternative){
            optional = false;
            mandatory = false;
        }
    }

    @Override
    public void setOptional(boolean value) {
        optional = value;
        if(optional){
            mandatory = false;
            alternative = false;
        }
    }

    @Override
    public void activate() {
        activate = true;
    }

    @Override
    public void deactivate() {
        activate = false;
    }

    @Override
    public boolean getMandatory() {
        return mandatory;
    }

    @Override
    public boolean getAlternative() {
        return alternative;
    }

    @Override
    public boolean getOptional() {
        return optional;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActivated(){
        return activate;
    }

    public void addAlternativeFeature(String name){
        FeatureBehavior alt = Features.INSTANCE.get(name);
        if(alt == null) throw new IllegalArgumentException( name + " is not a feature, can't be set as alternative");
        alternativesSet.put(name, alt);
    }
}
