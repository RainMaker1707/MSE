package features.notification;

import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Sound extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("sound");

    public static void activate(){
        if(behavior.isActivated()) {
            return;
        }
    }
    
}
