package com.sammy.malum.client.model.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import java.util.Optional;

@SuppressWarnings("FieldCanBeLocal")
public class WandParts extends Model {
	public static ModelLayerLocation LAYER = new ModelLayerLocation(MalumMod.malumPath("wand_parts"), "main");

	private final ModelPart cores;
	private final ModelPart heads;
	private final ModelPart bases;
	private final ModelPart baubles;
	private final ModelPart ornaments;


	private final ModelPart shortStaff;
	private final ModelPart mediumStaff;
	private final ModelPart longStaff;
	private final ModelPart capHead;
	private final ModelPart orbHead;
	private final ModelPart beaconHead;
	private final ModelPart simpleBase;
	private final ModelPart spikeBauble;
	private final ModelPart loopBauble;
	private final ModelPart lowerOrnament;
	private final ModelPart middleOrnament;
	private final ModelPart upperOrnament;

	/**
	 * Mixin here when adding model handling for custom parts
	 */
	public static Optional<ModelPart> getModelPart(WandPartType partType) {
		if (partType.id().getNamespace().equals(MalumMod.MALUM)) {
			switch (partType.group()) {
				case CORE -> cores
				case HEAD ->
			}
		}
		return Optional.empty();
	}

	public WandParts(ModelPart root) {
		super();

		cores = root.getChild("cores");
		shortStaff = cores.getChild("short_staff");
		mediumStaff = cores.getChild("medium_staff");
		longStaff = cores.getChild("long_staff");

		heads = root.getChild("heads");
		capHead = heads.getChild("cap_head");
		orbHead = heads.getChild("orb_head");
		beaconHead = heads.getChild("beacon_head");

		bases = root.getChild("bases");
		simpleBase = bases.getChild("simple_base");

		baubles = root.getChild("baubles");
		loopBauble = baubles.getChild("loop_bauble");
		spikeBauble = baubles.getChild("spike_bauble");

		ornaments = root.getChild("ornaments");
		lowerOrnament = ornaments.getChild("lower_ornament");
		middleOrnament = ornaments.getChild("middle_ornament");
		upperOrnament = ornaments.getChild("upper_ornament");
	}

	public static LayerDefinition createWandParts() {
		var meshdefinition = new MeshDefinition();
		var partdefinition = meshdefinition.getRoot();

		var cores = partdefinition.addOrReplaceChild("cores", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		cores.addOrReplaceChild("short_staff", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, -0.7854F, 0.0F));
		cores.addOrReplaceChild("medium_staff", CubeListBuilder.create().texOffs(8, 21).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		cores.addOrReplaceChild("long_staff", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -19.5F, -1.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		var heads = partdefinition.addOrReplaceChild("heads", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		var cap_head = heads.addOrReplaceChild("cap_head", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));
		cap_head.addOrReplaceChild("tip1", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		cap_head.addOrReplaceChild("tip2", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		var orb_head = heads.addOrReplaceChild("orb_head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
		orb_head.addOrReplaceChild("orb_inner", CubeListBuilder.create().texOffs(8, 40).addBox(0.0F, -4.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(8, 48).addBox(3.0F, -5.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		var beacon_head = heads.addOrReplaceChild("beacon_head", CubeListBuilder.create().texOffs(42, 40).addBox(-1.5F, -6.5F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));
		beacon_head.addOrReplaceChild("support1", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -6.5F, 0.0F, 9.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		beacon_head.addOrReplaceChild("support2", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -6.5F, 0.0F, 9.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		var bases = partdefinition.addOrReplaceChild("bases", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		bases.addOrReplaceChild("simple_base", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		var baubles = partdefinition.addOrReplaceChild("baubles", CubeListBuilder.create(), PartPose.offset(0.5F, 24.0F, 0.5F));
		baubles.addOrReplaceChild("loop_bauble", CubeListBuilder.create().texOffs(0, -7).addBox(0.0F, -2.0F, -3.5F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.5F, 0.0F, 0.7854F, 0.0F));
		baubles.addOrReplaceChild("spike_bauble", CubeListBuilder.create().texOffs(14, -7).addBox(0.0F, -2.0F, -3.5F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		var ornaments = partdefinition.addOrReplaceChild("ornaments", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		ornaments.addOrReplaceChild("lower_ornament", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		ornaments.addOrReplaceChild("middle_ornament", CubeListBuilder.create().texOffs(16, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(16, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		ornaments.addOrReplaceChild("upper_ornament", CubeListBuilder.create().texOffs(32, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {

	}
}