package com.sammy.malum.common.sound;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.client.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.resources.sounds.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class CthonicGoldBlockSoundType extends MalumBlockSoundType {

    public CthonicGoldBlockSoundType(String name) {
        super(name);
    }

    @Override
    public void onPlayBreakSound(Level level, Player player, BlockPos pos, BlockState state, EquivalentEffectSoundAcceptor acceptor) {
        acceptor.playSound(MalumBlockSoundEvents.CTHONIC_GOLD_ORE_BREAK_MOTIF, 1.0f, 1.0f);
    }

    @Override
    public void onPlayPlaceSound(Level level, Player player, BlockPos pos, EquivalentEffectSoundAcceptor acceptor) {
        acceptor.playSound(MalumBlockSoundEvents.CTHONIC_GOLD_ORE_PLACE_MOTIF, 1.0f, 1.0f);
    }

    @Override
    public void onPlayHitSound(Level level, Player player, BlockPos pos, EquivalentEffectSoundAcceptor acceptor) {
        var gameMode = Minecraft.getInstance().gameMode;
        if (gameMode != null) {
            float progress = gameMode.destroyProgress;
            float volume = (getVolume() + progress * progress * 4f) / 8f;
            float pitch = getPitch() * (0.5f + 0.25f * progress);
            acceptor.playSound(MalumBlockSoundEvents.CTHONIC_GOLD_ORE_HIT_MOTIF, volume, pitch);
        }
    }
}