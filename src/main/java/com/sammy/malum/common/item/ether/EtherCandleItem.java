package com.sammy.malum.common.item.ether;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleTypes;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleHolder;

import java.awt.*;

public class EtherCandleItem extends EtherItem {
    public EtherCandleItem(Block blockIn, Properties builder, boolean iridescent) {
        super(blockIn, builder, iridescent);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        var firstColor = new Color(EtherItem.getPrimaryColor(stack));
        var secondColor = new Color(EtherItem.getSecondaryColor(stack));
        float alphaMultiplier = isIridescent ? 0.75f : 0.5f;
        float time = level.getGameTime() + partialTick;
        var spinDataBuilder = SpinParticleData.create(0, 1).setSpinOffset(0.025f * time % 6.28f).setEasing(Easing.EXPO_IN_OUT);
        ScreenParticleBuilder.create(LodestoneScreenParticleTypes.STAR, target)
                .setTransparencyData(GenericParticleData.create(0.15f * alphaMultiplier, 0f).setEasing(Easing.QUINTIC_IN))
                .setScaleData(GenericParticleData.create((float) (0.6f + Math.sin(time * 0.1f) * 0.125f), 0))
                .setColorData(ColorParticleData.create(firstColor, secondColor).setCoefficient(1.25f))
                .setLifetime(6)
                .setRandomOffset(0.05f)
                .setSpinData(spinDataBuilder.build())
                .spawnOnStack(-0.5f, -5)
                .setScaleData(GenericParticleData.create((float) (0.4f - Math.sin(time * 0.1f) * 0.125f), 0))
                .setColorData(ColorParticleData.create(secondColor, firstColor))
                .setSpinData(spinDataBuilder.setSpinOffset(0.785f - 0.01f * time % 6.28f))
                .spawnOnStack(-0.5f, -5);
    }
}