package com.sammy.malum.common.item.spirit;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.visual_effects.ScreenParticleEffects;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler.ItemParticleSupplier;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleHolder;

import java.util.List;

public class SpiritShardItem extends Item implements ItemParticleSupplier, SpiritLike {
    protected final SpiritHolder<MalumSpiritType> spirit;
    protected Component flavorText;

    public SpiritShardItem(Properties properties, SpiritHolder<MalumSpiritType> spirit) {
        super(properties);
        this.spirit = spirit;
    }

    @Override
    public @NotNull MalumSpiritType getSpirit() {
        return spirit.getSpirit();
    }

    public SpiritHolder<MalumSpiritType> getSpiritHolder() {
        return spirit;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        SpiritHolder<MalumSpiritType> spirit = getSpiritHolder();
        if (flavorText == null) {
            flavorText = Component.translatable(spirit.getFlavourKey()).withStyle(ChatFormatting.ITALIC).withStyle(spirit.getStyle(true));
        }
        tooltipComponents.add(flavorText);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        ScreenParticleEffects.spawnSpiritShardScreenParticles(target, spirit);
    }
}
