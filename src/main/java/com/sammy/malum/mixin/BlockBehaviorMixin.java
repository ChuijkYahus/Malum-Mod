package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.sugar.*;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.CurioProspectorBelt;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(BlockBehaviour.class)
public class BlockBehaviorMixin {

    @ModifyArg(method = "onExplosionHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getDrops(Lnet/minecraft/world/level/storage/loot/LootParams$Builder;)Ljava/util/List;"))
    private LootParams.Builder malum$getBlockDrops(LootParams.Builder builder, @Local(argsOnly = true) Explosion explosion) {
        return CurioProspectorBelt.applyFortune(explosion.getIndirectSourceEntity(), builder);
    }
}
