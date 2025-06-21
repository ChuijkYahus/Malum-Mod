package com.sammy.malum.common.item.curiosities.curios.runes;

import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.handlers.screenparticle.*;
import team.lodestar.lodestone.systems.particle.screen.*;

public abstract class AbstractRuneCurioItem extends MalumCurioItem implements ParticleEmitterHandler.ItemParticleSupplier {

    public final SpiritHolder<MalumSpiritType> spirit;

    public AbstractRuneCurioItem(Properties builder, SpiritHolder<MalumSpiritType> spirit, MalumTrinketType type) {
        super(builder, type);
        this.spirit = spirit;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        ScreenParticleEffects.spawnRuneParticles(target, spirit);
    }
}