package com.sammy.malum.common.spiritrite.arcane;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.spiritrite.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import java.util.function.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class InfernalRiteType extends SpiritRiteType {
    public InfernalRiteType() {
        super("infernal_rite", ARCANE_SPIRIT, INFERNAL_SPIRIT, INFERNAL_SPIRIT);
    }

    @Override
    public OldTotemicRiteEffect getNaturalRiteEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.MINERS_RAGE);
    }

    @Override
    public OldTotemicRiteEffect getCorruptedEffect() {
        return new PotionRiteEffectOld(MalumSpiritTypes.AERIAL_SPIRIT, LivingEntity.class, MalumMobEffects.IFRITS_EMBRACE) {

            @SuppressWarnings("ConstantConditions")
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase, ServerLevel level) {
                super.doRiteEffect(totemBase, level);
                getNearbyBlocks(totemBase, BaseFireBlock.class).forEach(p -> {
                    BlockState state = totemBase.getLevel().getBlockState(p);
                    if (!state.is(MalumTags.BlockTags.INEXTINGUISHABLE_FLAME)) {
                        level.playSound(null, p, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
                        totemBase.getLevel().removeBlock(p, false);
                    }
                });
            }

            @Override
            public Predicate<LivingEntity> getEntityPredicate() {
                return super.getEntityPredicate().and(Entity::isOnFire);
            }
        };
    }
}