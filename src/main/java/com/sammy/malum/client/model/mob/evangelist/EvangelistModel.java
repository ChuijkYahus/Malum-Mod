package com.sammy.malum.client.model.mob.evangelist;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.model.mob.CultistHumanoidModel;
import com.sammy.malum.client.model.mob.MalumAnimationUtils;
import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistCultist;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import team.lodestar.lodestone.systems.easing.Easing;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class EvangelistModel extends CultistHumanoidModel<EvangelistCultist> {

	public static ModelLayerLocation LAYER = new ModelLayerLocation(MalumMod.malumPath("evangelist"), "main");

	private final ModelPart tentacles;
	private final ModelPart lower;
	private final ModelPart middle;
	private final ModelPart upper;

	public EvangelistModel(ModelPart modelDefinition) {
		super(modelDefinition);
		tentacles = body.getChild("tentacles");
		lower = tentacles.getChild("lower");
		middle = tentacles.getChild("middle");
		upper = tentacles.getChild("upper");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(34, 18).addBox(-3.0F, -9.0F, -3.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.55F))
				.texOffs(0, 35).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(64, 18).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.5F))
				.texOffs(64, 35).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -18.0F, -1.0F));

		PartDefinition crown = head.addOrReplaceChild("crown", CubeListBuilder.create().texOffs(34, 0).addBox(-8.5F, -64.0F, -1.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.5F))
				.texOffs(0, 0).addBox(-8.5F, -64.0F, -1.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 42.0F, 1.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 73).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(64, 73).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(0, 45).addBox(-7.0F, -14.0F, -3.0F, 14.0F, 13.0F, 5.0F, new CubeDeformation(0.25F))
				.texOffs(64, 45).addBox(-7.0F, -14.0F, -3.0F, 14.0F, 13.0F, 5.0F, new CubeDeformation(0.5F))
				.texOffs(0, 63).addBox(-6.0F, -4.0F, -2.0F, 12.0F, 7.0F, 3.0F, new CubeDeformation(0.5F))
				.texOffs(64, 63).addBox(-6.0F, -4.0F, -2.0F, 12.0F, 7.0F, 3.0F, new CubeDeformation(0.75F))
				.texOffs(38, 47).addBox(-3.0F, -15.0F, -0.5F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(102, 47).addBox(-3.0F, -15.0F, -0.5F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(38, 57).addBox(-3.0F, -9.0F, 2.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition tentacles = body.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition lower = tentacles.addOrReplaceChild("lower", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition cube_r1 = lower.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 2.7925F, 0.0F, 3.1416F));

		PartDefinition cube_r2 = lower.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r3 = lower.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -2.7925F));

		PartDefinition cube_r4 = lower.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition middle = tentacles.addOrReplaceChild("middle", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition cube_r5 = middle.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 2.8798F, 0.0F, 3.1416F));

		PartDefinition cube_r6 = middle.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r7 = middle.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -2.8798F));

		PartDefinition cube_r8 = middle.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition upper = tentacles.addOrReplaceChild("upper", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition cube_r9 = upper.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.75F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 2.9671F, 0.0F, 3.1416F));

		PartDefinition cube_r10 = upper.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(64, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.75F))
				.texOffs(0, 93).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 14.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r11 = upper.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.75F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r12 = upper.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(84, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.75F))
				.texOffs(20, 93).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 14.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 65).mirror().addBox(-4.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(51, 40).mirror().addBox(-6.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(33, 34).addBox(-6.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(94, 65).mirror().addBox(-4.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(115, 40).mirror().addBox(-6.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(97, 34).addBox(-6.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -16.0F, 0.0F));

		PartDefinition swordbone = right_arm.addOrReplaceChild("swordbone", CubeListBuilder.create().texOffs(0, -9).mirror().addBox(0.0F, -5.0F, -33.0F, 0.0F, 10.0F, 34.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 22.0F, 1.0F));

		PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 65).addBox(2.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(33, 34).mirror().addBox(1.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(51, 40).addBox(4.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(115, 40).addBox(4.0F, 7.0F, -1.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(94, 65).addBox(2.0F, 0.0F, 0.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.25F))
				.texOffs(97, 34).mirror().addBox(1.0F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(5.0F, -16.0F, 0.0F));

		PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(38, 65).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(38, 65).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EvangelistCultist evangelist, MalumAnimationUtils<EvangelistCultist> utils, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float headYClamp = 0.7f;
		float headXClamp = 0.08f;
		float armClamp = 1.4F;
		float legClamp = 0.2F;

		float headYRot = netHeadYaw * Mth.DEG_TO_RAD;
		headYRot = Mth.clamp(headYRot, -headYClamp, headYClamp);
		float headXRot = headPitch * Mth.DEG_TO_RAD;
		headXRot = Mth.clamp(headXRot, -headXClamp, headXClamp);

		float rightArmRotation = utils.getRightArmRotation(d -> d
				.setRate(0.3F).setAmount(0.7F).setEasing(Easing.SINE_IN).addClamp(armClamp));
		float leftArmRotation = utils.getLeftArmRotation(d -> d
				.setRate(0.3F).setAmount(0.7F).setEasing(Easing.SINE_IN).addClamp(armClamp));

		float rightLegRotation = utils.getRightLegRotation(d -> d
				.setRate(0.8F).setAmount(0.6f).setEasing(Easing.SINE_OUT).addClamp(legClamp));
		float leftLegRotation = utils.getLeftLegRotation(d -> d
				.setRate(0.8F).setAmount(0.6f).setEasing(Easing.SINE_OUT).addClamp(legClamp));

		head.yRot = headYRot;
		head.xRot = headXRot;

		rightArm.xRot = rightArmRotation;
		leftArm.xRot = leftArmRotation;

		rightLeg.xRot = rightLegRotation;
		rightLeg.visible = false;
		leftLeg.xRot = leftLegRotation;
		leftLeg.visible = false;
	}
}