package github.djiker.djikerscreateadditions;

import com.tterrag.registrate.util.entry.FluidEntry;
import net.createmod.catnip.theme.Color;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import static github.djiker.djikerscreateadditions.DjikersCreateAdditions.REGISTRATE;
import plus.dragons.createdragonsplus.common.fluids.SolidRenderFluidType;
public class DCAFluids {

    public static final FluidEntry<BaseFlowingFluid.Flowing> CONDENSED_LAVA =
            REGISTRATE.fluid(
                            "condensed_lava",
                            DjikersCreateAdditions.rl("block/condensed_lava_still"),
                            DjikersCreateAdditions.rl("block/condensed_lava_flow"),
                            SolidRenderFluidType.create(new Color(0x8B1A0E).asVectorF(),
                                    () -> 1f/40f))
                    .properties(b -> b
                            .canSwim(false)
                            .canDrown(false)
                            .pathType(PathType.LAVA)
                            .adjacentPathType(null)
                            .motionScale(0.007D)
                            .density(3000)
                            .viscosity(3000)
                            .lightLevel(15)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                            .canPushEntity(true))
                    .fluidProperties(p -> p
                            .levelDecreasePerBlock(1)
                            .tickRate(30)
                            .slopeFindDistance(4)
                            .explosionResistance(100f))
                    .source(BaseFlowingFluid.Source::new)
                    .block(CondensedLavaBlock::new)
                    .properties(p -> p.mapColor(MapColor.FIRE))
                    .build()
                    .register();

    public static void register() {
    }
}