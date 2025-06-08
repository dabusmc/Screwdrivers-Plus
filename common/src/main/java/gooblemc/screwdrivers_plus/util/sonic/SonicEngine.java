package gooblemc.screwdrivers_plus.util.sonic;

import gooblemc.screwdrivers_plus.item.custom.SonicItem;
import gooblemc.screwdrivers_plus.util.block.BeehiveUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import whocraft.tardis_refined.common.items.ScrewdriverMode;
import whocraft.tardis_refined.registry.TRSoundRegistry;

public class SonicEngine {

    private static final int COOLDOWN_TIME = 40;
    private static final int CREATIVE_COOLDOWN_TIME = 5;

    // BLOCKS

    public static void useOnBlock(UseOnContext context) {
        SonicItem item = (SonicItem) context.getItemInHand().getItem();
        Player player = context.getPlayer();

        if(item.isScrewdriverMode(context.getItemInHand(), ScrewdriverMode.ENABLED) && !player.isCrouching()) {
            handleBlockInteractions(context, player);
        }
    }

    private static void handleBlockInteractions(UseOnContext context, Player player) {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());
        ItemStack stack = context.getItemInHand();

        if(level instanceof ServerLevel serverLevel && !level.isClientSide) {
            if (state.getBlock() instanceof TntBlock) {
                handleTNT(serverLevel, context, player, stack);
            } else if (state.getBlock() instanceof GlassBlock || state.getBlock() instanceof StainedGlassBlock || state.getBlock() instanceof StainedGlassPaneBlock || state.getBlock() instanceof TintedGlassBlock || state.getBlock() == Blocks.GLASS_PANE) {
                destroyBlock(serverLevel, context, player, stack);
            } else if (state.getBlock() instanceof DoorBlock) {
                handleDoor(serverLevel, state, context, player, stack);
            } else if (state.getBlock() instanceof TrapDoorBlock) {
                cycleBlockState(serverLevel, state, context, player, stack, BlockStateProperties.OPEN);
            } else if(state.getBlock() instanceof BeehiveBlock) {
                handleBeehive(serverLevel, state, context, player, stack);
            } else if (state.hasProperty(BlockStateProperties.POWERED)) {
                cycleBlockState(serverLevel, state, context, player, stack, BlockStateProperties.POWERED);
            } else if (state.hasProperty(BlockStateProperties.LIT)) {
                cycleBlockState(serverLevel, state, context, player, stack, BlockStateProperties.LIT);
            } else if (state.hasProperty(BlockStateProperties.OPEN)) {
                cycleBlockState(serverLevel, state, context, player, stack, BlockStateProperties.OPEN);
            }
        }
    }

    // ENTITIES

    public static void useOnEntity(ItemStack stack, Player player, LivingEntity entity) {
        SonicItem item = (SonicItem) stack.getItem();

        if(player.level() instanceof ServerLevel serverLevel && item.isScrewdriverMode(stack, ScrewdriverMode.ENABLED)) {
            handleEntityInteractions(serverLevel, player, entity, item);
        }
    }

    private static void handleEntityInteractions(ServerLevel level, Player player, LivingEntity entity, SonicItem item) {
        if(!player.isCrouching()) {
            if (entity instanceof Creeper creeper) {
                creeper.setSwellDir(0);
                creeper.ignite();
            } else if (entity instanceof Sheep sheep) {
                if(sheep.readyForShearing()) {
                    sheep.shear(SoundSource.PLAYERS);
                    sheep.gameEvent(GameEvent.SHEAR, player);
                }
            }
        }

        item.playScrewdriverSound(level, player.blockPosition(), TRSoundRegistry.SCREWDRIVER_SHORT.get());
    }

    // SPECIFIC ACTIONS

    private static void cycleBlockState(ServerLevel level, BlockState state, UseOnContext context, Player player, ItemStack stack, Property<?> property) {
        level.setBlock(context.getClickedPos(), state.cycle(property), Block.UPDATE_ALL);
        level.updateNeighborsAt(context.getClickedPos(), state.getBlock());

        // NOTE: This is a bit hacky but makes it so that non-tickable blocks that receive a redstone update
        //       (such as redstone lamps) still get their updates as expected
        for (Direction direction : Direction.values()) {
            BlockPos neighbor = context.getClickedPos().relative(direction);
            level.neighborChanged(neighbor, level.getBlockState(context.getClickedPos()).getBlock(), context.getClickedPos());

            BlockState neighborState = level.getBlockState(neighbor);
            level.sendBlockUpdated(neighbor, neighborState, neighborState, Block.UPDATE_ALL);
        }

        playSoundAndCooldown(level, player, stack, context.getClickedPos());
    }

    private static void destroyBlock(ServerLevel level, UseOnContext context, Player player, ItemStack stack) {
        level.destroyBlock(context.getClickedPos(), true);
        playSoundAndCooldown(level, player, stack, context.getClickedPos());
    }

    private static void handleTNT(ServerLevel level, UseOnContext context, Player player, ItemStack stack) {
        level.removeBlock(context.getClickedPos(), false);
        TntBlock.explode(level, context.getClickedPos());
        playSoundAndCooldown(level, player, stack, context.getClickedPos());
    }

    private static void handleDoor(ServerLevel level, BlockState state, UseOnContext context, Player player, ItemStack stack) {
        if(!(state.getBlock() instanceof DoorBlock)) {
            return;
        }

        // Find the other half
        DoubleBlockHalf half = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);

        BlockPos otherPos = half == DoubleBlockHalf.LOWER ? context.getClickedPos().above() : context.getClickedPos().below();
        BlockState otherState = level.getBlockState(otherPos);

        // Update the block(s)
        level.setBlock(context.getClickedPos(), state.cycle(BlockStateProperties.OPEN), Block.UPDATE_ALL);
        level.setBlock(otherPos, otherState.cycle(BlockStateProperties.OPEN), Block.UPDATE_ALL);

        // Notify neighbors & recalculate shape
        level.blockUpdated(context.getClickedPos(), state.getBlock());
        level.blockUpdated(otherPos, otherState.getBlock());

        level.sendBlockUpdated(context.getClickedPos(), state, level.getBlockState(context.getClickedPos()), Block.UPDATE_ALL);
        level.sendBlockUpdated(otherPos, otherState, level.getBlockState(otherPos), Block.UPDATE_ALL);

        playSoundAndCooldown(level, player, stack, context.getClickedPos());
    }

    private static void handleBeehive(ServerLevel level, BlockState state, UseOnContext context, Player player, ItemStack stack) {
        if(!(state.getBlock() instanceof BeehiveBlock beehive)) {
            return;
        }

        if(state.getValue(BeehiveBlock.HONEY_LEVEL) >= BeehiveBlock.MAX_HONEY_LEVELS) {
            level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
            BeehiveBlock.dropHoneycomb(level, context.getClickedPos());
            if(!CampfireBlock.isSmokeyPos(level, context.getClickedPos())) {
                if(!BeehiveUtils.beehiveIsEmpty(level, context.getClickedPos())) {
                    BeehiveUtils.angerBeesAroundHive(level, context.getClickedPos());
                }

                beehive.releaseBeesAndResetHoneyLevel(level, state, context.getClickedPos(), player, BeehiveBlockEntity.BeeReleaseStatus.EMERGENCY);
            } else {
                beehive.resetHoneyLevel(level, state, context.getClickedPos());
            }
        }
    }

    // UTILITY

    public static void cooldown(Player player, ItemStack stack) {
        if (!player.isCreative()) {
            player.getCooldowns().addCooldown(stack.getItem(), COOLDOWN_TIME);
        } else {
            player.getCooldowns().addCooldown(stack.getItem(), CREATIVE_COOLDOWN_TIME);
        }
    }

    private static void playSoundAndCooldown(ServerLevel world, Player player, ItemStack stack, BlockPos pos) {
        SonicItem item = (SonicItem) stack.getItem();
        item.playScrewdriverSound(world, pos, TRSoundRegistry.SCREWDRIVER_SHORT.get());
        cooldown(player, stack);
    }

}
