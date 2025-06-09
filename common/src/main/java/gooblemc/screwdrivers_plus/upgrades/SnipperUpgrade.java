package gooblemc.screwdrivers_plus.upgrades;

import gooblemc.screwdrivers_plus.item.custom.SonicItem;
import gooblemc.screwdrivers_plus.util.BeehiveUtils;
import gooblemc.screwdrivers_plus.util.EntityUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class SnipperUpgrade extends SonicUpgrade {

    @Override
    public boolean useOnBlock(ServerLevel level, UseOnContext context, Player player, ItemStack stack) {
        BlockState state = level.getBlockState(context.getClickedPos());

        if(state.getBlock() instanceof BeehiveBlock beehive) {
            if(state.getValue(BeehiveBlock.HONEY_LEVEL) >= BeehiveBlock.MAX_HONEY_LEVELS) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
                BeehiveBlock.dropHoneycomb(level, context.getClickedPos());
                if(!CampfireBlock.isSmokeyPos(level, context.getClickedPos())) {
                    if(!BeehiveUtils.beehiveIsEmpty(level, context.getClickedPos())) {
                        BeehiveUtils.angerBeesAroundHive(level, context.getClickedPos());
                    }

                    beehive.releaseBeesAndResetHoneyLevel(level, state, context.getClickedPos(), player, BeehiveBlockEntity.BeeReleaseStatus.EMERGENCY);
                } else {
                    beehive.resetHoneyLevel(level, state, context.getClickedPos());
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public void useOnEntity(ServerLevel level, Player player, ItemStack stack, LivingEntity entity, SonicItem item) {
        boolean isWearingArmor = EntityUtils.isWearingAnyArmor(entity);
        boolean isHoldingItems = EntityUtils.isHoldingAnyItem(entity);

        if (entity instanceof Sheep sheep) {
            if(sheep.readyForShearing()) {
                sheep.shear(SoundSource.PLAYERS);
                sheep.gameEvent(GameEvent.SHEAR, player);
            }
        } else if(isWearingArmor || isHoldingItems) {
            if(entity instanceof Player) {
                return;
            }

            if(isWearingArmor) {
                EntityUtils.dropHeldItem(entity, EquipmentSlot.HEAD);
                EntityUtils.dropHeldItem(entity, EquipmentSlot.CHEST);
                EntityUtils.dropHeldItem(entity, EquipmentSlot.LEGS);
                EntityUtils.dropHeldItem(entity, EquipmentSlot.FEET);
            }

            if(isHoldingItems) {
                EntityUtils.dropHeldItem(entity, EquipmentSlot.MAINHAND);
                EntityUtils.dropHeldItem(entity, EquipmentSlot.OFFHAND);
            }
        }
    }

    @Override
    public String getSerializedName() {
        return "snipper";
    }

}
