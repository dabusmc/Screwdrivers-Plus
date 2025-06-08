package gooblemc.screwdrivers_plus.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ModCommands {

    private static void addUpgradeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("sonic_upgrade")
                        .requires(source -> source.hasPermission(1))
                        .executes(context -> {
                            CommandSourceStack source = context.getSource();
                            source.sendSuccess(() -> Component.literal("Added upgrade!"), false);
                            return 1;
                        })
        );
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        addUpgradeCommand(dispatcher);
    }

}
