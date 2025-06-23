package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.*;
import com.llamalad7.mixinextras.sugar.*;
import com.mojang.datafixers.util.*;
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
    HashMap<BlockPos, BlockState> malum$cachedBlockStates;

    @Mutable
    @Shadow
    @Final
    private float radius;

    @Shadow
    @Nullable
    public abstract LivingEntity getIndirectSourceEntity();

    @Shadow @Final @Nullable private Entity source;

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Explosion$BlockInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)V", at = @At(value = "RETURN"))
    private void malum$modifyExplosion(Level level, Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, Explosion.BlockInteraction blockInteraction, ParticleOptions smallExplosionParticles, ParticleOptions largeExplosionParticles, Holder explosionSound, CallbackInfo ci) {
        LivingEntity sourceEntity = getIndirectSourceEntity();
        this.radius = CurioDemolitionistRing.increaseExplosionRadius(sourceEntity, radius);
    }

    @Inject(method = "finalizeExplosion", at = @At(value = "HEAD"))
    private void malum$CacheHoarderRing(boolean pSpawnParticles, CallbackInfo ci) {
        malum$hasHoarderRing = CurioHoarderRing.hasHoarderRing(getIndirectSourceEntity());
    }

    @ModifyReturnValue(method = "finalizeExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", ordinal = 0))
    private BlockState malum$captureBlockState(BlockState state, BlockPos pos) {
        if (malum$cachedBlockStates == null) {
            malum$cachedBlockStates = new HashMap<>();
        }
        malum$cachedBlockStates.put(pos, state);
        return state;
    }

    @ModifyArgs(method = "finalizeExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;popResource(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V"))
    private void malum$popResource(Args args, @Local(ordinal = 0) List<Pair<ItemStack, BlockPos>> dropList) {
        BlockPos pos = args.get(1);
        ItemStack stack = args.get(2);

        if (malum$cachedBlockStates != null) {
            ProspectorGeas.modifyExplosionDrops(source, dropList, malum$cachedBlockStates::get);
            malum$cachedBlockStates = null;
        }

        args.set(1, CurioHoarderRing.getExplosionPos(malum$hasHoarderRing, pos, getIndirectSourceEntity(), stack));
    }

    @Inject(method = "getIndirectSourceEntityInternal", at = @At(value = "HEAD"), cancellable = true)
    private static void malum$modifyIndirectSourceEntity(Entity source, CallbackInfoReturnable<LivingEntity> cir) {
        AbstractNitrateEntity.getOwnerFromExplosion(source).ifPresent(cir::setReturnValue);
    }
}