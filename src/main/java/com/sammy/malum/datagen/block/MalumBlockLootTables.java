package com.sammy.malum.datagen.block;

import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.common.block.geode.GeodeCrystalClusterBlock;
import com.sammy.malum.common.block.soulstone.SoulstoneBudBlock;
import com.sammy.malum.common.block.storage.jar.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.util.GeodeCrystalRegistrySet;
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
import team.lodestar.lodestone.modules.toolkit.block.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;
import java.util.stream.*;

import static com.sammy.malum.registry.common.MalumContent.Blight.*;
import static com.sammy.malum.registry.common.MalumContent.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;

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

        protected Set<Block> generatedValues = new HashSet<>();
        protected BlocksLoot(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

        @Override
        protected void generate() {
            add(RUNEWOOD_LEAVES.get(), createLeavesDrops(RUNEWOOD_LEAVES.get(), RUNEWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE));
            add(HANGING_RUNEWOOD_LEAVES.get(), createLeavesDrops(HANGING_RUNEWOOD_LEAVES.get(), RUNEWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE));

            add(AZURE_RUNEWOOD_LEAVES.get(), createLeavesDrops(AZURE_RUNEWOOD_LEAVES.get(), AZURE_RUNEWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE));
            add(HANGING_AZURE_RUNEWOOD_LEAVES.get(), createLeavesDrops(HANGING_AZURE_RUNEWOOD_LEAVES.get(), AZURE_RUNEWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE));

            add(SOULWOOD_LEAVES.get(), createLeavesDrops(SOULWOOD_LEAVES.get(), SOULWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE));
            add(HANGING_SOULWOOD_LEAVES.get(), createLeavesDrops(HANGING_SOULWOOD_LEAVES.get(), SOULWOOD_SAPLING.get(), MAGIC_SAPLING_DROP_CHANCE));

            add(STRANGE_CRYSTAL.get(), createSingleItemTableWithSilkTouchOrShears(STRANGE_CRYSTAL.get(), STRANGE_CRYSTAL.get()));
            add(LARGE_STRANGE_CRYSTAL.get(), createTallBlockDrop(LARGE_STRANGE_CRYSTAL.get()));
            add(STRANGEROOT.get(), createSingleItemTableWithSilkTouchOrShears(STRANGEROOT.get(), STRANGEROOT.get()));

            add(BLIGHTED_SOULWOOD.get(), createSingleItemTableWithSilkTouch(BLIGHTED_SOULWOOD.get(), SOULWOOD_SET.log));
            add(BLIGHTED_EARTH.get(), createBlightedDrop(BLIGHTED_EARTH.get(), 4));
            add(BLIGHTED_GUNK.get(), createBlightedPlantDrop(BLIGHTED_GUNK.get(), 1));
            add(BLIGHTPEARL.get(), createBlightedPlantDrop(BLIGHTPEARL.get(), 1));
            add(BLIGHTROOT.get(), createBlightedPlantDrop(BLIGHTROOT.get(), 1));

            add(SOULSTONE_ORE.get(), createOreDrop(SOULSTONE_ORE.get(), RAW_SOULSTONE.get()));
            add(DEEPSLATE_SOULSTONE_ORE.get(), createOreDrop(DEEPSLATE_SOULSTONE_ORE.get(), RAW_SOULSTONE.get()));

            add(ARCHAIC_SOULSTONE_BUD.get(), createArchaicSoulstoneBudDrop(ARCHAIC_SOULSTONE_BUD.get(), SOULSTONE_BUD));
            add(SOULSTONE_BUD.get(), createSoulstoneBudDrop(SOULSTONE_BUD.get(), REALIZED_SOULSTONE_BUD));

            add(BRILLIANT_STONE.get(), createOreDrop(BRILLIANT_STONE.get(), RAW_BRILLIANCE.get()));
            add(BRILLIANT_DEEPSLATE.get(), createOreDrop(BRILLIANT_DEEPSLATE.get(), RAW_BRILLIANCE.get()));

            add(CTHONIC_GOLD_ORE.get(), createCthonicGoldOreDrop(CTHONIC_GOLD_ORE.get()));

            add(BLAZING_QUARTZ_ORE.get(), createOreDrop(BLAZING_QUARTZ_ORE.get(), BLAZING_QUARTZ.get()));

            addGeodeDrops(MUNDANE_QUARTZ);

            add(SOULWOVEN_BANNER.get(), createBannerDrop(SOULWOVEN_BANNER.get()));


            add(EtherBlock.class, this::createEtherDrop);
            add(SpiritJarBlock.class, this::createJarDrop);

            add(FlowerPotBlock.class, block -> createPotFlowerItemTable(((FlowerPotBlock) block).getPotted()));
            add(SaplingBlock.class, block -> createSingleItemTable(block.asItem()));
            add(DoublePlantBlock.class, block -> createSingleItemTableWithSilkTouchOrShears(block, block.asItem()));
            add(BushBlock.class, block -> createSingleItemTableWithSilkTouchOrShears(block, block.asItem()));
            add(GrassBlock.class, block -> createSingleItemTableWithSilkTouch(block, Items.DIRT));
            add(SlabBlock.class, this::createSlabItemTable);
            add(DoorBlock.class, this::createDoorTable);
            add(AbstractCauldronBlock.class, block -> createSingleItemTable(Items.CAULDRON));

            addRemaining();
        }

        protected void addRemaining() {
            for (Block knownBlock : getKnownBlocks()) {
                if (generatedValues.contains(knownBlock)) {
                    continue;
                }
                if (knownBlock.properties instanceof LodestoneBlockProperties blockProperties && blockProperties.getDatagenData().noLootDatagen) {
                    continue;
                }
                add(knownBlock, createSingleItemTable(knownBlock));
            }
        }
        protected void add(Class<? extends Block> blockClass, Function<Block, LootTable.Builder> builder) {
            for (Block knownBlock : getKnownBlocks()) {
                if (blockClass.isInstance(knownBlock)) {
                    if (knownBlock.properties instanceof LodestoneBlockProperties blockProperties && blockProperties.getDatagenData().noLootDatagen) {
                        continue;
                    }
                    add(knownBlock, builder.apply(knownBlock));
                }
            }
        }
        @Override
        protected void add(Block block, LootTable.Builder builder) {
            super.add(block, builder);
            generatedValues.add(block);
        }

        protected void addGeodeDrops(GeodeCrystalRegistrySet... sets) {
            for (GeodeCrystalRegistrySet set : sets) {


                var cluster = set.getCluster().block().get();
                add(cluster, LootTable.lootTable().withPool(applyExplosionCondition(cluster, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(cluster)
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cluster)
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(GeodeCrystalClusterBlock.AGE, 2))
                                                )
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                                .otherwise(LootItem.lootTableItem(cluster))
                                        )
                                )
                        )
                );
                var budding = set.getBudding().get();

                createSingleItemTableWithSilkTouch(budding, cluster, UniformGenerator.between(2, 4));
            }
        }

        protected LootTable.Builder createTallBlockDrop(Block block) {
            var upperCondition = IS_UPPER_PART.apply(block);
            var lowerCondition = IS_LOWER_PART.apply(block);
            return createSilkTouchDispatchTable(block, LootItem.lootTableItem(block).when(AnyOfCondition.anyOf(upperCondition, lowerCondition)));
        }

        protected LootTable.Builder createCthonicGoldOreDrop(Block block) {
            return createSilkTouchDispatchTable(block,
                    applyExplosionDecay(block, LootItem.lootTableItem(CTHONIC_GOLD_FRAGMENT.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))));
        }

        protected LootTable.Builder createBlightedDrop(Block block, int gunkAmount) {
            return createSilkTouchDispatchTable(block,
                    applyExplosionCondition(BLIGHTED_GUNK.get(), LootItem.lootTableItem(BLIGHTED_GUNK.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(gunkAmount)))));
        }

        protected LootTable.Builder createBlightedPlantDrop(Block block, int gunkAmount) {
            return createSilkTouchOrShearsDispatchTable(block,
                    applyExplosionCondition(BLIGHTED_GUNK.get(), LootItem.lootTableItem(BLIGHTED_GUNK.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(gunkAmount)))));
        }

        protected LootTable.Builder createArchaicSoulstoneBudDrop(Block block, ItemLike bud) {
            return LootTable.lootTable().withPool(
                    applyExplosionCondition(block,
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(bud.asItem())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                    )
                    )
            );
        }

        protected LootTable.Builder createSoulstoneBudDrop(Block block, ItemLike realizedBud) {
            return LootTable.lootTable().withPool(
                    applyExplosionCondition(block,
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(realizedBud)
                                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                    .setProperties(StatePropertiesPredicate.Builder.properties()
                                                            .hasProperty(SoulstoneBudBlock.STAGE, 3))
                                            )
                                            .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                    .include(MalumDataComponents.SOULSTONE_BUD_DATA.get())
                                            )
                                            .otherwise(LootItem.lootTableItem(block))
                                    )
                    )
            );
        }

        protected LootTable.Builder createEtherDrop(Block block) {
            return LootTable.lootTable().withPool(
                    applyExplosionCondition(block,
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(block)
                                            .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                    .include(MalumDataComponents.SECONDARY_DYED_COLOR.get())
                                                    .include(DataComponents.DYED_COLOR)
                                            )
                                    )
                    )
            );
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