package gooblemc.screwdrivers_plus.util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class EntityUtils {

    public static boolean isWearingAnyArmor(LivingEntity entity) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack stack = entity.getItemBySlot(slot);
                if (!stack.isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isHoldingItem(LivingEntity entity) {
        ItemStack mainHand = entity.getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack offHand = entity.getItemBySlot(EquipmentSlot.OFFHAND);

        return !mainHand.isEmpty() || !offHand.isEmpty();
    }

    public static void dropHeldItem(LivingEntity entity, EquipmentSlot slot) {
        ItemStack stack = entity.getItemBySlot(slot);
        if(!stack.isEmpty()) {
            entity.spawnAtLocation(stack.copy(), 0.5f);
            entity.setItemSlot(slot, ItemStack.EMPTY);
        }
    }

}
