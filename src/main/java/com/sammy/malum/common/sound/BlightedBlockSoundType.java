package com.sammy.malum.common.sound;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BlightedBlockSoundType extends MalumBlockSoundType {

    public BlightedBlockSoundType(String name) {
        super(name);
    }

    @Override
    public void onPlayBreakSound(Level level, Player player, BlockPos pos, BlockState state, EquivalentEffectSoundAcceptor acceptor) {
        acceptor.playSound(MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF, -0.75f, 1.5f);
    }

    @Override
    public void onPlayStepSound(Level level, Entity entity, BlockPos pos, BlockState state, EquivalentEffectSoundAcceptor acceptor) {
        if (level.random.nextFloat() < 0.25f) {
            acceptor.playSound(MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF, 1f, 1.5f);
        }
    }

    @Override
    public void onPlayPlaceSound(Level level, Player player, BlockPos pos, EquivalentEffectSoundAcceptor acceptor) {
        acceptor.playSound(MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF, 1f, 1.5f);
    }

    @Override
    public void onPlayHitSound(Level level, Player player, BlockPos pos, EquivalentEffectSoundAcceptor acceptor) {
        if (level.random.nextFloat() < 0.25f) {
            acceptor.playSound(MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF, 1f, 1.5f);
        }
    }
}