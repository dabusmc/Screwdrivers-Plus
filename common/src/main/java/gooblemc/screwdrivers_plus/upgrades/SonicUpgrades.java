package gooblemc.screwdrivers_plus.upgrades;

import java.util.HashMap;
import java.util.Map;

public class SonicUpgrades {

    private static Map<String, SonicUpgrade> s_Upgrades;

    public static void init() {
        s_Upgrades = new HashMap<>();
        s_Upgrades.put("sonic_upgrade_igniter", new IgniterUpgrade());
        s_Upgrades.put("sonic_upgrade_snipper", new SnipperUpgrade());
    }

    public static String[] getKeys() {
        return s_Upgrades.keySet().toArray(new String[0]);
    }

    public static SonicUpgrade getUpgradeFromName(String name) {
        if(s_Upgrades.containsKey(name)) {
            return s_Upgrades.get(name);
        }

        return null;
    }

    public static SonicUpgrade getUpgradeFromSerializedName(String name) {
        String upgrade = "sonic_upgrade_" + name;
        return getUpgradeFromName(upgrade);
    }

}
