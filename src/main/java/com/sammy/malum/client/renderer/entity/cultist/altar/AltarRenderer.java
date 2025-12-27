package com.sammy.malum.client.renderer.entity.cultist.altar;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.model.mob.altar.AltarModel;
import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.easing.Easing;

@OnlyIn(Dist.CLIENT)
public class AltarRenderer extends MobRenderer<AltarCultist, AltarModel> {
    private static final ResourceLocation ALTAR_LOCATION = MalumMod.malumPath("textures/entity/cultist/altar.png");

    public AltarRenderer(EntityRendererProvider.Context context) {
        super(context, new AltarModel(context.bakeLayer(AltarModel.LAYER)), 0.4F);
    }

    @Override
    protected void scale(AltarCultist altar, PoseStack poseStack, float partialTicks) {
        if (altar.isSquished()) {
            float squishDelta = Mth.lerp(partialTicks, altar.oSquish, altar.squish) / AltarCultist.SQUISH_ANIMATION_DURATION;
            float squishIn = 0.2f;
            float inverse = 1 - squishIn;
            float strength = 0.6f;
            float stretch;
            if (squishDelta < squishIn) {
                float relative = squishDelta / squishIn;
                var lerp = Easing.BACK_OUT.ease(relative, 0, 1);
                stretch = Mth.lerp(lerp, 1, strength);
            } else {
                float relative = (squishDelta - squishIn) / inverse;
                var lerp = Easing.BOUNCE_OUT.ease(relative, 0, 1);
                stretch = Mth.lerp(lerp, strength, 1);
            }
            poseStack.scale(stretch, 1/stretch, stretch);
        }
        float scale = altar.getCultistScaleMultiplier();
        poseStack.scale(scale, scale, scale);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AltarCultist altar) {
        return ALTAR_LOCATION;
    }
}