package gooblemc.screwdrivers_plus.upgrades;

import gooblemc.screwdrivers_plus.item.custom.SonicItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public abstract class SonicUpgrade {

    public abstract boolean useOnBlock(ServerLevel level, UseOnContext context, Player player, ItemStack stack);
    public abstract void useOnEntity(ServerLevel level, Player player, ItemStack stack, LivingEntity entity, SonicItem item);

    public abstract String getSerializedName();

}
