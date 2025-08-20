package com.sammy.malum.common.item.codex;

import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.handlers.screenparticle.*;
import team.lodestar.lodestone.systems.particle.screen.*;

public class EncyclopediaEsotericaItem extends EncyclopediaArcanaItem implements ParticleEmitterHandler.ItemParticleSupplier {

    public EncyclopediaEsotericaItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.swing(hand);
        if (level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            ProgressionScreenHolder.getAppropriateCodexScreen().openCodexViaItem(true);
            return InteractionResultHolder.success(stack);
        }
        return super.use(level, player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        ScreenParticleEffects.spawnEncyclopediaEsotericaScreenParticles(target, level, partialTick);
    }
}