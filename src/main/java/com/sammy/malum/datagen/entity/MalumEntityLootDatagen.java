package com.sammy.malum.datagen.entity;

import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.entity.MalumCultistEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

import static com.sammy.malum.registry.common.MalumContent.Materials.*;

public class MalumEntityLootDatagen extends EntityLootSubProvider {

    public MalumEntityLootDatagen(HolderLookup.Provider provider) {
        super(FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
/*
all cultists should drop some igneous chitin, the tougher the cultist the more chitin
altars, believers and cherubs should drop dark brimstone
cardinals drop volatile entropy
evangelists drop annulled memories
 */

        add(
                MalumCultistEntityTypes.ALTAR.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .add(
                                                LootItem.lootTableItem(IGNEOUS_CHITIN.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.35f))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1, 3)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(DARK_BRIMSTONE.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1, 3)
                                                                )
                                                        )
                                        )
                        )
        );
        add(
                MalumCultistEntityTypes.BELIEVER.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .add(
                                                LootItem.lootTableItem(IGNEOUS_CHITIN.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.35f))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1, 3)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(DARK_BRIMSTONE.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1, 3)
                                                                )
                                                        )
                                        )
                        )
        );
        add(
                MalumCultistEntityTypes.CARDINAL.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .add(
                                                LootItem.lootTableItem(IGNEOUS_CHITIN.get())
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(3, 7)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(VOLATILE_ENTROPY.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.65f))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1, 3)
                                                                )
                                                        )
                                        )
                        )
        );
        add(
                MalumCultistEntityTypes.CHERUB.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .add(
                                                LootItem.lootTableItem(IGNEOUS_CHITIN.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.25f))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1, 2)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(DARK_BRIMSTONE.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                                        )
                        )
        );
        add(
                MalumCultistEntityTypes.EVANGELIST.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .add(
                                                LootItem.lootTableItem(IGNEOUS_CHITIN.get())
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(3, 7)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(ANNULLED_MEMORY.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.65f))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1, 3)
                                                                )
                                                        )
                                        )
                        )
        );

    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return Stream.of(
                MalumCultistEntityTypes.ALTAR.get(),
                MalumCultistEntityTypes.BELIEVER.get(),
                MalumCultistEntityTypes.CARDINAL.get(),
                MalumCultistEntityTypes.CHERUB.get(),
                MalumCultistEntityTypes.EVANGELIST.get()
        );
    }

}