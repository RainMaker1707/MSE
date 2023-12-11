package features.notification;

import constant.Colors;
import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Sound extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("sound");

    public void activate(){
        if(behavior.isActivated()) {
            System.out.println(Colors.ANSI_BLUE + "*Ding*" + Colors.currentColor);
        }
    }
    
}
