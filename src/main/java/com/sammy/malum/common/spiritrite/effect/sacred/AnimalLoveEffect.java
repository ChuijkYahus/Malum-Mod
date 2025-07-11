package com.sammy.malum.common.spiritrite.effect.sacred;

import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.animal.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class AnimalLoveEffect extends SpiritRiteEntityEffect<Animal> {

    public AnimalLoveEffect() {
        super();
    }

    @Override
    public Class<Animal> getTargetClass() {
        return Animal.class;
    }

    @Override
    public void applyEffect(ServerLevel level, Animal target) {
        target.setInLoveTime(600);
        createEffect(level, target, ELDRITCH_SPIRIT, SACRED_SPIRIT);
    }

    @Override
    public boolean canApplyEffect(ServerLevel level, Animal target) {
        if (target instanceof Animal animal) {
            return animal.canFallInLove() && animal.getAge() == 0;
        }
        return false;
    }
}