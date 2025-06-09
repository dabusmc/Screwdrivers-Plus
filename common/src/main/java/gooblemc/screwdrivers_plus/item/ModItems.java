package gooblemc.screwdrivers_plus.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import gooblemc.ScrewdriversPlus;
import gooblemc.screwdrivers_plus.item.custom.SonicItem;
import gooblemc.screwdrivers_plus.upgrades.BomberUpgrade;
import gooblemc.screwdrivers_plus.upgrades.SonicUpgradeItem;
import gooblemc.screwdrivers_plus.upgrades.SonicUpgrades;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import whocraft.tardis_refined.common.util.PlatformWarning;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegistry<CreativeModeTab> TABS = DeferredRegistry.create(ScrewdriversPlus.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> MAIN_TAB = TABS.register("main_tab", ModItems::getCreativeTab);

    public static List<RegistrySupplier<Item>> TAB_ITEMS = new ArrayList<>();

    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(ScrewdriversPlus.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> SONIC_SCREWDRIVER = registerItem("sonic_screwdriver",
            () -> new SonicItem(new Item.Properties().stacksTo(1)), true);

    // Upgrades
    public static final RegistrySupplier<Item> BOMBER_UPGRADE = registerItem("sonic_upgrade_bomber",
            () -> new SonicUpgradeItem("bomber", new Item.Properties().stacksTo(1)), true);

    // Generic registration helper
    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item, boolean addToTab) {
        RegistrySupplier<Item> itemSupplier = ITEMS.register(name, item);
        if (addToTab) {
            TAB_ITEMS.add(itemSupplier);
        }
        return itemSupplier;
    }

    @ExpectPlatform
    public static CreativeModeTab getCreativeTab() {
        throw new RuntimeException(PlatformWarning.addWarning(ModItems.class));
    }
}