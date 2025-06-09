package gooblemc;

import gooblemc.screwdrivers_plus.block.ModBlocks;
import gooblemc.screwdrivers_plus.commands.ModCommands;
import gooblemc.screwdrivers_plus.item.ModItems;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import gooblemc.screwdrivers_plus.upgrades.SonicUpgrades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ScrewdriversPlus {

    public static final String MOD_ID = "screwdrivers_plus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        SonicUpgrades.init();

        ModItems.TABS.registerToModBus();
        ModBlocks.BLOCKS.registerToModBus();
        ModItems.ITEMS.registerToModBus();

        CommandRegistrationEvent.EVENT.register((dispatcher, registryAccess, environment) -> {
            ModCommands.register(dispatcher);
        });
    }

}
