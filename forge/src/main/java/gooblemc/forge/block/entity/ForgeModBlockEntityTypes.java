package gooblemc.forge.block.entity;

import gooblemc.screwdrivers_plus.block.ModBlocks;
import gooblemc.screwdrivers_plus.block.custom.entity.ChameleonBoxEntity;
import gooblemc.screwdrivers_plus.block.custom.entity.EngineersTableEntity;
import gooblemc.screwdrivers_plus.block.custom.entity.ModBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ForgeModBlockEntityTypes extends ModBlockEntityTypes {

    public static void initBlockEntityTypes() {
        ENGINEERS_TABLE = register("engineers_table", () -> BlockEntityType.Builder.of(EngineersTableEntity::new, ModBlocks.ENGINEERS_TABLE.get()).build(null));
        CHAMELEON_BOX = register("chameleon_box", () -> BlockEntityType.Builder.of(ChameleonBoxEntity::new, ModBlocks.CHAMELEON_BOX.get()).build(null));

        registerToBus();
    }

}
