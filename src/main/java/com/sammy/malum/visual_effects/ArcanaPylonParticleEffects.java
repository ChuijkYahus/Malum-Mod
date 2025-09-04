package com.sammy.malum.visual_effects;

import com.sammy.malum.client.*;
import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.*;
import com.sammy.malum.common.block.curiosities.runic_workbench.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.runic_workbench.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.*;

public class ArcanaPylonParticleEffects {

    public static void eatSpiritParticles(Level level, ArcanaPylonBlockEntity arcanaPylon, MalumNetworkedParticleEffectColorData colorData) {
        long gameTime = level.getGameTime();
        var rand = level.random;
        Vec3 targetPos = arcanaPylon.getItemPos();
        for (int i = 0; i < 4; i++) {
            int lifeDelay = 2 * i;
            var scaleData = GenericParticleData.create(0f, RandomHelper.randomBetween(rand, 0.3f, 0.45f) + i * 0.05f)
                    .setEasing(Easing.EXPO_OUT)
                    .setCoefficient(RandomHelper.randomBetween(rand, 1.25f, 1.5f)).build();
            var builder = SpiritBasedParticleBuilder.createSpirit(MalumParticles.CIRCLE.get())
                    .setSpirit(colorData.getSpirit())
                    .setBehavior(DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)))
                    .setTransparencyData(GenericParticleData.create(0.7f, 0f).setEasing(Easing.CIRC_OUT).build())
                    .setScaleData(scaleData)
                    .setLifetime(20)
                    .setLifeDelay(lifeDelay)
                    .enableNoClip();
            if (i % 2 == 0) {
                builder.act(b -> b.setColorData(b.getColorData().invert().build()));
            }
            builder.spawn(level, targetPos.x, targetPos.y, targetPos.z);
        }
    }
}