package gooblemc.screwdrivers_plus.util.sonic;

import gooblemc.screwdrivers_plus.item.custom.SonicItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import whocraft.tardis_refined.common.items.ScrewdriverMode;

public class SonicEngine {

    public static void useOnBlock(UseOnContext context) {
        SonicItem item = (SonicItem) context.getItemInHand().getItem();
        Player player = context.getPlayer();

        if(item.isScrewdriverMode(context.getItemInHand(), ScrewdriverMode.ENABLED) && !player.isCrouching()) {
            player.displayClientMessage(Component.literal("What's up sonic!"), false);
        }
    }

}
