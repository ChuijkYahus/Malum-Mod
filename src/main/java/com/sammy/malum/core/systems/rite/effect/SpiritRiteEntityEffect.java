package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.common.entity.activator.rite.EntityRiteEffectActivator;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.sound.SoundPlayer;

import java.util.*;
import java.util.stream.Stream;

import static com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData.fromSpirits;

public abstract class SpiritRiteEntityEffect<T extends LivingEntity> extends SpiritRiteEffect {

    protected SpiritRiteEntityEffect(SpiritRiteEffectTag... tags) {
        this(Arrays.asList(tags));
    }

    protected SpiritRiteEntityEffect(List<SpiritRiteEffectTag> tags) {
        super(Stream.concat(Stream.of(SpiritRiteEffectTag.RADIAL_EFFECT), tags.stream()).toList());
    }

    @Override
    public boolean triggerRiteEffect(ServerLevel level, BlockPos pos, SpiritArcanaType definingSpirit, RiteEffectConfig parameters) {
        List<T> nearbyTargets = findNearbyTargets(level, pos);
        if (nearbyTargets.isEmpty()) {
            return false;
        }
        var random = level.getRandom();
        int totemHeight = parameters.getTotemHeight();
        int counter = 2; //It'd be cool if you could increase the effectiveness of a rite by modifying the spirit rune placement a little
        //Maybe adding an extra arcane spirit to the bottom could increase the range of the rite
        //Adding an extra effect core spirit to the top could increase the amount of entities it can affect at once

        if (counter > nearbyTargets.size()) {
            counter = nearbyTargets.size();
        }
        Collections.shuffle(nearbyTargets);
        for (int i = 0; i < counter; i++) {
            var target = nearbyTargets.get(i);
            var uuid = target.getUUID();
            var position = pos.getCenter().add(0, totemHeight, 0);
            var velocity = new Vec3(
                    Easing.SINE_IN_OUT.asWeighedRandom(random, 0.3f, 0.6f) * (random.nextBoolean() ? 1 : -1),
                    Easing.SINE_IN_OUT.asWeighedRandom(random, 0.1f, 0.2f),
                    Easing.SINE_IN_OUT.asWeighedRandom(random, 0.3f, 0.6f) * (random.nextBoolean() ? 1 : -1)
            );
            EntityRiteEffectActivator entity = new EntityRiteEffectActivator(level, this, uuid, position, velocity);
            entity.setSpirit(definingSpirit);
            level.addFreshEntity(entity);
            SoundPlayer.create(MalumSoundEvents.SPARK_FORMED).volume(0.5f).pitchVariance(0.2f).play(entity);
        }
        return true;
    }

    public abstract Class<T> getTargetClass();

    public void tryApplyEffect(ServerLevel level, LivingEntity target) {
        if (getTargetClass().isInstance(target)) {
            applyEffect(level, getTargetClass().cast(target));
        }
    }

    public abstract void applyEffect(ServerLevel level, T target);

    public boolean canApplyEffect(ServerLevel level, T target) {
        return true;
    }

    public Holder<SoundEvent> getImpactSound() {
        return MalumSoundEvents.SPARK_IMPACT;
    }

    public float getImpactSoundVolume(LivingEntity target) {
        return 0.4f;
    }

    public int getEffectRange() {
        return 8;
    }

    public List<T> findNearbyTargets(ServerLevel level, BlockPos source) {
        AABB area = new AABB(source).inflate(getEffectRange());
        return new ArrayList<>(level.getEntitiesOfClass(getTargetClass(), area, e -> {
            if (e instanceof Player player && player.isSpectator()) {
                return false;
            }
            return canApplyEffect(level, e);
        }));
    }

    protected void createEffect(ServerLevel level, T target, SpiritLike... spirits) {
        createEffect(level, target, Arrays.asList(spirits));
    }

    protected void createEffect(ServerLevel level, T target, List<? extends SpiritLike> spirits) {
        MalumParticleEffectTypes.ENTITY_RITE_EFFECT
                .createEffect(target)
                .color(fromSpirits(spirits))
                .spawn(level);
    }
}