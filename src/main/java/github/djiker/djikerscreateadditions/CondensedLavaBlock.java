package github.djiker.djikerscreateadditions;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class CondensedLavaBlock extends LiquidBlock {

    public CondensedLavaBlock(FlowingFluid fluid, Properties props) {
        super(fluid, props);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {

        if (entity instanceof LivingEntity living) {
            living.hurt(level.damageSources().lava(), 8.0F);
            living.igniteForSeconds(15);
        }
        if (entity instanceof ItemEntity item) {
            if (!item.isInWater() && !item.fireImmune()) {
                item.igniteForSeconds(10); // vanilla item burn method
            }
        }


        super.entityInside(state, level, pos, entity);
    }


}