package gooblemc.screwdrivers_plus.util.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BeehiveUtils {

     public static boolean beehiveIsEmpty(ServerLevel level, BlockPos position) {
         BlockEntity beehiveEntity = level.getBlockEntity(position);
         if(beehiveEntity instanceof BeehiveBlockEntity beehiveBlockEntity) {
            return beehiveBlockEntity.isEmpty();
         }
         return true;
     }

     public static void angerBeesAroundHive(ServerLevel level, BlockPos position) {
         List<Bee> bees = level.getEntitiesOfClass(Bee.class, (new AABB(position)).inflate((double)8.0F, (double)6.0F, (double)8.0F));
         if (!bees.isEmpty()) {
             List<Player> targets = level.getEntitiesOfClass(Player.class, (new AABB(position)).inflate((double)8.0F, (double)6.0F, (double)8.0F));
             int i = targets.size();

             for(Bee bee : bees) {
                 if (bee.getTarget() == null) {
                     bee.setTarget(targets.get(level.random.nextInt(i)));
                 }
             }
         }
     }

}
