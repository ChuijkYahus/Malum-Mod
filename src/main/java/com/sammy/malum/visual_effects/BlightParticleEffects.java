package com.sammy.malum.visual_effects;

import com.sammy.malum.common.block.curiosities.repair_pylon.*;
import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.blight.*;
import net.minecraft.client.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.network.particle.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;
import team.lodestar.lodestone.systems.particle.world.options.*;

import javax.annotation.*;
import java.awt.*;
import java.util.function.*;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.*;
import static net.minecraft.util.Mth.nextFloat;

public class BlightParticleEffects {

    public static void blightSpreads(NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, BlightPropagationParticleEffect.BlightPropagationEffectData extraData) {
        var level = Minecraft.getInstance().level;
        var sourcePos = positionData.getAsBlockPos();
        var center = sourcePos.getCenter();
        var rand = level.getRandom();
        for (int i = 0; i < 3; i++) {
            MalumSpiritType cyclingSpiritType = colorData.getSpirit();
            if (rand.nextFloat() < 0.85f) {
                float multiplier = Mth.nextFloat(level.random, 0.4f, 1f);
                Color color = new Color((int) (31 * multiplier), (int) (19 * multiplier), (int) (31 * multiplier));
                float xVelocity = RandomHelper.randomBetween(rand, Easing.CUBIC_OUT, -0.025f, 0.025f);
                float zVelocity = RandomHelper.randomBetween(rand, Easing.CUBIC_OUT, -0.025f, 0.025f);
                float xOffset = RandomHelper.randomBetween(rand, -0.5f, 0.5f);
                float yOffset = RandomHelper.randomBetween(rand, 0.5f, 0.65f);
                float zOffset = RandomHelper.randomBetween(rand, -0.5f, 0.5f);

                Consumer<LodestoneWorldParticle> slowDown = p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.95f));
                Vec3 particlePosition = center.add(xOffset, yOffset, zOffset);
                int distance = extraData.sourcePos().distManhattan(sourcePos);
                int lifetime = 20 + 20 * distance;
                var builder = WorldParticleBuilder.create(LodestoneParticleTypes.WISP_PARTICLE.get())
                        .setScaleData(GenericParticleData.create(0.5f, RandomHelper.randomBetween(rand, 0.8f, 1.2f), 0).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
                        .setSpinData(SpinParticleData.createRandomDirection(rand, nextFloat(rand, 0.05f, 0.1f)).randomSpinOffset(rand).build())
                        .setColorData(ColorParticleData.create(color).build())
                        .setMotion(xVelocity, 0, zVelocity)
                        .addTickActor(slowDown)
                        .setLifetime(lifetime)
                        .setNaturalLighting()
                        .enableNoClip();

                for (int j = 0; j < 4; j++) {
                    var renderType = j / 2 == 0 ? LodestoneWorldParticleRenderType.LUMITRANSPARENT : LodestoneWorldParticleRenderType.ADDITIVE;
                    var behavior = j % 2 == 0 ? DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)) : BillboardParticleBehavior.INSTANCE;
                    float alpha = j / 2 == 0 ? 0.25f : 0.1f;
                    float scale = j % 2 == 0 ? 2f : 0.6f;
                    builder
                            .setScaleData(GenericParticleData.create(scale/2f, scale, 0).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
                            .setTransparencyData(GenericParticleData.create(alpha/2, alpha, 0f).setEasing(Easing.SINE_IN, Easing.SINE_OUT).build())
                            .setRenderType(renderType)
                            .setBehavior(behavior)
                            .spawn(level, particlePosition.x, particlePosition.y, particlePosition.z);
                }
            }
        }
    }
}
