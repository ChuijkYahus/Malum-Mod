package com.sammy.malum.common.item.curiosities.weapons.staff;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.entity.bolt.*;
import com.sammy.malum.common.entity.nitrate.*;
import com.sammy.malum.common.item.spirit.ISpiritAffiliatedItem;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.networked.staff.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.neoforge.client.event.AddAttributeTooltipsEvent;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.lodestone.registry.common.tag.LodestoneDamageTypeTags;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.item.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import java.util.*;

public class UnwindingChaosStaffItem extends AbstractStaffItem implements ISpiritAffiliatedItem {

    public static final List<SpiritHolder<SpiritArcanaType>> SPIRITS = List.of(MalumSpiritTypes.INFERNAL_SPIRIT, MalumSpiritTypes.SACRED_SPIRIT, MalumSpiritTypes.AQUEOUS_SPIRIT, MalumSpiritTypes.EARTHEN_SPIRIT);

    public static SpiritArcanaType getUnwindingChaosSpirit() {
        return SPIRITS.get(MalumMod.RANDOM.nextInt(SPIRITS.size()-1)).value();
    }

    public static final ColorParticleData AURIC_COLOR_DATA = EthericNitrate.AURIC_COLOR_DATA;

    public UnwindingChaosStaffItem(Tier tier, float magicDamage, float chargeRate, int chargeCapacity, LodestoneItemProperties properties) {
        super(tier, magicDamage, chargeRate, chargeCapacity, properties);
    }

    @Override
    public void modifyAttributeTooltipEvent(AddAttributeTooltipsEvent event) {
        event.addTooltipLines(ComponentHelper.positiveEffect("unwinding_chaos_volley"));
        event.addTooltipLines(ComponentHelper.positiveEffect("unwinding_chaos_burn"));
    }

    @Override
    public SpiritLike getDefiningSpiritType() {
        return getUnwindingChaosSpirit();
    }

