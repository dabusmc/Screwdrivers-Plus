package gooblemc.screwdrivers_plus.block.custom.entity;

import gooblemc.ScrewdriversPlus;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

import java.util.function.Supplier;

public class ModBlockEntityTypes {

    public static final DeferredRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegistry.create(ScrewdriversPlus.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static RegistrySupplier<BlockEntityType<EngineersTableEntity>> ENGINEERS_TABLE;
    public static RegistrySupplier<BlockEntityType<ChameleonBoxEntity>> CHAMELEON_BOX;

    public static void registerToBus() {
        BLOCK_ENTITY_TYPES.registerToModBus();
    }

    // Generic registration helper
    public static <T extends BlockEntityType<?>> RegistrySupplier<T> register(String id, Supplier<T> blockEntity) {
        return BLOCK_ENTITY_TYPES.register(id, blockEntity);
    }

}
