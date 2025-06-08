package gooblemc.screwdrivers_plus.item.custom;

import gooblemc.screwdrivers_plus.sonic.SonicEngine;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import whocraft.tardis_refined.common.items.ScrewdriverItem;

public class SonicItem extends ScrewdriverItem {

    public SonicItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        SonicEngine.useOnBlock(context);
        return super.useOn(context);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        SonicEngine.useOnEntity(itemStack, player, livingEntity);
        return super.interactLivingEntity(itemStack, player, livingEntity, interactionHand);
    }
}
