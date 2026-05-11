package com.sammy.malum.visual_effects.block;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.client.MalumModels;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.rendering.handlers.ParticleHandler;
import team.lodestar.lodestone.modules.rendering.particle.builder.ParticleBuilder;
import team.lodestar.lodestone.modules.rendering.particle.builder.ParticleSpec;
import team.lodestar.lodestone.modules.rendering.particle.runtime.ParticleSpawnContext;
import team.lodestar.lodestone.modules.rendering.particle.runtime.ParticleSpawnContextChain;
import team.lodestar.lodestone.modules.rendering.particle.runtime.profile.cube.CuboidDistributionProfile;
import team.lodestar.lodestone.modules.rendering.particle.visual.instance.InstanceFormat;
import team.lodestar.lodestone.modules.rendering.particle.visual.instance.StandardInstanceWriters;
import team.lodestar.lodestone.registry.client.LodestoneParticleVisuals;
import team.lodestar.lodestone.registry.client.LodestoneShaders;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.StateShards;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.*;
import static com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS;
import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;
import static net.minecraft.client.renderer.RenderStateShard.LIGHTMAP;
import static net.minecraft.client.renderer.RenderStateShard.NO_CULL;
import static team.lodestar.lodestone.registry.client.LodestoneRenderTypes.createGenericRenderType;

public class MagehandCofferParticleEffects {

    public static final LodestoneRenderType MAGEHAND = createGenericRenderType(
            RenderTypeToken.createToken(MalumMod.malumPath("textures/particle/magehand/magehand.png")), "magehand", POSITION_COLOR_TEX_LIGHTMAP, QUADS,
            b -> b.setStateShards(StateShards.ADDITIVE_TRANSPARENCY, LodestoneShaders.LODESTONE_TEXTURE, NO_CULL, LIGHTMAP)
    );

    private static final InstanceFormat COLOR_MODEL_MATRIX = InstanceFormat.create()
            .add(StandardInstanceWriters.COLOR, StandardInstanceWriters.MODEL_MATRIX)
            .build();

    private static final ParticleSpec SPEC = ParticleBuilder.create()
            .withVisual(LodestoneParticleVisuals.MESH, v -> v
                    .instancedModel(MalumModels.MAGEHAND, MAGEHAND).instanceFormat(COLOR_MODEL_MATRIX)
            )

            .build();

    public static void magehandParticle(Level level, Vec3 position) {
        var profile = CuboidDistributionProfile.centeredOn(position, 0).weighed(Easing.QUINTIC_IN);
        var chain = new ParticleSpawnContextChain(profile);
        var ctx = new ParticleSpawnContext().lifetime(2);
        ParticleHandler.spawn(SPEC, chain.apply(ctx));
    }
}