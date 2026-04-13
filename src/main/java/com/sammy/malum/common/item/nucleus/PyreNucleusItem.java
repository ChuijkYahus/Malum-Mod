package com.sammy.malum.common.item.nucleus;

import com.sammy.malum.registry.common.MalumAttachmentTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.modules.toolkit.item.LodestoneFuelItem;

import java.util.Optional;

import static com.sammy.malum.common.item.nucleus.WindNucleusItem.getExplosionAffectedEntities;

public class PyreNucleusItem extends Item {
    public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
            false, true, Optional.of(0.5f), Optional.empty()
    );

    public PyreNucleusItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        float radius = 1.75f;
        Vec3 pos = player.getEyePosition().add(player.getLookAngle().scale(3f));
        var explosionAffectedEntities = getExplosionAffectedEntities(level, player, pos, radius);

        for (Entity entity : explosionAffectedEntities) {
            entity.igniteForSeconds(5);
            if (entity instanceof LivingEntity living) {
                living.getData(MalumAttachmentTypes.LIVING_SOUL_INFO).setExposed();
            }
        }
        level.explode(
                player,
                Explosion.getDefaultDamageSource(level, player),
                EXPLOSION_DAMAGE_CALCULATOR,
                pos.x(),
                pos.y(),
                pos.z(),
                radius,
                false,
                Level.ExplosionInteraction.NONE,
                ParticleTypes.EXPLOSION,
                ParticleTypes.EXPLOSION_EMITTER,
                SoundEvents.GENERIC_EXPLODE
        );
        var itemstack = player.getItemInHand(usedHand);
        player.getCooldowns().addCooldown(this, 10);
        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.success(itemstack);
    }
}