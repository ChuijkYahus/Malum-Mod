package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.core.systems.rite.category.SpiritRiteAuraCategory;
import com.sammy.malum.core.systems.rite.category.SpiritRiteEffectCategory;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.*;

import java.util.*;

import static com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData.fromSpirits;

public abstract class SpiritRiteEntityEffect<T extends LivingEntity> extends SpiritRiteEffect {

    protected SpiritRiteEntityEffect() {
        super(SpiritRiteAuraCategory.AURA);
    }

    public abstract Class<T> getTargetClass();

    public abstract void applyEffect(ServerLevel level, T target);

    public boolean canApplyEffect(ServerLevel level, T target) {
        return true;
    }

    public List<T> findNearbyTargets(ServerLevel level, BlockPos source) {
        AABB area = new AABB(source).inflate(getCategory().getEffectRange());
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
