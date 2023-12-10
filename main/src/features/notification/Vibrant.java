package features.notification;

import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Vibrant extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("vibrant");

    public static void activate(){
        if(behavior.isActivated()) {
            return;
        }
    }
    
}
