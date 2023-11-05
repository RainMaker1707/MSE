package features.themes;

import constant.Colors;
import database.Features;
import features.FeatureBehavior;

public class Dark {
    static FeatureBehavior behavior = Features.INSTANCE.get("dark");

    public static void activate(){
        if(behavior.isActivated()) {
            System.out.print(Colors.BG_BLACK + Colors.ANSI_WHITE);
            Colors.currentColor = Colors.ANSI_WHITE;
        }
    }
}
