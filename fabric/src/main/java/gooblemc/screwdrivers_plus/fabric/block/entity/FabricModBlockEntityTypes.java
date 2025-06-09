package gooblemc.screwdrivers_plus.fabric.block.entity;

import gooblemc.screwdrivers_plus.block.ModBlocks;
import gooblemc.screwdrivers_plus.block.custom.entity.ChameleonBoxEntity;
import gooblemc.screwdrivers_plus.block.custom.entity.EngineersTableEntity;
import gooblemc.screwdrivers_plus.block.custom.entity.ModBlockEntityTypes;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class FabricModBlockEntityTypes extends ModBlockEntityTypes {

    public static void initBlockEntityTypes() {
        ENGINEERS_TABLE = register("engineers_table", () -> FabricBlockEntityTypeBuilder.create(EngineersTableEntity::new, ModBlocks.ENGINEERS_TABLE.get()).build());
        CHAMELEON_BOX = register("chameleon_box", () -> FabricBlockEntityTypeBuilder.create(ChameleonBoxEntity::new, ModBlocks.CHAMELEON_BOX.get()).build());

        registerToBus();
    }

}
