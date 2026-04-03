package com.sammy.malum.common.sound;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class QuartzClusterBlockSoundType extends MalumBlockSoundType {

    public QuartzClusterBlockSoundType(String name) {
        super(name);
    }

    @Override
    public void onPlayBreakSound(Level level, Player player, BlockPos pos, BlockState state, EquivalentEffectSoundAcceptor acceptor) {
        acceptor.playSound(SoundEvents.AMETHYST_CLUSTER_BREAK, 1f, 0.75f);
    }

    @Override
    public void onPlayStepSound(Level level, Entity entity, BlockPos pos, BlockState state, EquivalentEffectSoundAcceptor acceptor) {
        if (level.random.nextFloat() < 0.25f) {
            acceptor.playSound(SoundEvents.AMETHYST_CLUSTER_STEP, 1f, 1.5f);
        }
    }

    @Override
    public void onPlayPlaceSound(Level level, Player player, BlockPos pos, EquivalentEffectSoundAcceptor acceptor) {
        acceptor.playSound(SoundEvents.AMETHYST_CLUSTER_PLACE, 1f, 1.25f);
    }

    @Override
    public void onPlayHitSound(Level level, Player player, BlockPos pos, EquivalentEffectSoundAcceptor acceptor) {
        if (level.random.nextFloat() < 0.5f) {
            acceptor.playSound(SoundEvents.AMETHYST_CLUSTER_HIT, 1f, 1.5f);
        }
    }
}