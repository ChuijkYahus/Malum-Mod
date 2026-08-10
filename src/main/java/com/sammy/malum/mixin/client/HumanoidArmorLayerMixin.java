package com.sammy.malum.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.sammy.malum.client.model.armor.MalumArmorModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin {

    @Shadow @Final private TextureAtlas armorTrimAtlas;

    @ModifyVariable(method = "renderTrim(Lnet/minecraft/core/Holder;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/item/armortrim/ArmorTrim;Lnet/minecraft/client/model/Model;Z)V", at = @At(value = "HEAD"), index = 3, argsOnly = true)
    private MultiBufferSource malum$updateTrimRendering(MultiBufferSource buffer, @Local(argsOnly = true) Holder<ArmorMaterial> material, @Local(argsOnly = true) ArmorTrim armorTrim, @Local(argsOnly = true) Model model, @Local(argsOnly = true) boolean isInner) {
        if (model instanceof MalumArmorModel malumArmorModel) {

            var sprite = this.armorTrimAtlas.getSprite(isInner ? armorTrim.innerTexture(material) : armorTrim.outerTexture(material));
            return malumArmorModel.updateTrimRendering(buffer, sprite, isInner);
        }
        return buffer;
    }
}
