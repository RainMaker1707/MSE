package features.notification;

import constant.Colors;
import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Silent extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("silent");

    public void activate(){
        if(behavior.isActivated()) {
            System.out.println(Colors.ANSI_BLUE + "*Silent*" + Colors.currentColor);
        }
    }
    
}
