package features.themes;

import constant.Colors;
import database.Features;
import features.FeatureBehavior;

public class Light {
    static FeatureBehavior behavior = Features.INSTANCE.get("light");

    public static void activate(){
        if(behavior.isActivated()) {
            System.out.print(Colors.BG_WHITE + Colors.ANSI_BLACK);
            Colors.currentColor = Colors.ANSI_BLACK;
        }
    }
}
