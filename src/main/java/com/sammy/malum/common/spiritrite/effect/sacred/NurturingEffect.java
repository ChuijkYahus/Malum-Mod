package com.sammy.malum.common.spiritrite.effect.sacred;

import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class NurturingEffect extends SpiritRiteEntityEffect<Mob> {

    public static final HashMap<Class<? extends Mob>, NurturingEffectActor<?>> ACTORS = new HashMap<>();
    static {
        ACTORS.put(Sheep.class, NurturingEffectActor.SHEEP);
        ACTORS.put(Bee.class, NurturingEffectActor.BEE);
        ACTORS.put(Chicken.class, NurturingEffectActor.CHICKEN);
        ACTORS.put(Allay.class, NurturingEffectActor.ALLAY);
    }

    public NurturingEffect() {
        super();
    }

    @Override
    public Class<Mob> getTargetClass() {
        return Mob.class;
    }

    @Override
    public void applyEffect(ServerLevel level, Mob target) {
        boolean success = false;
        if (target instanceof Animal animal) {
            if (animal.getAge() < 0) {
                if (level.getRandom().nextFloat() <= 0.04f) {
                    animal.ageUp(25);
                    success = true;
                }
            }
        }
        var actor = ACTORS.get(target.getClass());
        if (actor != null) {
            success = actor.tryAct(level, target);
        }
        if (success) {
            createEffect(level, target, ELDRITCH_SPIRIT, SACRED_SPIRIT);
        }
    }
}
