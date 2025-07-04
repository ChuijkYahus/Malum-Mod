package com.sammy.malum.common.spiritrite.arcane;

import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.spiritrite.effect.sacred.*;
import com.sammy.malum.core.systems.rite.OldTotemicRiteEffect;
import com.sammy.malum.core.systems.rite.SpiritRiteType;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.ARCANE_SPIRIT;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.SACRED_SPIRIT;

public class SacredRiteType extends SpiritRiteType {

    public SacredRiteType() {
        super("sacred_rite", ARCANE_SPIRIT, SACRED_SPIRIT, SACRED_SPIRIT);
    }

    @Override
    public OldTotemicRiteEffect getNaturalRiteEffect() {
        return new OldTotemicRiteEffect(OldTotemicRiteEffect.MalumRiteEffectCategory.LIVING_ENTITY_EFFECT) {
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase, ServerLevel level) {
                getNearbyEntities(totemBase, LivingEntity.class, e -> !(e instanceof Monster)).forEach(e -> {
                    if (e.getHealth() < e.getMaxHealth()) {
                        e.heal(2);
                        MalumParticleEffectTypes.ENTITY_RITE_EFFECT
                                .createEffect(e)
                                .color(SACRED_SPIRIT)
                                .spawn(level);
                    }
                });
            }
        };
    }

    @Override
    public OldTotemicRiteEffect getCorruptedEffect() {
        return new OldTotemicRiteEffect(OldTotemicRiteEffect.MalumRiteEffectCategory.LIVING_ENTITY_EFFECT) {
            @SuppressWarnings("DataFlowIssue")
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase, ServerLevel level) {
                getNearbyEntities(totemBase, Mob.class).forEach(e -> {
                    if (e instanceof Animal animal) {
                        if (animal.getAge() < 0) {
                            if (totemBase.getLevel().random.nextFloat() <= 0.04f) {
                                MalumParticleEffectTypes.ENTITY_RITE_EFFECT
                                        .createEffect(e)
                                        .color(SACRED_SPIRIT)
                                        .spawn(level);
                                animal.ageUp(25);
                            }
                        }
                    }
                    if (NOURISHMENT_RITE_ACTORS.containsKey(e.getClass())) {
                        NurturingEffectActor<? extends Mob> nurturingEffectActor = NOURISHMENT_RITE_ACTORS.get(e.getClass());
                        nurturingEffectActor.tryAct(level, totemBase, e);
                    }
                });
            }
        };
    }

}
