package com.sammy.malum.common.spiritrite.effect.sacred;

import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.*;
import net.minecraft.world.level.block.*;

import static net.minecraft.world.entity.ai.goal.EatBlockGoal.IS_TALL_GRASS;

public interface NurturingEffectActor<T extends Mob> {

    NurturingEffectActor<Sheep> SHEEP = new NurturingEffectActor<>() {
        @Override
        public boolean act(ServerLevel level, Sheep sheep) {
            if (sheep.getRandom().nextFloat() < 0.6f) {
                BlockPos pos = sheep.blockPosition();
                if (IS_TALL_GRASS.test(level.getBlockState(pos)) || level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) {
                    EatBlockGoal goal = sheep.eatBlockGoal;
                    goal.start();
                    return true;
                }
            }
            return false;
        }

        @Override
        public Class<Sheep> getTargetClass() {
            return Sheep.class;
        }
    };

    NurturingEffectActor<Bee> BEE = new NurturingEffectActor<>() {
        @Override
        public boolean act(ServerLevel level, Bee bee) {
            Bee.BeePollinateGoal goal = bee.beePollinateGoal;
            if (goal.canBeeUse()) {
                goal.successfulPollinatingTicks += 40;
                goal.tick();
                return true;
            }
            return false;
        }

        @Override
        public Class<Bee> getTargetClass() {
            return Bee.class;
        }
    };

    NurturingEffectActor<Chicken> CHICKEN = new NurturingEffectActor<>() {
        @Override
        public boolean act(ServerLevel level, Chicken chicken) {
            chicken.eggTime -= 80;
            return true;
        }

        @Override
        public Class<Chicken> getTargetClass() {
            return Chicken.class;
        }
    };

    NurturingEffectActor<Allay> ALLAY = new NurturingEffectActor<>() {
        @Override
        public boolean act(ServerLevel level, Allay allay) {
            allay.duplicationCooldown -= 80;
            return true;
        }

        @Override
        public Class<Allay> getTargetClass() {
            return Allay.class;
        }
    };

    @SuppressWarnings("unchecked")
    default boolean tryAct(ServerLevel level, LivingEntity target) {
        if (getTargetClass().isInstance(target)) {
            return act(level, (T) target);
        }
        return false;
    }

    boolean act(ServerLevel level, T entity);

    Class<T> getTargetClass();
}
