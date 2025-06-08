package gooblemc.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import gooblemc.ScrewdriversPlus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ScrewdriversPlus.MOD_ID)
public final class ForgeScrewdriversPlus {

    public ForgeScrewdriversPlus() {
        ScrewdriversPlus.init();

        // Submit our event bus to let Architectury API register our content on the right time.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register setup events
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);
    }


    private void onCommonSetup(FMLCommonSetupEvent event) {
        // Common setup code here
    }

    private void onClientSetup(FMLClientSetupEvent event) {
    }

}
