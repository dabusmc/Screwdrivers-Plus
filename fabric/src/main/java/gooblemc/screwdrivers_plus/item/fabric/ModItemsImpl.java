package gooblemc.screwdrivers_plus.item.fabric;

import gooblemc.screwdrivers_plus.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import whocraft.tardis_refined.registry.RegistrySupplier;

public class ModItemsImpl {

    public static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SONIC_SCREWDRIVER.get()))
            .title(Component.translatable("itemgroup.screwdrivers_plus")).displayItems((displayContext, entries) -> {
                for (RegistrySupplier<Item> tabItem : ModItems.TAB_ITEMS) {
                    entries.accept(tabItem.get());
                }
            })
            .build();

    public static CreativeModeTab getCreativeTab() {
        return ITEM_GROUP;
    }


}