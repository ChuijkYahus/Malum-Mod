package com.sammy.malum.datagen.entity.cultist;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.registry.common.entity.MalumCultistEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Consumer;

import static com.sammy.malum.registry.common.MalumContent.Materials.*;

public class MalumCultistLootDatagen extends EntityLootSubProvider {

    public MalumCultistLootDatagen(HolderLookup.Provider provider) {
        super(FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
        addCultistLoot(MalumCultistEntityTypes.ALTAR, b -> b
                .chitin(UniformGenerator.between(2, 4), 1f)
                .brimstone(UniformGenerator.between(1, 2), 0.25f)
        );
        addCultistLoot(MalumCultistEntityTypes.BELIEVER, b -> b
                .chitin(UniformGenerator.between(1, 3), 1f)
        );
        addCultistLoot(MalumCultistEntityTypes.CHERUB, b -> b
                .chitin(UniformGenerator.between(1, 2), 1f)
        );

        addCultistLoot(MalumCultistEntityTypes.CARDINAL, b -> b
                .chitin(UniformGenerator.between(6, 8), 1f)
                .brimstone(UniformGenerator.between(3, 6), 1f)
                .special(ANOMALOUS_ENTROPY, ConstantValue.exactly(1), 1f)
        );
        addCultistLoot(MalumCultistEntityTypes.EVANGELIST, b -> b
                .chitin(UniformGenerator.between(8, 14), 1f)
                .special(BLADE_MEMORY, ConstantValue.exactly(1), 1f)
        );
    }

    public void addCultistLoot(DeferredHolder<EntityType<?>, ? extends EntityType<? extends CultistMonster>> entityType, Consumer<CultistLootBuilder> consumer) {
        var builder = new CultistLootBuilder();
        consumer.accept(builder);
        add(entityType.get(), builder.build());
    }
}