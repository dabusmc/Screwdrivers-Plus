package gooblemc;

import gooblemc.screwdrivers_plus.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ScrewdriversPlus {

    public static final String MOD_ID = "screwdrivers_plus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ModItems.TABS.registerToModBus();
        ModItems.ITEMS.registerToModBus();
    }

}
