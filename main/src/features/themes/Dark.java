package features.themes;

import constant.Colors;
import database.Features;
import behaviour.FeatureBehavior;
import features.Feature;

public class Dark extends Feature {
    static FeatureBehavior behavior = Features.INSTANCE.get("dark");

    public void activate(){
        if(behavior.isActivated()) {
            System.out.print(Colors.BG_DARK + Colors.ANSI_WHITE);
            Colors.currentColor = Colors.ANSI_WHITE;
        }
    }
}
