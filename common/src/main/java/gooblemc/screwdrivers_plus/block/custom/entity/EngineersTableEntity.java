package gooblemc.screwdrivers_plus.block.custom.entity;

import gooblemc.ScrewdriversPlus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class EngineersTableEntity extends BlockEntity {

    public EngineersTableEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.ENGINEERS_TABLE.get(), blockPos, blockState);
    }

    public static class Ticker<T extends BlockEntity> implements BlockEntityTicker<T> {

        @Override
        public void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
            ScrewdriversPlus.LOGGER.info("Ticker at " + blockPos.toShortString() + " with state " + blockState.toString() + " and entity type " + blockEntity.getClass().getName());
        }

    }

}
