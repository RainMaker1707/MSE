package behaviour;

import database.ContextsDB;

import java.util.HashMap;

public class ContextBehavior implements Behaviour{

    private final String name;
    boolean mandatory = true;
    boolean alternative = false;
    boolean optional = false;
    boolean activate = true;

    HashMap<String, ContextBehavior> alternativesSet;

    public ContextBehavior(String linkedContextName, String type) {
        this.name = linkedContextName;
        switch(type){
            case "mandatory": {
                this.setMandatory(true);
                this.activate();
                break;
            }
            case "alternative": {
                this.setAlternative(true);
                alternativesSet = new HashMap<>();
                this.activate = false;
                break;
            }
            case "optional": {
                this.setOptional(true);
                this.deactivate();
                break;
            }
            default: throw new IllegalArgumentException("ContextBehavior type: " + type
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
        if(this.getAlternative()){
            for(ContextBehavior alt: this.getAlternativesSet().values()){
                if(alt.isActivated()) alt.deactivate();
            }
        }
    }

    @Override
    public void deactivate() {
        if(this.getAlternative()){
            boolean flag = false;
            for(ContextBehavior f: this.getAlternativesSet().values()) {
                if(f.isActivated()) {
                    flag= true;
                    break;
                }
            }
            if(!flag) this.getAlternativesSet().values().stream().toList().get(0).activate();
        }
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

    @Override
    public void addAlternativeFeature(String name){
        ContextBehavior alt = ContextsDB.INSTANCE.get(name);
        if(alt == null) throw new IllegalArgumentException( name + " is not a feature, can't be set as alternative");
        alternativesSet.put(name, alt);
    }

    public HashMap<String, ContextBehavior> getAlternativesSet(){return alternativesSet;}
}