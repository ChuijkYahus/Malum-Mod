package com.sammy.malum.common.spiritrite.eldritch;

import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.core.systems.rite.TotemicRiteEffect;
import com.sammy.malum.core.systems.rite.TotemicRiteType;
import com.sammy.malum.registry.common.MalumDataTypes;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import team.lodestar.lodestone.helpers.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.sammy.malum.registry.common.MalumSpiritTypes.*;

public class EldritchWickedRiteType extends TotemicRiteType {
    public EldritchWickedRiteType() {
        super("greater_wicked_rite", ELDRITCH_SPIRIT, ARCANE_SPIRIT, WICKED_SPIRIT, WICKED_SPIRIT);
    }

    @Override
    public TotemicRiteEffect getNaturalRiteEffect() {
        return new TotemicRiteEffect(TotemicRiteEffect.MalumRiteEffectCategory.LIVING_ENTITY_EFFECT) {
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase, ServerLevel level) {
                getNearbyEntities(totemBase, LivingEntity.class, e -> !(e instanceof Player)).forEach(e -> {
                    if (e.getHealth() <= 2.5f && !e.isInvulnerableTo(DamageTypeHelper.create(e.level(), MalumDataTypes.VOODOO_PLAYERLESS))) {
                        MalumParticleEffectTypes.ENTITY_RITE_EFFECT
                                .createEffect(e)
                                .color(WICKED_SPIRIT)
                                .spawn(level);
                        e.hurt(DamageTypeHelper.create(e.level(), MalumDataTypes.VOODOO_PLAYERLESS), 10f);
                    }
                });
            }
        };
    }

    @Override
    public TotemicRiteEffect getCorruptedEffect() {
        return new TotemicRiteEffect(TotemicRiteEffect.MalumRiteEffectCategory.LIVING_ENTITY_EFFECT) {
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase, ServerLevel level) {
                Map<Class<? extends Animal>, List<Animal>> animalMap = getNearbyEntities(totemBase, Animal.class, e -> e.getAge() > 0 && !e.isInvulnerableTo(DamageTypeHelper.create(e.level(), MalumDataTypes.VOODOO_PLAYERLESS))).collect(Collectors.groupingBy(Animal::getClass));
                for (List<Animal> animals : animalMap.values()) {
                    if (animals.size() < 20) {
                        return;
                    }
                    int maxKills = animals.size() - 20;
                    animals.removeIf(Animal::isInLove);
                    for (Animal animal : animals) {
                        animal.hurt(DamageTypeHelper.create(animal.level(), MalumDataTypes.VOODOO_PLAYERLESS), animal.getMaxHealth());
                        MalumParticleEffectTypes.ENTITY_RITE_EFFECT
                                .createEffect(animal)
                                .color(WICKED_SPIRIT)
                                .spawn(level);
                        if (maxKills-- <= 0) {
                            return;
                        }
                    }
                }
            }
        };
    }
}