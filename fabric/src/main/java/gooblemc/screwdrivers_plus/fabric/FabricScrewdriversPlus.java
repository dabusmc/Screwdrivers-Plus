package gooblemc.screwdrivers_plus.fabric;

import gooblemc.ScrewdriversPlus;
import gooblemc.screwdrivers_plus.fabric.block.entity.FabricModBlockEntityTypes;
import net.fabricmc.api.ModInitializer;

public class FabricScrewdriversPlus implements ModInitializer {

	public static final String MOD_ID = "screwdrivers_plus";

	@Override
	public void onInitialize() {
		ScrewdriversPlus.init();
		FabricModBlockEntityTypes.initBlockEntityTypes();
		registerConfig();
	}

	public static void registerConfig() {
	}


}