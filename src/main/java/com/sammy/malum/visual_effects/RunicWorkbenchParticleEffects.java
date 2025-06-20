package com.sammy.malum.visual_effects;

import com.sammy.malum.client.*;
import com.sammy.malum.common.block.curiosities.obelisk.runewood.*;
import com.sammy.malum.common.block.curiosities.runic_workbench.*;
import com.sammy.malum.common.block.curiosities.spirit_altar.*;
import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.runic_workbench.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;
import team.lodestar.lodestone.systems.particle.world.options.*;

import java.util.function.*;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.*;

public class RunicWorkbenchParticleEffects {

    public static void craftRuneParticles(Level level, RunicWorkbenchBlockEntity workbench, MalumNetworkedParticleEffectColorData colorData, RunicWorkbenchEffectData extraData) {
        long gameTime = level.getGameTime();
        var rand = level.random;
        Vec3 targetPos = workbench.getItemPos();
        for (int i = 0; i < 2; i++) {
            var spirit = colorData.getSpirit();
            SpiritLightSpecs.coolLookingShinyThing(level, targetPos, spirit);
        }
        for (int i = 0; i < 6; i++) {
            var scaleData = GenericParticleData.create(0.1f, RandomHelper.randomBetween(rand, 0.5f, 0.6f) + i * 0.05f, 0.5f)
                    .setEasing(Easing.SINE_OUT, Easing.SINE_IN)
                    .setCoefficient(RandomHelper.randomBetween(rand, 1f, 1.25f)).build();
            var builder = SpiritBasedParticleBuilder.createSpirit(MalumParticles.SQUARE.get())
                    .setSpirit(colorData.getSpirit())
                    .setBehavior(DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)))
                    .setTransparencyData(GenericParticleData.create(0.7f, 0f).setEasing(Easing.SINE_IN_OUT).build())
                    .setSpritePicker(SimpleParticleOptions.ParticleSpritePicker.WITH_AGE)
                    .setScaleData(scaleData)
                    .setLifetime(25)
                    .setLifeDelay(i*2)
                    .enableNoClip();
            if (i % 2 == 0) {
                builder.act(b -> b.setColorData(b.getColorData().invert().build()));
            }
            builder
                    .spawn(level, targetPos.x, targetPos.y, targetPos.z)
                    .setBehavior(BillboardParticleBehavior.INSTANCE)
                    .setLifetime(15)
                    .setLifeDelay(i*3)
                    .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.25f).multiplyCoefficient(0.9f))
                    .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyValue(0.6f))
                    .spawn(level, targetPos.x, targetPos.y, targetPos.z);
        }

        for (int i = 0; i < 32; i++) {
            var spirit = colorData.getSpirit();
            Vec3 offsetPosition = VecHelper.rotatingRadialOffset(targetPos, 0.6f, i, 16, gameTime, 160);
            var lightSpecs = spiritLightSpecs(level, offsetPosition, spirit);
            if (i % 2 == 0) {
                lightSpecs.getBuilder().act(b -> b.setColorData(b.getColorData().invert().build()));
                lightSpecs.getBloomBuilder().act(b -> b.setColorData(b.getColorData().invert().build()));
            }

            lightSpecs.getBuilder()
                    .setMotion(0, 0.1f, 0)
                    .modifyColorData(d -> d.multiplyCoefficient(0.35f))
                    .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(2f).multiplyCoefficient(0.9f))
                    .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyCoefficient(0.9f))
                    .setLifetime(10)
                    .setLifeDelay(4+i/2);
            lightSpecs.getBloomBuilder()
                    .setMotion(0, 0.1f, 0)
                    .modifyColorData(d -> d.multiplyCoefficient(0.35f))
                    .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.6f).multiplyCoefficient(0.9f))
                    .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyCoefficient(0.9f))
                    .setLifetime(10)
                    .setLifeDelay(4+i/2);
            lightSpecs.spawnParticles();
        }
    }

    public static void craftItemParticles(Level level, RunicWorkbenchBlockEntity workbench, MalumNetworkedParticleEffectColorData colorData, RunicWorkbenchEffectData extraData) {
        long gameTime = level.getGameTime();
        Vec3 targetPos = workbench.getItemPos();

        for (int i = 0; i < 2; i++) {
            var spirit = colorData.getSpirit();
            SpiritLightSpecs.coolLookingShinyThing(level, targetPos, spirit);
        }
        for (int i = 0; i < 64; i++) {
            var spirit = colorData.getSpirit();
            Vec3 offsetPosition = VecHelper.rotatingRadialOffset(targetPos, 0.6f, i, 8, gameTime, 160);
            var lightSpecs = spiritLightSpecs(level, offsetPosition, spirit);
            if (i % 2 == 0) {
                lightSpecs.getBuilder().act(b -> b.setColorData(b.getColorData().invert().build()));
                lightSpecs.getBloomBuilder().act(b -> b.setColorData(b.getColorData().invert().build()));
            }

            lightSpecs.getBuilder()
                    .modifyColorData(d -> d.multiplyCoefficient(0.35f))
                    .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(2f).multiplyCoefficient(0.9f))
                    .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyCoefficient(0.9f))
                    .multiplyLifetime(2)
                    .setLifeDelay(i*2);
            lightSpecs.getBloomBuilder()
                    .modifyColorData(d -> d.multiplyCoefficient(0.35f))
                    .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.6f).multiplyCoefficient(0.9f))
                    .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyCoefficient(0.9f))
                    .multiplyLifetime(2);
            lightSpecs.spawnParticles();
        }
    }
}