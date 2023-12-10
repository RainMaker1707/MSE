package features.notification;

import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Silent extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("silent");

    public static void activate(){
        if(behavior.isActivated()) {
            return;
        }
    }
    
}
