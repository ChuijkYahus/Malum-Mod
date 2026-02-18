package com.sammy.malum.common.sound;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
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
    public void onPlayBreakSound(Level level, BlockPos pos) {
        level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, (getVolume() + 1.0F) * 0.15f, getPitch() * 1.9F, false);
    }

    @Override
    public void onPlayStepSound(Level level, BlockPos pos, BlockState state, SoundSource category) {
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF.get(), category, getVolume() * 0.1F, getPitch() * 1.6F);
    }

    @Override
    public void onPlayPlaceSound(Level level, BlockPos pos, Player player) {
        level.playSound(player, pos, MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, (getVolume() + 1.0F) * 0.25f, getPitch() * 1.8F);
    }

    @Override
    @OnlyIn(value = Dist.CLIENT)
    public void onPlayHitSound(BlockPos pos) {
        Minecraft.getInstance().getSoundManager().play(new SimpleSoundInstance(MalumBlockSoundEvents.MINOR_BLIGHT_MOTIF.get(), SoundSource.BLOCKS, (getVolume() + 1.0F) * 0.05f, getPitch() * 2F, MalumMod.RANDOM, pos));
    }

    @Override
    public void onPlayFallSound(Level level, BlockPos pos, SoundSource category) {
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), MalumBlockSoundEvents.MAJOR_BLIGHT_MOTIF.get(), category, getVolume() * 0.25f, getPitch() * 1.7F);
    }
}