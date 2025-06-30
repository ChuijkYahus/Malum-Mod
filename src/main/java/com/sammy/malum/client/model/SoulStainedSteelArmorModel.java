package com.sammy.malum.client.model;
// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.sammy.malum.MalumMod;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import team.lodestar.lodestone.systems.model.LodestoneArmorModel;

public class SoulStainedSteelArmorModel extends LodestoneArmorModel {
    public static ModelLayerLocation LAYER = new ModelLayerLocation(MalumMod.malumPath("soul_stained_steel_armor"), "main");

    public SoulStainedSteelArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return createArmorModel((mesh, root, body, leggings, right_legging, left_legging, right_foot, left_foot, right_arm, left_arm, head) -> {
            PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(16, 15).addBox(-5.5F, -9.5F, -4.5F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 15).mirror().addBox(1.5F, -9.5F, -4.5F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(16, 0).addBox(-1.5F, -10.5F, -5.5F, 3.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
                    .texOffs(80, 0).addBox(-1.5F, -10.5F, -5.5F, 3.0F, 6.0F, 9.0F, new CubeDeformation(0.3F))
                    .texOffs(80, 15).mirror().addBox(1.5F, -9.5F, -4.5F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.3F)).mirror(false)
                    .texOffs(80, 15).addBox(-5.5F, -9.5F, -4.5F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_guard = helmet.addOrReplaceChild("left_guard", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.5F, -3.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(64, 0).mirror().addBox(-1.0F, -1.5F, -3.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(3.0F, -5.0F, -1.0F, 0.0F, 0.0F, -0.3491F));

            PartDefinition right_guard = helmet.addOrReplaceChild("right_guard", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5F, -3.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 0).addBox(-2.0F, -0.5F, -3.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-3.0F, -6.0F, -1.0F, 0.0F, 0.0F, 0.3491F));

            PartDefinition head_wings = helmet.addOrReplaceChild("head_wings", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

            PartDefinition left_head_wing = head_wings.addOrReplaceChild("left_head_wing", CubeListBuilder.create().texOffs(30, 16).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 12).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(31, 1).addBox(-1.0F, -4.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(95, 1).addBox(-1.0F, -4.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.3F))
                    .texOffs(64, 12).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.31F))
                    .texOffs(94, 16).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(4.0F, 0.0F, 0.0F));

            PartDefinition right_head_wing = head_wings.addOrReplaceChild("right_head_wing", CubeListBuilder.create().texOffs(30, 16).mirror().addBox(-1.0F, 2.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(0, 12).mirror().addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(31, 1).mirror().addBox(-1.0F, -4.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(95, 1).mirror().addBox(-1.0F, -4.0F, 5.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false)
                    .texOffs(64, 12).mirror().addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.31F)).mirror(false)
                    .texOffs(94, 16).mirror().addBox(-1.0F, 2.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(-4.0F, 0.0F, 0.0F));

            PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 25).addBox(-5.0F, 1.0F, -3.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(32, 25).addBox(-4.5F, 3.5F, -2.5F, 9.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(42, 38).mirror().addBox(-5.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(42, 38).addBox(2.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(42, 46).addBox(2.0F, -1.0F, 3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(42, 46).mirror().addBox(-5.0F, -1.0F, 3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(106, 46).mirror().addBox(-5.0F, -1.0F, 3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.3F)).mirror(false)
                    .texOffs(106, 46).addBox(2.0F, -1.0F, 3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.3F))
                    .texOffs(106, 38).addBox(2.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.3F))
                    .texOffs(106, 38).mirror().addBox(-5.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.3F)).mirror(false)
                    .texOffs(64, 25).addBox(-5.0F, 1.0F, -3.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_shoulder = left_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(0, 38).addBox(0.0F, -4.0F, -3.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.01F))
                    .texOffs(64, 38).addBox(0.0F, -4.0F, -3.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.31F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition right_shoulder = right_arm.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(0, 38).mirror().addBox(-5.0F, -4.0F, -3.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false)
                    .texOffs(64, 38).mirror().addBox(-5.0F, -4.0F, -3.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.31F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_leg = left_legging.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 61).mirror().addBox(-2.4F, -0.5F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_thigh_guard = left_leg.addOrReplaceChild("left_thigh_guard", CubeListBuilder.create().texOffs(22, 38).mirror().addBox(-1.796F, -2.3318F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(86, 38).mirror().addBox(-1.796F, -2.3318F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(2.1F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

            PartDefinition right_leg = right_legging.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 61).addBox(-2.6F, -0.5F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition right_thigh_guard = right_leg.addOrReplaceChild("right_thigh_guard", CubeListBuilder.create().texOffs(22, 38).addBox(-2.204F, -2.3323F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(86, 38).addBox(-2.204F, -2.3323F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-2.1F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

            PartDefinition codpiece = leggings.addOrReplaceChild("codpiece", CubeListBuilder.create().texOffs(0, 53).addBox(-5.0F, 9.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.01F))
                    .texOffs(26, 53).addBox(-2.0F, 11.5F, -3.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.01F))
                    .texOffs(38, 54).addBox(-2.0F, 11.5F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
                    .texOffs(102, 54).addBox(-2.0F, 11.5F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.26F))
                    .texOffs(90, 53).addBox(-2.0F, 11.5F, -3.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.31F))
                    .texOffs(64, 53).addBox(-5.0F, 9.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.31F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_boot = left_foot.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(0, 73).addBox(-2.9F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.01F))
                    .texOffs(64, 73).addBox(-2.9F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.31F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_boot_wing = left_boot.addOrReplaceChild("left_boot_wing", CubeListBuilder.create().texOffs(24, 75).addBox(3.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(88, 75).addBox(3.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.1F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

            PartDefinition right_boot = right_foot.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(0, 73).mirror().addBox(-3.1F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(64, 73).mirror().addBox(-3.1F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition right_boot_wing = right_boot.addOrReplaceChild("right_boot_wing", CubeListBuilder.create().texOffs(24, 75).mirror().addBox(-4.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                    .texOffs(88, 75).mirror().addBox(-4.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-0.1F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
            return LayerDefinition.create(mesh, 128, 128);
        });
    }
}