package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.*;
import com.llamalad7.mixinextras.sugar.*;
import com.mojang.datafixers.util.*;
import com.sammy.malum.common.data.attachment.*;
import com.sammy.malum.common.entity.nitrate.*;
import com.sammy.malum.common.geas.pact.infernal.*;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.CurioDemolitionistRing;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.CurioHoarderRing;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.common.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.*;

import javax.annotation.Nullable;
import java.util.*;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Unique
    boolean malum$hasHoarderRing;

    @Unique
    boolean malum$hasProspectorGeas;

    @Mutable
    @Shadow
    @Final
    private float radius;

    @Shadow
    @Nullable
    public abstract LivingEntity getIndirectSourceEntity();

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Explosion$BlockInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)V", at = @At(value = "RETURN"))
    private void malum$modifyExplosion(Level level, Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, Explosion.BlockInteraction blockInteraction, ParticleOptions smallExplosionParticles, ParticleOptions largeExplosionParticles, Holder explosionSound, CallbackInfo ci) {
        LivingEntity sourceEntity = getIndirectSourceEntity();
        this.radius = CurioDemolitionistRing.increaseExplosionRadius(sourceEntity, radius);
    }

    @Inject(method = "finalizeExplosion", at = @At(value = "HEAD"))
    private void malum$CacheHoarderRing(boolean pSpawnParticles, CallbackInfo ci) {
        LivingEntity entity = getIndirectSourceEntity();
        malum$hasHoarderRing = CurioHoarderRing.hasHoarderRing(entity);
        malum$hasProspectorGeas = ProspectorGeas.hasProspector(entity);
    }

    //TODO: This shouldn't use a redirect, hopefully it's fine!
    @Redirect(method = "finalizeExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;popResource(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V"))
    private void malum$popResource(Level level, BlockPos pos, ItemStack stack) {
        pos = CurioHoarderRing.getExplosionPos(malum$hasHoarderRing, pos, getIndirectSourceEntity(), stack);
        if (malum$hasProspectorGeas) {
            ProspectorGeas.popResourceAndMarkEntity(level, pos, stack);
        } else {
            Block.popResource(level, pos, stack);
        }
    }

    @Inject(method = "getIndirectSourceEntityInternal", at = @At(value = "HEAD"), cancellable = true)
    private static void malum$modifyIndirectSourceEntity(Entity source, CallbackInfoReturnable<LivingEntity> cir) {
        AbstractNitrateEntity.getOwnerFromExplosion(source).ifPresent(cir::setReturnValue);
    }
}