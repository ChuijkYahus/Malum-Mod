package com.sammy.malum.core.systems.rite.effect;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.entity.activator.RiteEffectActivatorEntity;
import com.sammy.malum.common.entity.activator.SpiritCollectionActivatorEntity;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.RandomHelper;

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
    public void triggerRiteEffect(ServerLevel level, TotemBaseBlockEntity totemBase) {
        List<T> nearbyTargets = findNearbyTargets(level, totemBase.getBlockPos());
        if (nearbyTargets.isEmpty()) {
            return;
        }
        var random = totemBase.getLevel().getRandom();
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
            var position = totemBase.getBlockPos().getCenter().add(0, totemBase.getTotemHeight(), 0);
            var velocity = new Vec3(
                    RandomHelper.randomBetween(random, 0.3f, 0.6f) * (random.nextBoolean() ? 1 : -1),
                    RandomHelper.randomBetween(random, 0.1f, 0.2f),
                    RandomHelper.randomBetween(random, 0.3f, 0.6f) * (random.nextBoolean() ? 1 : -1)
            );
            RiteEffectActivatorEntity entity = new RiteEffectActivatorEntity(level, uuid, position, velocity);
            level.addFreshEntity(entity);
        }
    }

    public abstract Class<T> getTargetClass();

    public abstract void applyEffect(ServerLevel level, T target);

    public boolean canApplyEffect(ServerLevel level, T target) {
        return true;
    }

    public int getEffectRange() {
        return 8;
    }

    public List<T> findNearbyTargets(ServerLevel level, BlockPos source) {
        AABB area = new AABB(source).inflate(getEffectRange());
        return new ArrayList<>(level.getEntitiesOfClass(getTargetClass(), area, e -> canApplyEffect(level, e)));
    }

        public boolean tryApplyEffect(ServerLevel level, T target) {
        if (canApplyEffect(level, target)) {
            applyEffect(level, target);
            return true;
        }
        return false;
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
