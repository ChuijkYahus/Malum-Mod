package com.sammy.malum.client.model.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.registry.client.MalumModels;
import net.minecraft.Util;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("FieldCanBeLocal")
public class WandPartsModel extends Model {


	public static MalumModels.ModelHolder<WandPartsModel> MODEL = new MalumModels.ModelHolder<>("wand_parts", WandPartsModel::new, WandPartsModel::createWandParts);

	private final ModelPart cores;
	private final ModelPart heads;
	private final ModelPart bases;
	private final ModelPart baubles;
	private final ModelPart ornaments;

	/**
	 * Maps a part type to it's model part.
	 * Mixin here to add custom part handling.
	 * @return The model part to render
	 */
	public static Optional<ModelPart> getModelPart(WandPartType partType) {
		if (partType.isMalum()) {
			WandPartsModel wandPartsModel = MODEL.getModel();
			var group = switch (partType.group()) {
				case CORE -> wandPartsModel.cores;
				case HEAD -> wandPartsModel.heads;
				case BASE -> wandPartsModel.bases;
				case BAUBLE -> wandPartsModel.baubles;
				case ORNAMENT -> wandPartsModel.ornaments;
			};
			var name = getModelPartName(partType);
			return Optional.of(getPart(group, name));
		}
		return Optional.empty();
	}


	/**.
	 * @return The name of the model part that should be used for a wand
	 */
	protected static String getModelPartName(WandPartType partType) {
		return partType.id().getPath() + "_" + partType.group().name;
	}

	protected static ModelPart getPart(ModelPart part, String name) {
		try {
			return part.getChild(name);
		} catch (Exception ignored) {
			return new ModelPart(Collections.emptyList(), Collections.emptyMap());
		}
	}

	public WandPartsModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		cores = root.getChild("cores");
		heads = root.getChild("heads");
		bases = root.getChild("bases");
		baubles = root.getChild("baubles");
		ornaments = root.getChild("ornaments");
	}

	public static LayerDefinition createWandParts() {
		var meshdefinition = new MeshDefinition();
		var partdefinition = meshdefinition.getRoot();

		var cores = partdefinition.addOrReplaceChild("cores", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		cores.addOrReplaceChild("short_staff", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, -0.7854F, 0.0F));
		cores.addOrReplaceChild("medium_staff", CubeListBuilder.create().texOffs(8, 21).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		cores.addOrReplaceChild("long_staff", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -19.5F, -1.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		var heads = partdefinition.addOrReplaceChild("heads", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cap_head = heads.addOrReplaceChild("cap_head", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));

		cap_head.addOrReplaceChild("tip1", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		cap_head.addOrReplaceChild("tip2", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition orb_head = heads.addOrReplaceChild("orb_head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition prongs = orb_head.addOrReplaceChild("prongs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		prongs.addOrReplaceChild("prong1", CubeListBuilder.create().texOffs(16, 44).addBox(0.001F, -4.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.6569F, -4.0F));

		prongs.addOrReplaceChild("prong2", CubeListBuilder.create().texOffs(16, 44).addBox(0.001F, -4.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.6569F, -4.0F, 1.5708F, -0.7854F, -2.3562F));

		prongs.addOrReplaceChild("prong3", CubeListBuilder.create().texOffs(16, 44).addBox(0.001F, -4.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.6569F, -4.0F, 1.5708F, 0.7854F, 2.3562F));

		orb_head.addOrReplaceChild("orb_inner", CubeListBuilder.create().texOffs(8, 40).addBox(0.0F, -4.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(8, 48).addBox(3.0F, -5.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition beacon_head = heads.addOrReplaceChild("beacon_head", CubeListBuilder.create().texOffs(42, 40).addBox(-1.5F, -6.5F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));

		beacon_head.addOrReplaceChild("support1", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -10.5F, 0.0F, 9.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		beacon_head.addOrReplaceChild("support2", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -10.5F, 0.0F, 9.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		var bases = partdefinition.addOrReplaceChild("bases", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		bases.addOrReplaceChild("simple_base", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

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