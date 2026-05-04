package com.sammy.malum.client.model.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

@SuppressWarnings("FieldCanBeLocal")
public class WandPartsModel extends Model {


	private final ModelPart cores;
	private final ModelPart heads;
	private final ModelPart bases;
	private final ModelPart baubles;
	private final ModelPart ornaments;


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

		cores.addOrReplaceChild("short_core", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		cores.addOrReplaceChild("medium_core", CubeListBuilder.create().texOffs(8, 21).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		cores.addOrReplaceChild("long_core", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -19.5F, -1.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		var heads = partdefinition.addOrReplaceChild("heads", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		var cap_head = heads.addOrReplaceChild("cap_head", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));
		cap_head.addOrReplaceChild("tip1", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		cap_head.addOrReplaceChild("tip2", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		var orb_head = heads.addOrReplaceChild("orb_head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
		var prongs = orb_head.addOrReplaceChild("prongs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		prongs.addOrReplaceChild("prong1", CubeListBuilder.create().texOffs(16, 44).addBox(0.001F, -3.9999F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.6569F, -4.0F));
		prongs.addOrReplaceChild("prong2", CubeListBuilder.create().texOffs(16, 44).addBox(0.001F, -3.9999F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.6569F, -4.0F, 1.5708F, -0.7854F, -2.3562F));
		prongs.addOrReplaceChild("prong3", CubeListBuilder.create().texOffs(16, 44).addBox(0.001F, -3.9999F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.6569F, -4.0F, 1.5708F, 0.7854F, 2.3562F));
		orb_head.addOrReplaceChild("orb_inner", CubeListBuilder.create().texOffs(8, 40).addBox(0.0F, -4.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(8, 48).addBox(3.0F, -5.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		var beacon_head = heads.addOrReplaceChild("beacon_head", CubeListBuilder.create().texOffs(42, 40).addBox(-1.5F, -6.5F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));
		beacon_head.addOrReplaceChild("support1", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -10.5F, 0.0F, 9.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		beacon_head.addOrReplaceChild("support2", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -10.5F, 0.0F, 9.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		var bases = partdefinition.addOrReplaceChild("bases", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        bases.addOrReplaceChild("simple_base", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        var grid_loop_base = bases.addOrReplaceChild("grid_loop_base", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        grid_loop_base.addOrReplaceChild("grid_loop", CubeListBuilder.create().texOffs(0, -7).addBox(0.0F, -2.0F, -3.5F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        var spiked_base = bases.addOrReplaceChild("spiked_base", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        spiked_base.addOrReplaceChild("spike", CubeListBuilder.create().texOffs(14, -7).addBox(0.0F, -2.0F, -3.5F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        var carved_base = bases.addOrReplaceChild("carved_base", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        carved_base.addOrReplaceChild("carving", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        var baubles = partdefinition.addOrReplaceChild("baubles", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		baubles.addOrReplaceChild("carved_bauble", CubeListBuilder.create().texOffs(16, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        var ornaments = partdefinition.addOrReplaceChild("ornaments", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        ornaments.addOrReplaceChild("carved_ornament", CubeListBuilder.create().texOffs(32, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {

	}
}