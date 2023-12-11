package features.notification;

import constant.Colors;
import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Vibrant extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("vibrant");

    public void activate(){
        if(behavior.isActivated()) {
            System.out.println(Colors.ANSI_BLUE + "*Vibrant*" + Colors.currentColor);
        }
    }
    
}
