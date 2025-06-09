package gooblemc.screwdrivers_plus.item.custom;

import gooblemc.screwdrivers_plus.sonic.SonicEngine;
import gooblemc.screwdrivers_plus.upgrades.SonicUpgrade;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import whocraft.tardis_refined.common.items.ScrewdriverItem;

public class SonicItem extends ScrewdriverItem {

    public static final String UPGRADES = "upgrades";

    public SonicItem(Properties properties) {
        super(properties);
    }

    public boolean hasUpgrade(ItemStack stack, SonicUpgrade upgrade) {
        CompoundTag tag = stack.getOrCreateTagElement(UPGRADES);
        return tag.contains(upgrade.getSerializedName());
    }

    public void addUpgrade(ItemStack stack, SonicUpgrade upgrade) {
        CompoundTag tag = stack.getOrCreateTagElement(UPGRADES);

        if(!hasUpgrade(stack, upgrade)) {
            tag.putBoolean(upgrade.getSerializedName(), true);
        }
    }

    public void removeUpgrade(ItemStack stack, SonicUpgrade upgrade) {
        CompoundTag tag = stack.getOrCreateTagElement(UPGRADES);

        if(hasUpgrade(stack, upgrade)) {
            tag.remove(upgrade.getSerializedName());
        }
    }

    public String[] getUpgrades(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTagElement(UPGRADES);
        return tag.getAllKeys().toArray(new String[0]);
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
