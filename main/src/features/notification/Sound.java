package features.notification;

import database.Features;
import features.FeatureBehavior;

public class Sound {
    static FeatureBehavior behavior = Features.INSTANCE.get("sound");

    public static void activate(){
        if(behavior.isActivated()) {
            return;
        }
    }
    
}
