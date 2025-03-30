package com.sammy.malum.visual_effects;

import com.sammy.malum.client.SpiritBasedParticleBuilder;
import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlockEntity;
import com.sammy.malum.common.item.ether.EtherItem;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import com.sammy.malum.registry.client.ParticleRegistry;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.data.ColorEffectData;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.helpers.VecHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleTypes;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntityInventory;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.SimpleParticleOptions;
import team.lodestar.lodestone.systems.particle.builder.AbstractParticleBuilder;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleDataBuilder;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.particle.world.LodestoneWorldParticle;
import team.lodestar.lodestone.systems.particle.world.behaviors.DirectionalParticleBehavior;
import team.lodestar.lodestone.systems.particle.world.behaviors.SparkParticleBehavior;

import java.awt.*;
import java.util.function.Consumer;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;
import static net.minecraft.util.Mth.nextFloat;

public class SoulBindingBrazierParticleEffects {

    public static MalumSpiritType getCentralSpiritType(SoulBrazierBlockEntity brazier) {
        final LodestoneBlockEntityInventory spiritInventory = brazier.spiritInventory;
        int spiritCount = spiritInventory.getFilledSlotCount();
        Item currentItem = spiritInventory.getStackInSlot(0).getItem();
        if (spiritCount > 1) {
            float duration = 30f * spiritCount;
            float gameTime = (brazier.getLevel().getGameTime() % duration) / 30f;
            currentItem = spiritInventory.getStackInSlot(Mth.floor(gameTime)).getItem();
        }
        if (!(currentItem instanceof SpiritShardItem spiritItem)) {
            return null;
        }
        return spiritItem.type;
    }

    public static ColorParticleDataBuilder getParticleColor(SoulBrazierBlockEntity brazier) {
        Color start;
        Color end;
        if (brazier.state.equals(SoulBrazierBlockEntity.BrazierState.BINDING)) {
            start = new Color(EtherItem.DEFAULT_FIRST_COLOR.rgb());
            end = new Color(EtherItem.DEFAULT_SECOND_COLOR.rgb());
        } else {
            start = new Color(215, 20, 85);
            end = new Color(18, 120, 199);
        }
        return ColorParticleData.create(start, end).setEasing(Easing.SINE_IN_OUT);
    }

