package features.notification;

import database.Features;
import features.FeatureBehavior;

public class Silent {
    static FeatureBehavior behavior = Features.INSTANCE.get("silent");

    public static void activate(){
        if(behavior.isActivated()) {
            return;
        }
    }
    
}
