package com.sammy.malum.client.renderer.entity.cultist;

import com.sammy.malum.client.renderer.entity.bolt.AbstractBoltEntityRenderer;
import com.sammy.malum.common.entity.bolt.HexBolt;
import com.sammy.malum.common.entity.mob.cultist.CultistBoltProjectile;
import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import com.sammy.malum.registry.client.MalumRenderTypeTokens;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.systems.rendering.rendeertype.LodestoneRenderTypeBuilder;
import team.lodestar.lodestone.systems.rendering.rendeertype.ShaderUniformHandler;

public class CultistBoltRenderer extends AbstractBoltEntityRenderer<CultistBoltProjectile> {
    public CultistBoltRenderer(EntityRendererProvider.Context context) {
        super(context, CultistBoltProjectile.CULTIST_RED, CultistBoltProjectile.CULTIST_CRIMSON);
    }

    @Override
    public LodestoneRenderTypeBuilder getTrailRenderType(boolean isTransparent) {
        return LodestoneRenderTypes.TRANSPARENT_TWO_SIDED_TEXTURE_TRIANGLE.apply(MalumRenderTypeTokens.CONCENTRATED_TRAIL).withUniformHandler(ShaderUniformHandler.LUMITRANSPARENT);
    }

    @Override
    public float getScaleMultiplier() {
        return 0.6f;
    }
}
