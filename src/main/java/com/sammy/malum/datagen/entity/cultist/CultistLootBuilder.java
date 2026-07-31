package com.sammy.malum.datagen.entity.cultist;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.ArrayList;

import static com.sammy.malum.registry.common.MalumContent.Materials.IGNEOUS_CHITIN;
import static com.sammy.malum.registry.common.MalumContent.Materials.TORN_BRIMSTONE;

public class CultistLootBuilder {

    public record CultistLootDrop(ItemLike item, NumberProvider amount, float chance) {

        public LootPoolSingletonContainer.Builder<?> createDrop() {
            var builder = LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(amount()));

            if (chance() < 1f) {
                return builder.when(LootItemRandomChanceCondition.randomChance(chance()));
            }
            return builder;
        }
    }

    protected ArrayList<CultistLootDrop> drops;

    public CultistLootBuilder chitin(NumberProvider amount, float chance) {
        return special(IGNEOUS_CHITIN, amount, chance);
    }

    public CultistLootBuilder brimstone(NumberProvider amount, float chance) {
        return special(TORN_BRIMSTONE, amount, chance);
    }

    public CultistLootBuilder special(ItemLike specialDrop, NumberProvider amount, float chance) {
        drops.add(new CultistLootDrop(specialDrop, amount, chance));
        return this;
    }

    public LootTable.Builder build() {
        var pool = LootPool.lootPool();
        for (CultistLootDrop drop : drops) {
            pool.add(drop.createDrop());
        }

        return LootTable.lootTable().withPool(pool);
    }
}