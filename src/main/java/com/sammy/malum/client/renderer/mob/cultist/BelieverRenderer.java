package com.sammy.malum.client.renderer.mob.cultist;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.layer.EvangelistItemInHandLayer;
import com.sammy.malum.client.model.mob.BelieverModel;
import com.sammy.malum.client.model.mob.CardinalModel;
import com.sammy.malum.common.entity.cultist.believer.BelieverCultist;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BelieverRenderer extends MobRenderer<BelieverCultist, BelieverModel> {
    private static final ResourceLocation BELIEVER_LOCATION = MalumMod.malumPath("textures/entity/cultist/believer.png");

    public BelieverRenderer(EntityRendererProvider.Context context) {
        super(context, new BelieverModel(context.bakeLayer(BelieverModel.LAYER)), 0.3F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    protected void scale(BelieverCultist cardinal, PoseStack poseStack, float partialTicks) {
        float scale = cardinal.getCultistScaleMultiplier();
        poseStack.scale(scale, scale, scale);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BelieverCultist cardinal) {
        return BELIEVER_LOCATION;
    }
}