package gooblemc.screwdrivers_plus.upgrades;

import net.minecraft.world.item.Item;

public class UpgradeItem extends Item {

    private SonicUpgrade m_Upgrade;

    public UpgradeItem(SonicUpgrade upgrade, Properties properties) {
        super(properties);
        m_Upgrade = upgrade;
    }

}