    public static void beginSoulBindingParticles(SoulBrazierBlockEntity brazier, ColorEffectData colorData) {
        var level = brazier.getLevel();
        var random = level.random;
        BlockPos blockPos = brazier.getBlockPos();
        float x = blockPos.getX() + 0.5f;
        float y = blockPos.getY() + 1.1f;
        float z = blockPos.getZ() + 0.5f;
        Vec3 pos = new Vec3(x, y, z);
        long gameTime = level.getGameTime();
        var color = getParticleColor(brazier);
        for (int i = 0; i < 4; i++) {
            int lifeTime = RandomHelper.randomBetween(random, 20, 25);
            float spin = RandomHelper.randomBetween(random, 0.01f, 0.02f);
            float scale = 5f;
            WorldParticleBuilder.create(ParticleRegistry.RADIAL_DISPLAY)
                    .setTransparencyData(GenericParticleData.create(0.1f, 0.6f, 0f).setEasing(Easing.SINE_OUT, Easing.CUBIC_OUT).build())
                    .setScaleData(GenericParticleData.create(scale * 0.2f, scale * RandomHelper.randomBetween(random, 0.9f, 1.1f)).build())
                    .setColorData(color.setCoefficient(0.7f).build())
                    .setSpinData(SpinParticleData.create(spin).build())
                    .setBehavior(DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)))
                    .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                    .setLifetime(lifeTime)
                    .enableNoClip()
                    .spawn(level, x, y - 0.4f, z)
                    .setTransparencyData(GenericParticleData.create(0.1f, 0.6f, 0f).setEasing(Easing.CUBIC_OUT, Easing.CUBIC_OUT).build())
                    .setScaleData(GenericParticleData.create(scale * 0.2f, scale * RandomHelper.randomBetween(random, 0.9f, 1.1f)).build())
                    .setColorData(color.setCoefficient(1.2f).build())
                    .setMotion(0, -0.04f, 0)
                    .spawn(level, x, y + 0.4f, z);
        }
        for (int i = 0; i < 16; i++) {
            Vec3 rotatingPos = VecHelper.rotatingRadialOffset(pos, 0.5f, i, 16, gameTime, 80);
            int lifeTime = RandomHelper.randomBetween(random, 30, 40);
            float scale = RandomHelper.randomBetween(random, 0.3f, 0.4f);
            float length = RandomHelper.randomBetween(random, 1.8f, 2.2f);
            WorldParticleBuilder.create(LodestoneParticleTypes.EXTRUDING_SPARK_PARTICLE)
                    .setTransparencyData(GenericParticleData.create(0.1f, 0.4f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                    .setLengthData(GenericParticleData.create(length, 0).setEasing(Easing.SINE_IN_OUT).build())
                    .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
                    .setColorData(colorData.getColor())
                    .setMotion(0, 0.01f, 0)
                    .setBehavior(SparkParticleBehavior.sparkBehavior().setLengthCenter(1f))
                    .setLifetime(lifeTime)
                    .setRandomOffset(0.1f)
                    .spawn(level, rotatingPos.x, rotatingPos.y, rotatingPos.z);
        }
        for (int i = 0; i < 3; i++) {
            float scaleMultiplier = (float) (1 + Math.pow(random.nextFloat(), 2) * 0.5f);
            WorldParticleBuilder.create(ParticleRegistry.GIANT_GLOWING_STAR)
                    .setScaleData(GenericParticleData.create(4f * scaleMultiplier, 0.5f, 0).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                    .setTransparencyData(GenericParticleData.create(0.9f, 0.07f, 0).setEasing(Easing.SINE_IN, Easing.CIRC_IN).build())
                    .setSpinData(SpinParticleData.createRandomDirection(random, nextFloat(random, 0.05f, 0.1f)).randomSpinOffset(random).build())
                    .setRandomMotion(0.01f, 0.01f)
                    .setColorData(colorData.getColor())
                    .setRandomOffset(0.2f)
                    .setLifetime(25)
                    .enableNoClip()
                    .spawn(level, x, y, z)
                    .setBehavior(DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)))
                    .spawn(level, x, y, z);
        }
        float geasY = (float) (blockPos.getY() + SoulBrazierBlockEntity.BRAZIER_GEAS_ICON_OFFSET.y);
        for (int i = 0; i < 5; i++) {
            float scaleMultiplier = (float) (1 + Math.pow(random.nextFloat(), 2) * 0.5f);
            WorldParticleBuilder.create(ParticleRegistry.GIANT_GLOWING_STAR)
                    .setScaleData(GenericParticleData.create(0.5f, 4f * scaleMultiplier, 2f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                    .setTransparencyData(GenericParticleData.create(0.1f, 0.3f, 0).setEasing(Easing.SINE_IN, Easing.SINE_IN_OUT).build())
                    .setSpinData(SpinParticleData.createRandomDirection(random, nextFloat(random, 0.05f, 0.1f)).build())
                    .setRandomMotion(0.01f, 0.01f)
                    .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                    .setColorData(colorData.getColor())
                    .setRandomOffset(0.2f)
                    .setLifetime(25)
                    .enableNoClip()
                    .setBehavior(DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)))
                    .spawn(level, x, geasY, z);
        }
    }

    public static void finishSoulBindingParticles(SoulBrazierBlockEntity brazier, ColorEffectData colorData) {

    }

    public static void passiveBrazierParticles(SoulBrazierBlockEntity brazier) {
        MalumSpiritType activeSpiritType = getCentralSpiritType(brazier);
        if (activeSpiritType == null) {
            return;
        }
        var level = brazier.getLevel();
        var random = level.random;
        var itemPos = brazier.getItemPos();
        var spiritInventory = brazier.spiritInventory;
        int spiritsRendered = 0;
        BlockPos blockPos = brazier.getBlockPos();
        for (int i = 0; i < spiritInventory.slotCount; i++) {
            ItemStack item = spiritInventory.getStackInSlot(i);
            if (item.getItem() instanceof SpiritShardItem shard) {
                var spirit = shard.type;
                var offset = brazier.getSpiritOffset(spiritsRendered++, 0);
                var spiritPosition = offset.add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                spiritLightSpecs(level, spiritPosition, spirit).spawnParticles();
                if (brazier.isActive()) {
                    Vec3 velocity = itemPos.subtract(spiritPosition).normalize().scale(RandomHelper.randomBetween(random, 0.04f, 0.08f));
                    if (random.nextFloat() < 0.9f) {
                        var sparkParticles = SparkParticleEffects.spiritMotionSparks(level, spiritPosition, spirit);
                        sparkParticles.getBuilder()
                                .setMotion(velocity)
                                .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.4f));
                        sparkParticles.getBloomBuilder().setMotion(velocity);
                        sparkParticles.spawnParticles();
                    }
                    if (random.nextFloat() < 0.6f) {
                        var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, spiritPosition, spirit);
                        lightSpecs.getBuilder()
                                .multiplyLifetime(0.8f)
                                .setMotion(velocity.scale(1.5f))
                                .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.2f));
                        lightSpecs.getBloomBuilder().setMotion(velocity);
                        lightSpecs.spawnParticles();
                    }
                }
            }
        }
        if (brazier.isActive()) {
            if (!brazier.sacrificedTargets.isEmpty()) {
                var bloodPos = itemPos.add(0, 0.4f, 0);
                var color = ColorParticleData.create(0.6f, 0.1f, 0.1f).build();
                SpiritLightSpecs.rotatingLightSpecs(level, bloodPos, color, 0.6f, brazier.sacrificedTargets.size(),
                        b -> b.setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT).multiplyLifetime(1.5f));
                SpiritLightSpecs.rotatingLightSpecs(level, bloodPos, color, 0.6f, brazier.sacrificedTargets.size());
            }


            float x = blockPos.getX() + 0.5f;
            float y = blockPos.getY() + 1.1f;
            float z = blockPos.getZ() + 0.5f;
            Vec3 pos = new Vec3(x, y, z);
            long gameTime = level.getGameTime();
            Vec3 rotatingPos = VecHelper.rotatingRadialOffset(pos, 0.4f, 0, 1, gameTime, 80);
            //Upwards Moving Particles
            if (gameTime % 2L == 0) {
                var color = getParticleColor(brazier).setCoefficient(1.5f).build();
                int lifeTime = RandomHelper.randomBetween(random, 60, 80);
                float scale = RandomHelper.randomBetween(random, 1.2f, 1.4f);
                float velocity = RandomHelper.randomBetween(random, 0.03f, 0.05f);
                var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, pos, color);
                lightSpecs.getBuilder()
                        .setTransparencyData(GenericParticleData.create(0.05f, 0.2f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .addMotion(0, velocity * 1.2f, 0).setLifetime(lifeTime);
                lightSpecs.spawnParticlesRaw();
            }
            //Upwards Moving Sparks
            if (gameTime % 2L == 0) {
                var color = getParticleColor(brazier).setCoefficient(2.5f).build();
                int lifeTime = RandomHelper.randomBetween(random, 100, 120);
                float scale = RandomHelper.randomBetween(random, 0.3f, 0.4f);
                float velocity = RandomHelper.randomBetween(random, 0.06f, 0.07f);
                var lightSpecs = SparkParticleEffects.spiritMotionSparks(level, rotatingPos, color);
                lightSpecs.getBuilder()
                        .setTransparencyData(GenericParticleData.create(0.1f, 0.6f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .addMotion(0, velocity * 1.4f, 0)
                        .setBehavior(DirectionalParticleBehavior.directional(pos.subtract(rotatingPos).normalize()))
                        .setLifetime(lifeTime)
                        .setRandomOffset(0.1f);
                lightSpecs.spawnParticlesRaw();
            }
            //Big Shine
            final Vec3 up = new Vec3(0, 1, 0);
            if (gameTime % 12L == 0) {
                var color = getParticleColor(brazier).setCoefficient(0.6f).build();
                int lifeTime = RandomHelper.randomBetween(random, 50, 60);
                float scale = RandomHelper.randomBetween(random, 1.9f, 2.2f);
                WorldParticleBuilder.create(ParticleRegistry.GIANT_GLOWING_STAR)
                        .setTransparencyData(GenericParticleData.create(0f, 0.8f, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(scale*1.5f, 0).setEasing(Easing.SINE_IN).build())
                        .setBehavior(DirectionalParticleBehavior.directional(up))
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .setMotion(0, 0.001f, 0)
                        .setLifetime(lifeTime)
                        .setColorData(color)
                        .enableNoClip()
                        .spawn(level, x, y, z)
                        .setLengthData(GenericParticleData.create(scale*2, 0).setEasing(Easing.SINE_IN).build())
                        .setBehavior(SparkParticleBehavior.sparkBehavior())
                        .spawn(level, x, y, z);
            }

            //Radial Display
            if (brazier.progress % 15L == 0) {
                var color = getParticleColor(brazier);
                int lifeTime = RandomHelper.randomBetween(random, 80, 100);
                float spin = RandomHelper.randomBetween(random, 0.005f, 0.01f);
                float scale = 4.5f;
                WorldParticleBuilder.create(ParticleRegistry.RADIAL_DISPLAY)
                        .setTransparencyData(GenericParticleData.create(0f, 0.5f, 0f).setEasing(Easing.CUBIC_OUT, Easing.CUBIC_OUT).build())
                        .setScaleData(GenericParticleData.create(scale, scale * RandomHelper.randomBetween(random, 0.95f, 1.05f)).build())
                        .setBehavior(DirectionalParticleBehavior.directional(up))
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .setSpinData(SpinParticleData.create(spin).build())
                        .setColorData(color.setCoefficient(0.7f).build())
                        .setLifetime(lifeTime)
                        .enableNoClip()
                        .spawn(level, x, y-0.4f, z)
                        //Small Upper Ring
                        .setScaleData(GenericParticleData.create(1.2f).build())
                        .setLifetime(50)
                        .spawn(level, x, y, z)
                        //Shrinking Central Ring
                        .setTransparencyData(GenericParticleData.create(0f, 0.5f, 0f).setCoefficient(0.8f).setEasing(Easing.CUBIC_OUT, Easing.CUBIC_OUT).build())
                        .setScaleData(GenericParticleData.create(scale, scale * RandomHelper.randomBetween(random, 0.4f, 0.5f)).build())
                        .setColorData(color.setCoefficient(1.5f).build())
                        .setMotion(0, 0.02f, 0)
                        .setLifetime(40)
                        .spawn(level, x, y-0.4f, z);
            }

            //Rotating Stars
            if (gameTime % 4 == 0) {
                float distance = 2.15f;
                int amount = brazier.spiritInventory.getFilledSlotCount() * 4;
                ColorEffectData colorEffectData = ColorEffectData.fromSpiritIngredients(brazier.recipe.spirits);
                Vec3 geasIconPos = SoulBrazierBlockEntity.BRAZIER_GEAS_ICON_OFFSET.add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                for (int i = 0; i < amount; i++) {
                    var spiritSparkPos = VecHelper.rotatingRadialOffset(pos, distance, i, amount, level.getGameTime(), 3000);
                    var spiritType = colorEffectData.getSpirit();
                    int lifeTime = RandomHelper.randomBetween(random, 80, 100);
                    float scale = RandomHelper.randomBetween(random, 0.2f, 0.3f) * Math.min((brazier.progress + 10) / 40f, 1);
                    var direction = geasIconPos.subtract(spiritSparkPos).normalize();
                    SpiritBasedParticleBuilder.createSpirit(ParticleRegistry.STAR)
                            .setSpirit(spiritType)
                            .setTransparencyData(GenericParticleData.create(0.1f, 0.3f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                            .setScaleData(GenericParticleData.create(scale, scale*0.2f).setEasing(Easing.SINE_IN_OUT).build())
                            .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                            .setRandomOffset(0.1f)
                            .setLifetime(lifeTime)
                            .enableNoClip()
                            .spawn(level, spiritSparkPos.x, spiritSparkPos.y, spiritSparkPos.z)
                            .setBehavior(DirectionalParticleBehavior.directional(direction))
                            .spawn(level, spiritSparkPos.x, spiritSparkPos.y, spiritSparkPos.z);

                    //Pointy Beams
                    SpiritBasedParticleBuilder.createSpirit(LodestoneParticleTypes.EXTRUDING_SPARK_PARTICLE)
                            .setSpirit(spiritType)
                            .setTransparencyData(GenericParticleData.create(0f, 0.3f, 0).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
                            .setLengthData(GenericParticleData.create(scale*8f, scale*0.2f).setEasing(Easing.SINE_IN_OUT).build())
                            .setScaleData(GenericParticleData.create(scale*1.4f, scale*0.2f).setEasing(Easing.SINE_IN_OUT).build())
                            .setBehavior(SparkParticleBehavior.sparkBehavior().setForcedDirection(direction).setLengthCenter(1f))
                            .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                            .setRandomOffset(0.1f)
                            .setLifetime(40)
                            .enableNoClip()
                            .spawn(level, spiritSparkPos.x, spiritSparkPos.y, spiritSparkPos.z);
                }
            }

            //Geas Shine
            if (gameTime % 20L == 0) {
                Vec3 geasIconPos = SoulBrazierBlockEntity.BRAZIER_GEAS_ICON_OFFSET.add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                var color = activeSpiritType.createColorData().setCoefficient(0.6f).build();
                int lifeTime = RandomHelper.randomBetween(random, 50, 60);
                float scale = RandomHelper.randomBetween(random, 2.4f, 2.8f);
                WorldParticleBuilder.create(ParticleRegistry.GIANT_GLOWING_STAR)
                        .setTransparencyData(GenericParticleData.create(0f, 0.4f, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN).build())
                        .setBehavior(DirectionalParticleBehavior.directional(up))
                        .setLifetime(lifeTime)
                        .setColorData(color)
                        .enableNoClip()
                        .spawn(level, geasIconPos.x, geasIconPos.y, geasIconPos.z)
                        .setLengthData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN).build())
                        .setBehavior(SparkParticleBehavior.sparkBehavior().setForcedDirection(up))
                        .spawn(level, geasIconPos.x, geasIconPos.y, geasIconPos.z);
            }

            //Geas Rotating Stars
            if (gameTime % 4 == 0) {
                float distance = 1.2f;
                int amount = brazier.spiritInventory.getFilledSlotCount() * 2;
                ColorEffectData colorEffectData = ColorEffectData.fromSpiritIngredients(brazier.recipe.spirits);
                Vec3 geasIconPos = SoulBrazierBlockEntity.BRAZIER_GEAS_ICON_OFFSET.add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                for (int i = 0; i < amount; i++) {
                    var spiritSparkPos = VecHelper.rotatingRadialOffset(geasIconPos, distance, i, amount, level.getGameTime(), 600);
                    var spiritType = colorEffectData.getSpirit();
                    int lifeTime = RandomHelper.randomBetween(random, 60, 80);
                    float scale = RandomHelper.randomBetween(random, 0.3f, 0.4f) * Math.min((brazier.progress + 10) / 40f, 1);
                    var direction = geasIconPos.subtract(spiritSparkPos).normalize();
                    SpiritBasedParticleBuilder.createSpirit(ParticleRegistry.LIGHT_SPEC_SMALL)
                            .setSpirit(spiritType)
                            .setTransparencyData(GenericParticleData.create(0.1f, 0.5f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                            .setScaleData(GenericParticleData.create(scale, scale*0.2f).setEasing(Easing.SINE_IN_OUT).build())
                            .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                            .setRandomOffset(0.1f)
                            .setLifetime(lifeTime)
                            .enableNoClip()
                            .spawn(level, spiritSparkPos.x, spiritSparkPos.y, spiritSparkPos.z)
                            .setBehavior(DirectionalParticleBehavior.directional(direction))
                            .spawn(level, spiritSparkPos.x, spiritSparkPos.y, spiritSparkPos.z);
                }
            }
        }
    }
}
