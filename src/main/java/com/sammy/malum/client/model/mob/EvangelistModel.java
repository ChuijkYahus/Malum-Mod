package com.sammy.malum.client.model.mob;// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.sammy.malum.MalumMod;
import com.sammy.malum.common.entity.cultist.evangelist.EvangelistCultist;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class EvangelistModel extends HumanoidModel<EvangelistCultist> {

	public static ModelLayerLocation LAYER = new ModelLayerLocation(MalumMod.malumPath("evangelist"), "main");

	private final ModelPart tentacles;
	private final ModelPart lower;
	private final ModelPart middle;
	private final ModelPart upper;

	public EvangelistModel(ModelPart root) {
        super(root);
		tentacles = body.getChild("tentacles");
		lower = tentacles.getChild("lower");
		middle = tentacles.getChild("middle");
		upper = tentacles.getChild("upper");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0, 0, 0));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(34, 18).addBox(-3.0F, -9.0F, -3.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.55F))
				.texOffs(0, 35).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(64, 18).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.5F))
				.texOffs(64, 35).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.5F))
				.texOffs(0, 0).addBox(-8.5F, -22.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(34, 0).addBox(-8.5F, -22.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -18.0F, -1.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 73).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(64, 73).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(0, 45).addBox(-7.0F, -14.0F, -3.0F, 14.0F, 13.0F, 5.0F, new CubeDeformation(0.25F))
				.texOffs(64, 45).addBox(-7.0F, -14.0F, -3.0F, 14.0F, 13.0F, 5.0F, new CubeDeformation(0.5F))
				.texOffs(0, 63).addBox(-6.0F, -4.0F, -2.0F, 12.0F, 7.0F, 3.0F, new CubeDeformation(0.5F))
				.texOffs(64, 63).addBox(-6.0F, -4.0F, -2.0F, 12.0F, 7.0F, 3.0F, new CubeDeformation(0.75F))
				.texOffs(38, 47).addBox(-3.0F, -15.0F, -0.5F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(102, 47).addBox(-3.0F, -15.0F, -0.5F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(38, 57).addBox(-3.0F, -9.0F, 2.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition tentacles = body.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition lower = tentacles.addOrReplaceChild("lower", CubeListBuilder.create(), PartPose.offset(0.0F, 32.0F, 0.0F));

		PartDefinition cube_r1 = lower.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 2.7925F, 0.0F, 3.1416F));

		PartDefinition cube_r2 = lower.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r3 = lower.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -2.7925F));

		PartDefinition cube_r4 = lower.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition middle = tentacles.addOrReplaceChild("middle", CubeListBuilder.create(), PartPose.offset(0.0F, 28.0F, 0.0F));

		PartDefinition cube_r5 = middle.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 2.8798F, 0.0F, 3.1416F));

		PartDefinition cube_r6 = middle.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r7 = middle.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -2.8798F));

		PartDefinition cube_r8 = middle.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition upper = tentacles.addOrReplaceChild("upper", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r9 = upper.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.75F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 2.9671F, 0.0F, 3.1416F));

		PartDefinition cube_r10 = upper.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.75F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r11 = upper.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.75F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r12 = upper.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.75F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 65).mirror().addBox(-4.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(51, 40).mirror().addBox(-6.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(33, 34).addBox(-6.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(94, 65).mirror().addBox(-4.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(115, 40).mirror().addBox(-6.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(97, 34).addBox(-6.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -16.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 65).addBox(2.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(33, 34).mirror().addBox(1.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(51, 40).addBox(4.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(115, 40).addBox(4.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(94, 65).addBox(2.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.25F))
				.texOffs(97, 34).mirror().addBox(1.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(5.0F, -16.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(38, 65).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(38, 65).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EvangelistCultist entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float headClamp = 0.08f;
		float armClamp = 0.8F;
		float legClamp = 0.1F;
		head.xRot = Mth.clamp(head.xRot, -headClamp, headClamp);
		head.y = -18.0F;
		head.z = 0.0F;

		body.xRot = 0.0F;
		body.y = -5.0F;
		body.z = -0.0F;

		rightArm.xRot *= 0.5F;
		rightArm.setPos(-5.0F, -17.0F, 0.0F);
		rightArm.xRot = Mth.clamp(rightArm.xRot, -armClamp, armClamp);
		leftArm.xRot *= 0.5F;
		leftArm.setPos(5.0F, -17.0F, 0.0F);
		leftArm.xRot = Mth.clamp(leftArm.xRot, -armClamp, armClamp);

		rightLeg.setPos(-2f, 0, 0);
		rightLeg.xRot *= 0.5F;
		rightLeg.xRot = Mth.clamp(rightLeg.xRot, -legClamp, legClamp);
		leftLeg.setPos(2f, 0, 0);
		leftLeg.xRot *= 0.5F;
		leftLeg.xRot = Mth.clamp(leftLeg.xRot, -legClamp, legClamp);


		float motion = 1.0F;
		boolean flag = entity.getFallFlyingTicks() > 4;
		if (flag) {
			motion = (float)entity.getDeltaMovement().lengthSqr();
			motion /= 0.2F;
			motion *= motion * motion;
		}

		if (motion < 1.0F) {
			motion = 1.0F;
		}

		float lowerRotation = Mth.cos(limbSwing * 0.4F) * 0.4F * limbSwingAmount / motion;
		float middleRotation = Mth.cos(limbSwing * 0.5f) * 0.3F * limbSwingAmount / motion;
		float upperRotation = Mth.cos(limbSwing * 0.6f) * 0.2F * limbSwingAmount / motion;
		float lowerOffset = lowerRotation * 6;
		float middleOffset = middleRotation * 6;
		float upperOffset = upperRotation * 6;
		lower.z = lowerOffset;
		middle.z = middleOffset;
		upper.z = upperOffset;
		lower.xRot = lowerRotation;
		middle.xRot = middleRotation;
		upper.xRot = upperRotation;
	}


}