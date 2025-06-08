package gooblemc.screwdrivers_plus.block;

import gooblemc.ScrewdriversPlus;
import gooblemc.screwdrivers_plus.block.custom.ChameleonBox;
import gooblemc.screwdrivers_plus.block.custom.EngineersTable;
import gooblemc.screwdrivers_plus.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegistry<Block> BLOCKS = DeferredRegistry.create(ScrewdriversPlus.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> CHAMELEON_BOX = register("chameleon_box",
            () -> new ChameleonBox(BlockBehaviour.Properties.of()),
            new Item.Properties());
    public static final RegistrySupplier<Block> ENGINEERS_TABLE = register("engineers_table",
            () -> new EngineersTable(BlockBehaviour.Properties.of()),
            new Item.Properties());

    // Generic registration helper
    public static RegistrySupplier<Block> register(String id, Supplier<Block> block) {
        RegistrySupplier<Block> blockSupplier = BLOCKS.register(id, block);
        return blockSupplier;
    }

    public static RegistrySupplier<Block> register(String id, Supplier<Block> block, Item.Properties itemProperties) {
        RegistrySupplier<Block> blockSupplier = BLOCKS.register(id, block);

        ModItems.registerItem(id, () -> new BlockItem(blockSupplier.get(), itemProperties), true);

        return blockSupplier;
    }

}
