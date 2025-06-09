package gooblemc.screwdrivers_plus.upgrades;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class SonicUpgradeItem extends Item {

    private String m_UpgradeName;

    public SonicUpgradeItem(String upgradeName, Properties properties) {
        super(properties);
        m_UpgradeName = upgradeName;
    }

}
