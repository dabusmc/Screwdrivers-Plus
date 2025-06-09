package gooblemc.screwdrivers_plus.upgrades;

import gooblemc.screwdrivers_plus.item.custom.SonicItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;

public class IgniterUpgrade extends SonicUpgrade {

    @Override
    public boolean useOnBlock(ServerLevel level, UseOnContext context, Player player, ItemStack stack) {
        BlockState state = level.getBlockState(context.getClickedPos());

        if(state.getBlock() instanceof TntBlock) {
            level.removeBlock(context.getClickedPos(), false);
            TntBlock.explode(level, context.getClickedPos());
            return true;
        }

        return false;
    }

    @Override
    public void useOnEntity(ServerLevel level, Player player, ItemStack stack, LivingEntity entity, SonicItem item) {
        if (entity instanceof Creeper creeper) {
            creeper.setSwellDir(0);
            creeper.ignite();
        }
    }

    @Override
    public String getSerializedName() {
        return "igniter";
    }

}
