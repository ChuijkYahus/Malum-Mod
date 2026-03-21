package com.sammy.malum.datagen.block;

import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.common.block.storage.jar.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.*;
import net.minecraft.data.loot.*;
import net.minecraft.world.flag.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;
import java.util.stream.*;

import static com.sammy.malum.registry.common.block.MalumBlocks.*;
import static team.lodestar.lodestone.helpers.DataHelper.*;

public class MalumBlockLootTables extends LootTableProvider {

    private static final float[] MAGIC_SAPLING_DROP_CHANCE = new float[]{0.015F, 0.0225F, 0.033333336F, 0.05F};

    private static final Function<Block, LootItemCondition.Builder> IS_UPPER_PART = Util.memoize(b -> AllOfCondition.allOf(
            LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)),
            LocationCheck.checkLocation(LocationPredicate.Builder.location()
                            .setBlock(BlockPredicate.Builder.block()
                                    .of(b)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))
                            ),
                    new BlockPos(0, -1, 0)
            )
    ));

    private static final Function<Block, LootItemCondition.Builder> IS_LOWER_PART = Util.memoize(b -> AllOfCondition.allOf(
            LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)),
            LocationCheck.checkLocation(LocationPredicate.Builder.location()
                            .setBlock(BlockPredicate.Builder.block()
                                    .of(b)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))
                            ),
                    new BlockPos(0, 1, 0)
            )
    ));

    public MalumBlockLootTables(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(pOutput, Set.of(), List.of(
                new SubProviderEntry(BlocksLoot::new, LootContextParamSets.BLOCK)
        ), provider);
    }

    public static class BlocksLoot extends BlockLootSubProvider {

        protected BlocksLoot(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

        @Override
        protected void generate() {
            Set<DeferredHolder<Block, ? extends Block>> blocks = new HashSet<>(BLOCKS.getEntries());

            takeAll(blocks, b -> {
                if (b.get().properties() instanceof LodestoneBlockProperties properties) {
                    return properties.getDatagenData().noLootDatagen;
                }
                return true;
            });

            takeAll(blocks, RUNEWOOD_LEAVES, HANGING_RUNEWOOD_LEAVES).forEach((b) -> add(b.get(), createLeavesDrops(b.get(), RUNEWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE)));
            takeAll(blocks, AZURE_RUNEWOOD_LEAVES, HANGING_AZURE_RUNEWOOD_LEAVES).forEach((b) -> add(b.get(), createLeavesDrops(b.get(), AZURE_RUNEWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE)));
            takeAll(blocks, SOULWOOD_LEAVES, HANGING_SOULWOOD_LEAVES).forEach((b) -> add(b.get(), createLeavesDrops(b.get(), SOULWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE)));

            add(take(blocks, STRANGE_CRYSTAL).get(), createSingleItemTableWithSilkTouchOrShears(STRANGE_CRYSTAL.get(), MalumItems.STRANGE_CRYSTAL.get()));
            add(take(blocks, LARGE_STRANGE_CRYSTAL).get(), createTallBlockDrop(LARGE_STRANGE_CRYSTAL.get()));
            add(take(blocks, STRANGEROOT).get(), createSingleItemTableWithSilkTouchOrShears(STRANGEROOT.get(), MalumItems.STRANGEROOT.get()));

            add(take(blocks, BLIGHTED_SOULWOOD).get(), createSingleItemTableWithSilkTouch(BLIGHTED_SOULWOOD.get(), MalumItems.SOULWOOD_LOG.get()));
            add(take(blocks, BLIGHTED_EARTH).get(), createBlightedDrop(BLIGHTED_EARTH.get(), 4));
            add(take(blocks, BLIGHTED_GROWTH).get(), createBlightedPlantDrop(BLIGHTED_GROWTH.get(), 1));
            add(take(blocks, BLIGHTPEARL).get(), createBlightedPlantDrop(BLIGHTPEARL.get(), 1));
            add(take(blocks, BLIGHTROOT).get(), createBlightedPlantDrop(BLIGHTROOT.get(), 1));

            add(take(blocks, BRILLIANT_STONE).get(), createOreDrop(BRILLIANT_STONE.get(), MalumItems.RAW_BRILLIANCE.get()));
            add(take(blocks, BRILLIANT_DEEPSLATE).get(), createOreDrop(BRILLIANT_DEEPSLATE.get(), MalumItems.RAW_BRILLIANCE.get()));
            add(take(blocks, SOULSTONE_ORE).get(), createOreDrop(SOULSTONE_ORE.get(), MalumItems.RAW_SOULSTONE.get()));
            add(take(blocks, DEEPSLATE_SOULSTONE_ORE).get(), createOreDrop(DEEPSLATE_SOULSTONE_ORE.get(), MalumItems.RAW_SOULSTONE.get()));
            add(take(blocks, BLAZING_QUARTZ_ORE).get(), createOreDrop(BLAZING_QUARTZ_ORE.get(), MalumItems.BLAZING_QUARTZ.get()));
            add(take(blocks, NATURAL_QUARTZ_ORE).get(), createOreDrop(NATURAL_QUARTZ_ORE.get(), MalumItems.NATURAL_QUARTZ.get()));
            add(take(blocks, DEEPSLATE_QUARTZ_ORE).get(), createOreDrop(DEEPSLATE_QUARTZ_ORE.get(), MalumItems.NATURAL_QUARTZ.get()));
            add(take(blocks, CTHONIC_GOLD_ORE).get(), createCthonicGoldOreDrop(CTHONIC_GOLD_ORE.get()));

            add(take(blocks, SOULWOVEN_BANNER).get(), createBannerDrop(SOULWOVEN_BANNER.get()));

            takeAll(blocks, b -> b.get() instanceof FlowerPotBlock).forEach(b -> add(b.get(), createPotFlowerItemTable(((FlowerPotBlock)b.get()).getPotted())));
            takeAll(blocks, b -> b.get() instanceof SaplingBlock).forEach(b -> add(b.get(), createSingleItemTable(b.get().asItem())));
            takeAll(blocks, b -> b.get() instanceof DoublePlantBlock).forEach(b -> add(b.get(), createSingleItemTableWithSilkTouchOrShears(b.get(), b.get().asItem())));
            takeAll(blocks, b -> b.get() instanceof BushBlock).forEach(b -> add(b.get(), createSingleItemTableWithSilkTouchOrShears(b.get(), b.get().asItem())));

            takeAll(blocks, b -> b.get() instanceof GrassBlock).forEach(b -> add(b.get(), createSingleItemTableWithSilkTouch(b.get(), Items.DIRT)));
            takeAll(blocks, b -> b.get() instanceof SlabBlock).forEach(b -> add(b.get(), createSlabItemTable(b.get())));
            takeAll(blocks, b -> b.get() instanceof DoorBlock).forEach(b -> add(b.get(), createDoorTable(b.get())));

            takeAll(blocks, b -> b.get() instanceof EtherBlock).forEach(b -> add(b.get(), createEtherDrop(b.get())));
            takeAll(blocks, b -> b.get() instanceof SpiritJarBlock).forEach(b -> add(b.get(), createJarDrop(b.get())));

            takeAll(blocks, b -> true).forEach(b -> add(b.get(), createSingleItemTable(b.get().asItem())));
        }

        protected LootTable.Builder createTallBlockDrop(Block block) {
            var upperCondition = IS_UPPER_PART.apply(block);
            var lowerCondition = IS_LOWER_PART.apply(block);
            return createSilkTouchDispatchTable(block, LootItem.lootTableItem(block).when(AnyOfCondition.anyOf(upperCondition, lowerCondition)));
        }

        protected LootTable.Builder createCthonicGoldOreDrop(Block block) {
            return createSilkTouchDispatchTable(block,
                    applyExplosionDecay(block, LootItem.lootTableItem(MalumItems.CTHONIC_GOLD_FRAGMENT.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))));
        }

        protected LootTable.Builder createBlightedDrop(Block block, int gunkAmount) {
            return createSilkTouchDispatchTable(block,
                    applyExplosionCondition(MalumItems.BLIGHTED_GUNK.get(), LootItem.lootTableItem(MalumItems.BLIGHTED_GUNK.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(gunkAmount)))));
        }

        protected LootTable.Builder createBlightedPlantDrop(Block block, int gunkAmount) {
            return createSilkTouchOrShearsDispatchTable(block,
                    applyExplosionCondition(MalumItems.BLIGHTED_GUNK.get(), LootItem.lootTableItem(MalumItems.BLIGHTED_GUNK.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(gunkAmount)))));
        }

        protected LootTable.Builder createEtherDrop(Block block) {
            return LootTable.lootTable().withPool(
                    applyExplosionCondition(block,
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(block)
                                            .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                            .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                    .include(MalumDataComponents.SECONDARY_DYED_COLOR.get())
                                                    .include(DataComponents.DYED_COLOR)
                                            ))));
        }

        protected LootTable.Builder createBannerDrop(Block block) {
            return LootTable.lootTable().withPool(
                    applyExplosionCondition(block,
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(block)
                                            .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                            .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                    .include(MalumDataComponents.SOULWOVEN_BANNER_PATTERN.get())))));

        }

        protected LootTable.Builder createJarDrop(Block block) {
            return LootTable.lootTable().withPool(
                    applyExplosionCondition(block,
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(block)
                                            .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                            .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                    .include(MalumDataComponents.SPIRIT_JAR_CONTENTS.get())))));
        }

        protected LootTable.Builder createSingleItemTableWithSilkTouchOrShears(Block p_124258_, ItemLike p_124259_) {
            return createSilkTouchOrShearsDispatchTable(p_124258_, applyExplosionCondition(p_124258_, LootItem.lootTableItem(p_124259_)));
        }
    }
}