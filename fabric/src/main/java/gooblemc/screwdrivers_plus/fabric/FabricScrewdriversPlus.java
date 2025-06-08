package gooblemc.screwdrivers_plus.fabric;

import gooblemc.ScrewdriversPlus;
import net.fabricmc.api.ModInitializer;

public class FabricScrewdriversPlus implements ModInitializer {

	public static final String MOD_ID = "screwdrivers_plus";

	@Override
	public void onInitialize() {
		ScrewdriversPlus.init();
		registerConfig();
	}

	public static void registerConfig() {
	}


}