package com.sammy.malum.common.item.nucleus;

import net.minecraft.core.particles.*;
import net.minecraft.core.registries.*;
import net.minecraft.sounds.*;
import net.minecraft.stats.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

public class WindNucleusItem extends Item {
    public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
            true, false, Optional.of(3f), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
    );

    public WindNucleusItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        float radius = 1.5f;
        float addedMotion = 2f;
        Vec3 pos = player.getEyePosition().add(player.getLookAngle().scale(3f));
        level.explode(
                player,
                null,
                EXPLOSION_DAMAGE_CALCULATOR,
                pos.x(),
                pos.y(),
                pos.z(),
                radius,
                false,
                Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.GUST,
                ParticleTypes.GUST,
                SoundEvents.WIND_CHARGE_BURST
        );
        var explosionAffectedEntities = getExplosionAffectedEntities(level, player, pos, radius);

        for (Entity entity : explosionAffectedEntities) {
            final Vec3 movement = entity.getDeltaMovement();
            entity.setDeltaMovement(player.getLookAngle().normalize().scale(Math.max(movement.length(), addedMotion)));
        }
        ItemStack itemstack = player.getItemInHand(usedHand);
        player.getCooldowns().addCooldown(this, 10);
        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.success(itemstack);
    }

    public static List<Entity> getExplosionAffectedEntities(Level level, @Nullable Entity ignored, Vec3 pos, float radius) {
        float f2 = radius * 2.0F;
        int k1 = Mth.floor(pos.x - (double)f2 - 1.0);
        int l1 = Mth.floor(pos.x + (double)f2 + 1.0);
        int i2 = Mth.floor(pos.y - (double)f2 - 1.0);
        int i1 = Mth.floor(pos.y + (double)f2 + 1.0);
        int j2 = Mth.floor(pos.z - (double)f2 - 1.0);
        int j1 = Mth.floor(pos.z + (double)f2 + 1.0);
        return level.getEntities(ignored, new AABB(k1, i2, j2, l1, i1, j1));
    }
}