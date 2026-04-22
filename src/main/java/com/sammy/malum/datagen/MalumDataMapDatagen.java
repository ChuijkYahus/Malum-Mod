package com.sammy.malum.datagen;

import com.sammy.malum.common.data.map.*;
import com.sammy.malum.registry.common.util.WoodBlockSet;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
import com.sammy.malum.registry.common.util.data.ItemlessBlockBundle;
import com.sammy.malum.registry.common.util.data.ItemlessBlockBundleWithWall;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;

import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.RUNEWOOD_SET;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumDataMaps.*;
import static com.sammy.malum.registry.common.MalumContent.*;

public class MalumDataMapDatagen extends DataMapProvider {

    protected MalumDataMapDatagen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        MalumMetallicsDatagen.MALUM.addSoulstoneConversions(builder(SOULSTONE_ORE_CONVERSION));

        builder(TOTEM_POLE_CONVERSION)
                .add(RUNEWOOD_SET.log.block(), new TotemPoleConversionMap(Totemancy.RUNEWOOD_TOTEM_POLE), false)
                .add(RUNEWOOD_SET.wood.block(), new TotemPoleConversionMap(Totemancy.RUNEWOOD_TOTEM_POLE), false)
                .add(SOULWOOD_SET.log.block(), new TotemPoleConversionMap(Totemancy.SOULWOOD_TOTEM_POLE), false)
                .add(SOULWOOD_SET.wood.block(), new TotemPoleConversionMap(Totemancy.SOULWOOD_TOTEM_POLE), false)
                .add(Totemancy.RUNEWOOD_TOTEM_POLE, new TotemPoleConversionMap(Totemancy.SOULWOOD_TOTEM_POLE), false);

        fillCarving(RUNEWOOD_SET);
        fillCarving(SOULWOOD_SET);

        builder(FLUID_TAPPING)
                .add(RUNEWOOD_SET.strippedSappyLog.block(), new FluidTappingMap(RUNIC_SAP_CAULDRON, RUNEWOOD_SET.strippedLog.block(), RUNIC_SAP_BOTTLE, 15312230, 0.02f), false)
                .add(SOULWOOD_SET.strippedSappyLog.block(), new FluidTappingMap(AZOIC_SAP_CAULDRON, SOULWOOD_SET.strippedLog.block(), RUNIC_SAP_BOTTLE, 12002653, 0.025f), false);

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(RUNEWOOD_SAPLING.item(), new Compostable(0.4f), false)
                .add(RUNEWOOD_LEAVES.item(), new Compostable(0.3f), false)
                .add(HANGING_RUNEWOOD_LEAVES.item(), new Compostable(0.2f), false)

                .add(AZURE_RUNEWOOD_SAPLING.item(), new Compostable(0.4f), false)
                .add(AZURE_RUNEWOOD_LEAVES.item(), new Compostable(0.3f), false)
                .add(HANGING_AZURE_RUNEWOOD_LEAVES.item(), new Compostable(0.2f), false)

                .add(SOULWOOD_SAPLING.item(), new Compostable(0.4f), false)
                .add(SOULWOOD_LEAVES.item(), new Compostable(0.3f), false)
                .add(HANGING_SOULWOOD_LEAVES.item(), new Compostable(0.2f), false)

                .add(Blight.BLIGHTED_GUNK.item(), new Compostable(0.1f), false);


        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ARCANE_CHARCOAL, new FurnaceFuel(32000), false)
                .add(CompactBlocks.BLOCK_OF_ARCANE_CHARCOAL.item(), new FurnaceFuel(288000), false)
                .add(PYRE_NUCLEUS, new FurnaceFuel(32000), false)
                .add(CompactBlocks.BLOCK_OF_PYRE_NUCLEI.item(), new FurnaceFuel(288000), false);

    }

    public void fillCarving(WoodBlockSet set) {
        fillCarving(
                new BlockBundle[]{set.boards, set.verticalBoards, set.blocks, set.planks, set.verticalPlanks, set.tiles},
                new ItemlessBlockBundle[]{set.carvedBoards, set.carvedVerticalBoards, set.carvedBlocks, set.carvedPlanks, set.carvedVerticalPlanks, set.carvedTiles});
    }

    public void fillCarving(BlockBundle[] inputs, ItemlessBlockBundle[] outputs) {
        Builder<BlockCarvingMap, Block> builder = builder(BLOCK_CARVING);
        for (int i = 0; i < inputs.length; i++) {
            var input = inputs[i];
            var output = outputs[i];
            builder.add(input.block.getBlockHolder(), new BlockCarvingMap(output.block), true);
            builder.add(input.stairs.getBlockHolder(), new BlockCarvingMap(output.stairs), true);
            builder.add(input.slab.getBlockHolder(), new BlockCarvingMap(output.slab), true);
            if (input instanceof BlockBundleWithWall withWall && output instanceof ItemlessBlockBundleWithWall withItemlessWall) {
                builder.add(withWall.wall.getBlockHolder(), new BlockCarvingMap(withItemlessWall.wall), true);
            }
        }
    }
}
