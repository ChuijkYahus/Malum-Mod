package com.sammy.malum.common.item.curiosities.curios.sets.scythe;

import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.particles.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.chat.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;

import java.util.*;
import java.util.function.*;

public class CurioRisingEdgeRing extends MalumCurioItem {
    private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
            true, false, Optional.of(0.5f), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
    );

    public CurioRisingEdgeRing(Properties builder) {
        super(builder, MalumTrinketType.METALLIC);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("ascension_launch"));
        consumer.accept(ComponentHelper.negativeCurioEffect("lower_ascension_damage"));
    }

    public static void launchEntity(LivingEntity attacker, LivingEntity target, boolean isUppercut) {
        float velocity = isUppercut ? 0.8f : 0.5f;
        attacker.level().explode(
                attacker,
                null,
                EXPLOSION_DAMAGE_CALCULATOR,
                target.position().x(),
                target.position().y(),
                target.position().z(),
                1.2f,
                false,
                Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.GUST,
                ParticleTypes.GUST,
                MalumGearSoundEvents.SCYTHE_ASCENSION_LAUNCH
        );
        target.setDeltaMovement(target.getDeltaMovement().add(0, velocity, 0));
    }
}