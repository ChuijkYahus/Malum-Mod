package com.sammy.malum.common.item.spirit;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.core.systems.spirit.SpiritLike;
import com.sammy.malum.visual_effects.ScreenParticleEffects;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler.ItemParticleSupplier;
import team.lodestar.lodestone.modules.rendering.particle.standard.screen.ScreenParticleHolder;

import java.util.List;

public class SpiritShardItem extends Item implements ItemParticleSupplier, SpiritLike {

    protected final SpiritHolder<SpiritArcanaType> spirit;

    public SpiritShardItem(Properties properties, SpiritHolder<SpiritArcanaType> spirit) {
        super(properties);
        this.spirit = spirit;
    }

    @Override
    public @NotNull SpiritArcanaType getSpirit() {
        return spirit.getSpirit();
    }

    public SpiritHolder<SpiritArcanaType> getSpiritHolder() {
        return spirit;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        getSpirit().getTextData().addToItemTooltip(tooltipComponents);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        ScreenParticleEffects.spawnSpiritShardScreenParticles(target, spirit);
    }
}
