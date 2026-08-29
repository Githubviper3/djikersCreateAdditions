package github.djiker.djikerscreateadditions;

import com.simibubi.create.AllFluids;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import plus.dragons.createdragonsplus.common.fluids.dye.DyeVariantRegistry;
import plus.dragons.createdragonsplus.config.CDPConfig;

import java.util.HashMap;
import java.util.Map;

import static net.neoforged.neoforge.fluids.FluidInteractionRegistry.addInteraction;
import static net.neoforged.neoforge.fluids.FluidInteractionRegistry.InteractionInformation;
import static plus.dragons.createdragonsplus.common.registry.CDPFluids.DRAGON_BREATH;
import static plus.dragons.createdragonsplus.common.registry.CDPFluids.DYES_BY_VARIANT;

public class DCAFluidInteractions {

    public static void register() {

        addInteraction(
                (FluidType) DCAFluids.CONDENSED_LAVA.get().getFluidType(),
                new InteractionInformation((FluidType) NeoForgeMod.WATER_TYPE.value(),
                        (fluidState) -> fluidState.isSource() ?
                                Blocks.MAGMA_BLOCK.defaultBlockState() :
                                Blocks.COBBLED_DEEPSLATE.defaultBlockState()));

        addInteraction(
                (FluidType) DCAFluids.CONDENSED_LAVA.get().getFluidType(),
                new InteractionInformation((FluidType) NeoForgeMod.LAVA_TYPE.value(),
                        (fluidState) -> !fluidState.isSource() ?
                                Blocks.NETHERRACK.defaultBlockState() :
                                Blocks.MAGMA_BLOCK.defaultBlockState()));

        addInteraction(
                (FluidType) DCAFluids.CONDENSED_LAVA.get().getFluidType(),
                new InteractionInformation((FluidType) AllFluids.HONEY.get().getFluidType(),
                        (fluidState) -> !fluidState.isSource() ?
                                Blocks.CALCITE.defaultBlockState() :
                                Blocks.MAGMA_BLOCK.defaultBlockState()));

        addInteraction(
                (FluidType) DCAFluids.CONDENSED_LAVA.get().getFluidType(),
                new InteractionInformation((FluidType) AllFluids.CHOCOLATE.get().getFluidType(),
                        (fluidState) -> !fluidState.isSource() ?
                                AllPaletteStoneTypes.SCORCHIA.getBaseBlock().get().defaultBlockState() :
                                Blocks.MAGMA_BLOCK.defaultBlockState()));

        // Basalt-generator style → Blackstone (source required)
        addInteraction(
                DCAFluids.CONDENSED_LAVA.get().getFluidType(),
                new InteractionInformation(
                        (level, currentPos, relativePos, currentState) ->
                                level.getBlockState(currentPos.above()).is(Blocks.SOUL_SOIL) &&
                                        level.getBlockState(relativePos).is(Blocks.BLUE_ICE),
                        Blocks.BLACKSTONE.defaultBlockState()
                )
        );

        addInteraction(
                DCAFluids.CONDENSED_LAVA.get().getFluidType(),
                new InteractionInformation(
                        (level, currentPos, relativePos, currentState) ->
                                level.getBlockState(currentPos.below()).is(Blocks.SOUL_SOIL) &&
                                        level.getBlockState(relativePos).is(Blocks.BLUE_ICE),
                        Blocks.BLACKSTONE.defaultBlockState()
                )
        );

        addInteraction(NeoForgeMod.LAVA_TYPE.value(),
                new InteractionInformation(
                        (level, currentPos, relativePos, currentState) ->
                                level.getBlockState(currentPos.above()).is(Blocks.SOUL_SOIL) &&
                                        level.getBlockState(relativePos).is(Blocks.BLUE_ICE),
                        Blocks.BASALT.defaultBlockState()));

        addInteraction(DCAFluids.CONDENSED_LAVA.getType(), new InteractionInformation(
                DRAGON_BREATH.getType(),
                fluidState -> fluidState.isSource()
                        ? Blocks.OBSIDIAN.defaultBlockState()
                        : Blocks.AMETHYST_BLOCK.defaultBlockState()));

        var genConcrete = CDPConfig.common().features.dyeFluidsLavaInteractionGenerateColoredConcrete.get();

        DYES_BY_VARIANT.forEach((id, entry) -> {
            var variant = DyeVariantRegistry.get(id).orElseThrow();
            var type = entry.getType();
            var blockName = variant.serializedName() + "_terracotta";

            var block = BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace(blockName));
            if (block == Blocks.AIR)
                return;
            addInteraction(DCAFluids.CONDENSED_LAVA.get().getFluidType(), new InteractionInformation(
                    type,
                    fluidState -> fluidState.isSource()
                            ? Blocks.TERRACOTTA.defaultBlockState()
                            : genConcrete ? block.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState()));
        });


    }
}