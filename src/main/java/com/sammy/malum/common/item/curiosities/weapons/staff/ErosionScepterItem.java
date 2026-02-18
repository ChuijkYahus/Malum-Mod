package com.sammy.malum.common.item.curiosities.weapons.staff;

import com.sammy.malum.common.entity.bolt.*;
import com.sammy.malum.common.item.spirit.ISpiritAffiliatedItem;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.AddAttributeTooltipsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.lodestone.registry.common.tag.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.item.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import java.awt.*;

public class ErosionScepterItem extends AbstractStaffItem implements ISpiritAffiliatedItem {

    public static final Color MALIGNANT_PURPLE = new Color(68, 11, 61);
    public static final Color MALIGNANT_BLACK = new Color(12, 4, 11);
    public static final ColorParticleData SCEPTER_COLOR_DATA = ColorParticleData.create(MALIGNANT_PURPLE, MALIGNANT_BLACK).setEasing(Easing.BOUNCE_IN_OUT).setCoefficient(1.2f).build().lock();

    public ErosionScepterItem(Tier tier, float magicDamage, float chargeRate, int chargeCapacity, LodestoneItemProperties properties) {
        super(tier, magicDamage, chargeRate, chargeCapacity, properties);
    }

    @Override
    public void modifyAttributeTooltipEvent(AddAttributeTooltipsEvent event) {
        event.addTooltipLines(ComponentHelper.positiveEffect("erosive_spread"));
        event.addTooltipLines(ComponentHelper.positiveEffect("erosive_silence"));
    }

    @Override
    public SpiritLike getDefiningSpiritType() {
        return MalumSpiritTypes.UMBRAL_SPIRIT;
    }
    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (!(event.getSource().getDirectEntity() instanceof AbstractBoltProjectile) && event.getSource().is(LodestoneDamageTypeTags.IS_MAGIC)) {
            var silenced = MalumMobEffects.SILENCED;
            var effect = target.getEffect(silenced);
            if (effect == null) {
                target.addEffect(new MobEffectInstance(silenced, 150, 0, true, true, true));
            } else {
                EntityHelper.amplifyEffect(effect, target, 1, 19);
                EntityHelper.extendEffect(effect, target, 30, 300);
            }
            if (!event.getSource().is(MalumTags.DamageTypeTags.IS_INVERTED_HEART)) {
                SoundHelper.playSound(target, MalumSoundEvents.DRAINING_MOTIF.get(), attacker.getSoundSource(), 1, 1.25f);
            }
        }
        super.outgoingDamageEvent(event, attacker, target, stack);
    }

    @Override
    public int getProjectileCount(Level level, LivingEntity livingEntity, float pct) {
        return pct == 1f ? 2 : 0;
    }

    @Override
    public void fireProjectile(LivingEntity player, ItemStack stack, Level level, InteractionHand hand, int count) {
        int spawnDelay = count * 5;
        float pitchOffset = count * 1.5f;
        float velocity = 4f;
        float magicDamage = (float) player.getAttributes().getValue(LodestoneAttributes.MAGIC_DAMAGE) * 0.3f;
        for (int i = 0; i < 4; i++) {
            float xSpread = RandomHelper.randomBetween(level.random, -0.125f, 0.125f);
            float ySpread = RandomHelper.randomBetween(level.random, -0.025f, 0.025f);
            var projectile = fireProjectile(player, hand, DrainingBolt::new, velocity, pitchOffset, magicDamage, spawnDelay);
            var direction = projectile.getDeltaMovement();
            float yRot = ((float) (Mth.atan2(direction.x, direction.z) * (double) (180F / (float) Math.PI)));
            float yaw = (float) Math.toRadians(yRot);
            var left = new Vec3(-Math.cos(yaw), 0, Math.sin(yaw));
            var up = left.cross(direction);
            var adjusted = projectile.getDeltaMovement().add(left.scale(xSpread)).add(up.scale(ySpread));
            projectile.setDeltaMovement(adjusted);
            level.addFreshEntity(projectile);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnChargeParticles(Level pLevel, LivingEntity pLivingEntity, Vec3 pos, ItemStack pStack, float pct) {
        RandomSource random = pLevel.random;
        WorldParticleBuilder.create(MalumParticles.DRAINING_TARGET)
                .setBehavior(DirectionalParticleBehavior.directional(pLivingEntity.getLookAngle().normalize()))
                .setSpinData(SpinParticleData.createRandomDirection(random, 0.1f, 0.2f).setSpinOffset(RandomHelper.randomBetween(random, -0.314f, 0.314f)).build())
                .setTransparencyData(GenericParticleData.create(0.8f * pct, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setColorData(SCEPTER_COLOR_DATA.copy().setCoefficient(2f).build())
                .setScaleData(GenericParticleData.create(0.3f * pct, 0).setEasing(Easing.SINE_IN_OUT).build())
                .setMotion(pLivingEntity.getLookAngle().normalize().scale(0.05f))
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .enableForcedSpawn()
                .setLifeDelay(2)
                .enableNoClip()
                .setLifetime(5)
                .spawn(pLevel, pos.x, pos.y, pos.z)
                .setScaleData(GenericParticleData.create(0.4f * pct, 0).setEasing(Easing.SINE_IN_OUT).build())
                .setColorData(SCEPTER_COLOR_DATA.invert().build())
                .spawn(pLevel, pos.x, pos.y, pos.z);
    }
}
