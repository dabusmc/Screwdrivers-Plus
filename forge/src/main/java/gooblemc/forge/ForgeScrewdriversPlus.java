package gooblemc.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import gooblemc.ScrewdriversPlus;

@Mod(ScrewdriversPlus.MOD_ID)
public final class ForgeScrewdriversPlus {

    public ForgeScrewdriversPlus() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(ScrewdriversPlus.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        ScrewdriversPlus.init();
    }

}
