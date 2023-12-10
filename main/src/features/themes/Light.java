package features.themes;

import constant.Colors;
import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Light extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("light");

    public static void activate(){
        if(behavior.isActivated()) {
            System.out.print(Colors.BG_LIGHT + Colors.ANSI_BLACK);
            Colors.currentColor = Colors.ANSI_BLACK;
        }
    }
}
