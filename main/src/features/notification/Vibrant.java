package features.notification;

import database.Features;
import features.FeatureBehavior;

public class Vibrant {
    static FeatureBehavior behavior = Features.INSTANCE.get("vibrant");

    public static void activate(){
        if(behavior.isActivated()) {
            return;
        }
    }
    
}
