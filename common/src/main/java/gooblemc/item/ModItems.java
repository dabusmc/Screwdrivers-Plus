package gooblemc.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gooblemc.ScrewdriversPlus;
import gooblemc.item.custom.SonicItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ScrewdriversPlus.MOD_ID, RegistryKeys.ITEM);

    public static final RegistrySupplier<Item> SONIC_ITEM = register("sonic_screwdriver", () -> new SonicItem(new Item.Settings().maxCount(1)));

    private static RegistrySupplier<Item> register(String name, Supplier<Item> item) {
        RegistrySupplier<Item> supplier = ITEMS.register(name, item);
        return supplier;
    }

}