    @Override
    public void outgoingDeathEvent(LivingDeathEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (!(attacker.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (!target.getData(MalumAttachmentTypes.LIVING_SOUL_INFO).shouldDropSpirits()) {
            return;
        }
        if (target.isOnFire()) {
            addStaffCharges(serverLevel, attacker, target, 160);
        }
    }

    @Override
    public void finalizedOutgoingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        DamageSource source = event.getSource();
        Level level = attacker.level();
        RandomSource random = level.random;

        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        if (source.is(DamageTypeTags.IS_FIRE)) {
            addStaffCharges(serverLevel, attacker, target, 40);
        }

        boolean canTriggerMagic = source.is(LodestoneDamageTypeTags.CAN_TRIGGER_MAGIC_DAMAGE);
        if (canTriggerMagic || source.is(MalumDamageTypes.INVERTED_HEART_PROPAGATION)) {
            target.igniteForSeconds(5);
        }
        if (canTriggerMagic) {
            for (int i = 0; i < 3; i++) {
                MalumParticleEffectTypes.STAFF_SLAM.createEffect()
                        .originatesFrom(attacker)
                        .targets(target)
                        .randomOffset(random, 0.3f, 0.8f * (random.nextBoolean() ? 1 : -1))
                        .forwardOffset(1.4f).upwardOffset(0.3f)
                        .deviation(0.2f)
                        .randomDeviationAngle(random)
                        .color(getUnwindingChaosSpirit())
                        .spawn(serverLevel);

            }
            float pitch = RandomHelper.randomBetween(level.getRandom(), 0.75f, 2f);
            SoundHelper.playSound(attacker, MalumGearSoundEvents.WORLDSOUL_MOTIF_HEAVY_IMPACT.get(), 2f, pitch);
        }
    }

    @Override
    public int getProjectileCount(Level level, LivingEntity livingEntity, float pct) {
        if (pct == 1f) {
            var data = livingEntity.getData(MalumAttachmentTypes.STAFF_ABILITIES);
            return 3 + data.consumeAllStaffCharges(livingEntity) * 2;
        }
        return 0;
    }

    @Override
    public void fireProjectile(LivingEntity player, ItemStack stack, Level level, InteractionHand hand, int count) {
        int ceil = Mth.ceil(count / 2f);
        int spawnDelay = 1 + ceil * 2;
        ceil = ceil % 7;
        float spread = count > 0 ? ceil * 0.1f * (count % 2L == 0 ? 1 : -1) : 0f;
        float pitchOffset = count > 4 ? 2f + (2f - ceil * 1.5f) : 0.5f;
        float velocity = 3f;
        float magicDamage = (float) player.getAttributes().getValue(LodestoneAttributes.MAGIC_DAMAGE);
        var projectile = fireProjectile(player, hand, EntropicFlameBolt::new, velocity, pitchOffset, magicDamage, spawnDelay);
        var direction = projectile.getDeltaMovement();
        float yRot = ((float) (Mth.atan2(direction.x, direction.z) * (double) (180F / (float) Math.PI)));
        float yaw = (float) Math.toRadians(yRot);
        var left = new Vec3(-Math.cos(yaw), 0, Math.sin(yaw));
        var adjusted = projectile.getDeltaMovement().add(left.scale(spread));
        projectile.setDeltaMovement(adjusted);
        level.addFreshEntity(projectile);
    }

    public void addStaffCharges(ServerLevel serverLevel, LivingEntity attacker, LivingEntity target, int charge) {
        attacker.getData(MalumAttachmentTypes.STAFF_ABILITIES).reduceStaffChargeCooldown(attacker, charge);
        float pitch = RandomHelper.randomBetween(attacker.getRandom(), 0.75f, 1.25f);
        SoundHelper.playSound(target, MalumGearSoundEvents.WORLDSOUL_MOTIF_LIGHT_IMPACT.get(), attacker.getSoundSource(), 1.5f, pitch);
        MalumParticleEffectTypes.UNWINDING_CHAOS_CHARGE.createEffect(target)
                .color(getDefiningSpiritType())
                .customData(new UnwindingChaosChargeParticleEffect.UnwindingChaosChargeEffectData(attacker.getId()))
                .spawn(serverLevel);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnChargeParticles(Level pLevel, LivingEntity pLivingEntity, Vec3 pos, ItemStack pStack, float pct) {
        final WorldParticleBuilder builder = WorldParticleBuilder.create(MalumParticles.CHAOS_TARGET)
                .setTransparencyData(GenericParticleData.create(0.7f * pct, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                .setBehavior(DirectionalParticleBehavior.directional(pLivingEntity.getLookAngle().normalize()))
                .setScaleData(GenericParticleData.create(0.3f * pct, 0).setEasing(Easing.SINE_IN_OUT).build())
                .setMotion(pLivingEntity.getLookAngle().normalize().scale(0.2f * pct))
                .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                .enableForcedSpawn()
                .setLifetime(4)
                .enableNoClip();
        for (int i = 0; i < 2; i++) {
            var spin = SpinParticleData.create(0.05f, 0.1f);
            final long delta = (i == 1 ? 1 : -1) * (pLevel.getGameTime() + i * 5);
            float offset = 0.4f * Mth.sin((delta / 8f) % 6.28f);
            var spirit = i == 0 ? MalumSpiritTypes.AQUEOUS_SPIRIT : MalumSpiritTypes.INFERNAL_SPIRIT;
            builder.setColorData(spirit.createColorData().build()).setLifeDelay(i==0 ? 2 : 4)
                    .setSpinData(spin.setSpinOffset(i * 1.57f + offset).build())
                    .setRenderType(LodestoneWorldParticleRenderType.ADDITIVE)
                    .spawn(pLevel, pos.x, pos.y, pos.z)
                    .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                    .spawn(pLevel, pos.x, pos.y, pos.z);
        }
    }
}