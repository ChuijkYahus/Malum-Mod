package com.sammy.malum.common.spiritrite.effect.wicked;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteEntityEffect;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.Animal;
import team.lodestar.lodestone.helpers.DamageTypeHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class AnimalCullingEffect extends SpiritRiteEntityEffect<Animal> {

    public AnimalCullingEffect() {
        super();
    }

    @Override
    public Class<Animal> getTargetClass() {
        return Animal.class;
    }

    @Override
    public void applyEffect(ServerLevel level, Animal target) {
        DamageSource damageSource = DamageTypeHelper.create(level, MalumDamageTypes.VOODOO_PLAYERLESS);
        target.hurt(damageSource, target.getMaxHealth() * 2); //For good measure
        createEffect(level, target, ELDRITCH_SPIRIT, WICKED_SPIRIT);
    }

    @Override
    public List<Animal> findNearbyTargets(ServerLevel level, BlockPos source) {
        List<Animal> targets = super.findNearbyTargets(level, source);
        //Group animals by class type, each type of animal is only considered valid when at least 20 specimens are present
        var sorted = new HashSet<>(targets.stream().collect(Collectors.groupingBy(Animal::getClass)).values());
        for (List<Animal> group : sorted) {
            if (group.size() < 20) {
                targets.removeAll(group);
            }
        }
        return targets;
    }

    @Override
    public boolean canApplyEffect(ServerLevel level, Animal target) {
        DamageSource damageSource = DamageTypeHelper.create(level, MalumDamageTypes.VOODOO_PLAYERLESS);
        if (target.isInvulnerableTo(damageSource)) {
            return false;
        }
        if (target.isInLove()) {
            return false;
        }
        return target.getAge() >= 0;
    }
}