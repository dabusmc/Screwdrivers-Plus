package gooblemc.screwdrivers_plus.item.custom;

import gooblemc.screwdrivers_plus.util.sonic.SonicEngine;
import net.minecraft.world.InteractionResult;
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

}
