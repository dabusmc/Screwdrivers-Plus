package gooblemc.screwdrivers_plus.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import gooblemc.screwdrivers_plus.item.ModItems;
import gooblemc.screwdrivers_plus.item.custom.SonicItem;
import gooblemc.screwdrivers_plus.upgrades.SonicUpgrades;
import gooblemc.screwdrivers_plus.util.EntityUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ModCommands {

    private static int addUpgrade(CommandContext<CommandSourceStack> context, String upgrade) {
        CommandSourceStack source = context.getSource();

        if(!source.isPlayer()) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.add_upgrade.failure.not_a_player"));
            return 0;
        }

        ServerPlayer player = source.getPlayer();
        EquipmentSlot heldItem = EntityUtils.isHoldingItem(player, ModItems.SONIC_SCREWDRIVER.get());
        if(heldItem == null) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.add_upgrade.failure.not_holding_sonic"));
            return 0;
        }

        ItemStack sonicStack = player.getItemBySlot(heldItem);
        SonicItem sonicItem = (SonicItem) sonicStack.getItem();

        if(sonicItem.hasUpgrade(sonicStack, SonicUpgrades.getUpgradeFromSerializedName(upgrade))) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.add_upgrade.failure.upgrade_already_added"));
            return 0;
        }

        sonicItem.addUpgrade(sonicStack, SonicUpgrades.getUpgradeFromSerializedName(upgrade));

        source.sendSuccess(() -> Component.translatable("commands.screwdrivers_plus.add_upgrade.success"), false);
        return 1;
    }

    private static int removeUpgrade(CommandContext<CommandSourceStack> context, String upgrade) {
        CommandSourceStack source = context.getSource();

        if(!source.isPlayer()) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.remove_upgrade.failure.not_a_player"));
            return 0;
        }

        ServerPlayer player = source.getPlayer();
        EquipmentSlot heldItem = EntityUtils.isHoldingItem(player, ModItems.SONIC_SCREWDRIVER.get());
        if(heldItem == null) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.remove_upgrade.failure.not_holding_sonic"));
            return 0;
        }

        ItemStack sonicStack = player.getItemBySlot(heldItem);
        SonicItem sonicItem = (SonicItem) sonicStack.getItem();

        if(!sonicItem.hasUpgrade(sonicStack, SonicUpgrades.getUpgradeFromSerializedName(upgrade))) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.remove_upgrade.failure.upgrade_not_there"));
            return 0;
        }

        sonicItem.removeUpgrade(sonicStack, SonicUpgrades.getUpgradeFromSerializedName(upgrade));

        source.sendSuccess(() -> Component.translatable("commands.screwdrivers_plus.remove_upgrade.success"), false);
        return 1;
    }

    private static int listUpgrades(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        if(!source.isPlayer()) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.list_upgrades.failure.not_a_player"));
            return 0;
        }

        ServerPlayer player = source.getPlayer();
        EquipmentSlot heldItem = EntityUtils.isHoldingItem(player, ModItems.SONIC_SCREWDRIVER.get());
        if(heldItem == null) {
            source.sendFailure(Component.translatable("commands.screwdrivers_plus.list_upgrades.failure.not_holding_sonic"));
            return 0;
        }

        ItemStack sonicStack = player.getItemBySlot(heldItem);
        SonicItem sonicItem = (SonicItem) sonicStack.getItem();

        StringBuilder upgradesList = new StringBuilder();
        String[] upgrades = sonicItem.getUpgrades(sonicStack);

        if(upgrades.length == 0) {
            source.sendSuccess(() -> Component.translatable("commands.screwdrivers_plus.list_upgrades.success.no_upgrades"), false);
            return 1;
        }

        int i = 0;
        for(String upgrade : upgrades) {
            upgradesList.append(upgrade);
            if(i < upgrades.length - 1) {
                upgradesList.append(", ");
            }
            i += 1;
        }

        source.sendSuccess(() -> Component.literal(upgradesList.toString()), false);
        return 1;
    }

    private static void sonicUpgradeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("sonic_upgrade")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("add")
                                .then(Commands.literal("bomber")
                                    .executes(context -> addUpgrade(context, "bomber"))))
                        .then(Commands.literal("remove")
                                .then(Commands.literal("bomber")
                                        .executes(context -> removeUpgrade(context, "bomber"))))
                        .then(Commands.literal("list")
                                .executes(ModCommands::listUpgrades))
        );
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        sonicUpgradeCommand(dispatcher);
    }

}
