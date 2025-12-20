package com.sammy.malum.client.model.mob;// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.sammy.malum.MalumMod;
import com.sammy.malum.common.entity.cultist.cardinal.CardinalCultist;
import com.sammy.malum.common.entity.cultist.evangelist.EvangelistCultist;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class CardinalModel extends HumanoidModel<CardinalCultist> {

	public static ModelLayerLocation LAYER = new ModelLayerLocation(MalumMod.malumPath("cardinal"), "main");

	public CardinalModel(ModelPart root) {
        super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0, 0, 0));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -5.0F, -3.5F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(64, 14).addBox(-4.0F, -5.0F, -3.5F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.5F))
				.texOffs(38, 42).addBox(-4.0F, 6.0F, -3.5F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.5F))
				.texOffs(0, 34).addBox(-10.0F, -7.0F, -3.5F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 34).mirror().addBox(2.0F, -7.0F, -3.5F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(64, 34).addBox(-10.0F, -7.0F, -3.5F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.5F))
				.texOffs(64, 34).mirror().addBox(2.0F, -7.0F, -3.5F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false)
				.texOffs(0, 0).addBox(-11.5F, -15.0F, 0.5F, 23.0F, 14.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(64, 0).addBox(-11.5F, -15.0F, 0.5F, 23.0F, 14.0F, 0.0F, new CubeDeformation(0.5F))
				.texOffs(30, 32).addBox(-6.0F, 2.0F, -3.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(30, 32).mirror().addBox(4.0F, 2.0F, -3.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -18.0F, -4.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 46).addBox(-11.0F, -14.0F, -6.0F, 22.0F, 14.0F, 11.0F, new CubeDeformation(0.05F))
				.texOffs(74, 46).addBox(-11.0F, -14.0F, -6.0F, 22.0F, 14.0F, 11.0F, new CubeDeformation(0.55F))
				.texOffs(55, 60).addBox(7.0F, -18.0F, -6.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.05F))
				.texOffs(55, 60).mirror().addBox(-11.0F, -18.0F, -6.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.05F)).mirror(false)
				.texOffs(129, 60).addBox(7.0F, -18.0F, -6.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.55F))
				.texOffs(129, 60).mirror().addBox(-11.0F, -18.0F, -6.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.55F)).mirror(false)
				.texOffs(0, 71).addBox(-11.0F, 0.0F, -6.0F, 22.0F, 5.0F, 11.0F, new CubeDeformation(0.05F))
				.texOffs(74, 71).addBox(-11.0F, 0.0F, -6.0F, 22.0F, 5.0F, 11.0F, new CubeDeformation(0.55F))
				.texOffs(0, 87).addBox(-9.0F, -8.0F, -5.0F, 18.0F, 19.0F, 9.0F, new CubeDeformation(0.05F))
				.texOffs(84, 101).addBox(-9.0F, -8.0F, -5.0F, 18.0F, 19.0F, 9.0F, new CubeDeformation(0.55F))
				.texOffs(0, 116).addBox(-8.0F, 5.0F, -4.0F, 16.0F, 11.0F, 7.0F, new CubeDeformation(0.05F))
				.texOffs(54, 87).addBox(-11.0F, -14.0F, 5.0F, 22.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(102, 87).addBox(-11.0F, -14.0F, 5.0F, 22.0F, 12.0F, 2.0F, new CubeDeformation(0.5F))
				.texOffs(54, 101).addBox(-4.5F, -6.0F, -3.0F, 9.0F, 13.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 134).mirror().addBox(-5.0F, -7.5F, -2.0F, 4.0F, 28.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(16, 134).mirror().addBox(-6.0F, -9.0F, -2.5F, 5.0F, 31.0F, 5.0F, new CubeDeformation(0.5F)).mirror(false)
				.texOffs(36, 133).mirror().addBox(-6.5F, 14.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(-10.0F, -10.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 170).addBox(1.0F, -7.5F, -4.0F, 6.0F, 23.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(28, 170).addBox(1.0F, -9.0F, -4.5F, 7.0F, 26.0F, 9.0F, new CubeDeformation(0.5F))
				.texOffs(60, 170).addBox(0.5F, 13.0F, -5.0F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.5F)), PartPose.offset(10.0F, -10.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 201).mirror().addBox(-2.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(64, 201).mirror().addBox(-2.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(-5.0F, 11.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 201).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(64, 201).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offset(5.0F, 11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(CardinalCultist entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float headClamp = 0.08f;
		float armClamp = 1.4F;
		float legClamp = 0.2F;
		head.xRot = Mth.clamp(head.xRot, -headClamp, headClamp);
		head.y = -17.0F;
		head.z = -3.5F;


		body.xRot = 0.0F;
		body.y = 2.0F;
		body.z = -0.0F;

		rightArm.xRot *= 0.7F;
		rightArm.setPos(-10.0F, -10.0F, 0.0F);
		rightArm.xRot = Mth.clamp(rightArm.xRot, -armClamp, armClamp);
		leftArm.xRot *= 0.7F;
		leftArm.setPos(10.0F, -10.0F, 0.0F);
		leftArm.xRot = Mth.clamp(leftArm.xRot, -armClamp, armClamp);

		rightLeg.xRot *= 0.7F;
		rightLeg.xRot = Mth.clamp(rightLeg.xRot, -legClamp, legClamp);
		leftLeg.xRot *= 0.7F;
		leftLeg.xRot = Mth.clamp(leftLeg.xRot, -legClamp, legClamp);

	}


}