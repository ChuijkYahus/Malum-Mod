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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import team.lodestar.lodestone.systems.sound.*;

import java.util.function.*;

public class CthonicGoldSoundType extends MalumSoundType {

    public CthonicGoldSoundType(String name) {
        super(name);
    }

    @Override
    public void onPlayBreakSound(Level level, BlockPos pos) {
        level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, MalumSoundEvents.CTHONIC_GOLD_ORE_BREAK_MOTIF.get(), SoundSource.BLOCKS, (getVolume() + 1.0F) / 2.0F, getPitch() - level.random.nextFloat() * 0.4f, false);
    }

    @Override
    public void onPlayPlaceSound(Level level, BlockPos pos, Player player) {
        level.playSound(player, pos, MalumSoundEvents.CTHONIC_GOLD_ORE_PLACE_MOTIF.get(), SoundSource.BLOCKS, (getVolume() + 2.0F) / 2.0F, getPitch() - level.random.nextFloat() * 0.4f);
    }

    @Override
    @OnlyIn(value = Dist.CLIENT)
    public void onPlayHitSound(BlockPos pos) {
        MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
        if (gameMode != null) {
            float progress = gameMode.destroyProgress;
            float volume = (getVolume() + progress * progress * 4f) / 8f;
            float pitch = getPitch() * (0.5f + 0.25f * progress);
            Minecraft.getInstance().getSoundManager().play(new SimpleSoundInstance(MalumSoundEvents.CTHONIC_GOLD_ORE_HIT_MOTIF.get(), SoundSource.BLOCKS, volume, pitch, MalumMod.RANDOM, pos));
        }
    }
}