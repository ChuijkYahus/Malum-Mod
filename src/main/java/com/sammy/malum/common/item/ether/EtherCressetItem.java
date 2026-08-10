package com.sammy.malum.common.item.ether;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleTypes;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.rendering.particle.standard.builder.ScreenParticleBuilder;
import team.lodestar.lodestone.modules.rendering.particle.standard.data.GenericParticleData;
import team.lodestar.lodestone.modules.rendering.particle.standard.data.color.ColorParticleData;
import team.lodestar.lodestone.modules.rendering.particle.standard.data.spin.SpinParticleData;
import team.lodestar.lodestone.modules.rendering.particle.standard.data.spin.SpinParticleDataBuilder;
import team.lodestar.lodestone.modules.rendering.particle.standard.screen.ScreenParticleHolder;

import java.awt.*;

public class EtherCressetItem extends EtherItem {

    public static EtherCressetItem ether(Block block, Properties properties) {
        return new EtherCressetItem(block, properties, false);
    }

    public static EtherCressetItem iridescent(Block block, Properties properties) {
        return new EtherCressetItem(block, properties, true);
    }

    public EtherCressetItem(Block blockIn, Properties builder, boolean iridescent) {
        super(blockIn, builder, iridescent);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        var firstColor = new Color(EtherItem.getPrimaryColor(stack));
        var secondColor = new Color(EtherItem.getSecondaryColor(stack));
        float alphaMultiplier = isIridescent ? 0.75f : 0.5f;
        float time = level.getGameTime() + partialTick;
        final SpinParticleDataBuilder spinDataBuilder = SpinParticleData.create(0, 1).setSpinOffset(0.025f * time % 6.28f).setEasing(Easing.EXPO_IN_OUT);
        ScreenParticleBuilder.create(LodestoneScreenParticleTypes.STAR, target)
                .setTransparencyData(GenericParticleData.create(0.11f * alphaMultiplier, 0f).setEasing(Easing.QUINTIC_IN).build())
                .setScaleData(GenericParticleData.create((float) (0.75f + Math.sin(time * 0.05f) * 0.125f), 0).build())
                .setColorData(ColorParticleData.create(firstColor, secondColor).setCoefficient(1.25f).build())
                .setSpinData(spinDataBuilder.build())
                .setLifetime(7)
                .setRandomOffset(0.05f)
                .spawnOnStack(0, -4)
                .setScaleData(GenericParticleData.create((float) (0.75f - Math.sin(time * 0.075f) * 0.125f), 0).build())
                .setColorData(ColorParticleData.create(secondColor, firstColor).build())
                .setSpinData(spinDataBuilder.setSpinOffset(0.785f - 0.01f * time % 6.28f).build())
                .spawnOnStack(0, -4);
    }
}