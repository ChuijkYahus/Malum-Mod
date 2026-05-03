// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class wand<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "wand"), "main");
	private final ModelPart cores;
	private final ModelPart short_staff;
	private final ModelPart medium_staff;
	private final ModelPart long_staff;
	private final ModelPart heads;
	private final ModelPart cap_head;
	private final ModelPart tip1;
	private final ModelPart tip2;
	private final ModelPart orb_head;
	private final ModelPart orb_inner;
	private final ModelPart beacon_head;
	private final ModelPart support1;
	private final ModelPart support2;
	private final ModelPart bases;
	private final ModelPart simple_base;
	private final ModelPart baubles;
	private final ModelPart loop_bauble;
	private final ModelPart spike_bauble;
	private final ModelPart ornaments;
	private final ModelPart lower_ornament;
	private final ModelPart middle_ornament;
	private final ModelPart upper_ornament;

	public wand(ModelPart root) {
		this.cores = root.getChild("cores");
		this.short_staff = this.cores.getChild("short_staff");
		this.medium_staff = this.cores.getChild("medium_staff");
		this.long_staff = this.cores.getChild("long_staff");
		this.heads = root.getChild("heads");
		this.cap_head = this.heads.getChild("cap_head");
		this.tip1 = this.cap_head.getChild("tip1");
		this.tip2 = this.cap_head.getChild("tip2");
		this.orb_head = this.heads.getChild("orb_head");
		this.orb_inner = this.orb_head.getChild("orb_inner");
		this.beacon_head = this.heads.getChild("beacon_head");
		this.support1 = this.beacon_head.getChild("support1");
		this.support2 = this.beacon_head.getChild("support2");
		this.bases = root.getChild("bases");
		this.simple_base = this.bases.getChild("simple_base");
		this.baubles = root.getChild("baubles");
		this.loop_bauble = this.baubles.getChild("loop_bauble");
		this.spike_bauble = this.baubles.getChild("spike_bauble");
		this.ornaments = root.getChild("ornaments");
		this.lower_ornament = this.ornaments.getChild("lower_ornament");
		this.middle_ornament = this.ornaments.getChild("middle_ornament");
		this.upper_ornament = this.ornaments.getChild("upper_ornament");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cores = partdefinition.addOrReplaceChild("cores", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition short_staff = cores.addOrReplaceChild("short_staff", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition medium_staff = cores.addOrReplaceChild("medium_staff", CubeListBuilder.create().texOffs(8, 21).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition long_staff = cores.addOrReplaceChild("long_staff", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -19.5F, -1.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition heads = partdefinition.addOrReplaceChild("heads", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cap_head = heads.addOrReplaceChild("cap_head", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));

		PartDefinition tip1 = cap_head.addOrReplaceChild("tip1", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition tip2 = cap_head.addOrReplaceChild("tip2", CubeListBuilder.create().texOffs(6, 40).addBox(-1.5F, -7.5F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition orb_head = heads.addOrReplaceChild("orb_head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition orb_inner = orb_head.addOrReplaceChild("orb_inner", CubeListBuilder.create().texOffs(8, 40).addBox(0.0F, -4.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 48).addBox(3.0F, -5.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition beacon_head = heads.addOrReplaceChild("beacon_head", CubeListBuilder.create().texOffs(42, 40).addBox(-1.5F, -6.5F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));

		PartDefinition support1 = beacon_head.addOrReplaceChild("support1", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -6.5F, 0.0F, 9.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition support2 = beacon_head.addOrReplaceChild("support2", CubeListBuilder.create().texOffs(24, 40).addBox(-4.5F, -6.5F, 0.0F, 9.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition bases = partdefinition.addOrReplaceChild("bases", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition simple_base = bases.addOrReplaceChild("simple_base", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition baubles = partdefinition.addOrReplaceChild("baubles", CubeListBuilder.create(), PartPose.offset(0.5F, 24.0F, 0.5F));

		PartDefinition loop_bauble = baubles.addOrReplaceChild("loop_bauble", CubeListBuilder.create().texOffs(0, -7).addBox(0.0F, -2.0F, -3.5F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition spike_bauble = baubles.addOrReplaceChild("spike_bauble", CubeListBuilder.create().texOffs(14, -7).addBox(0.0F, -2.0F, -3.5F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition ornaments = partdefinition.addOrReplaceChild("ornaments", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition lower_ornament = ornaments.addOrReplaceChild("lower_ornament", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition middle_ornament = ornaments.addOrReplaceChild("middle_ornament", CubeListBuilder.create().texOffs(16, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(16, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition upper_ornament = ornaments.addOrReplaceChild("upper_ornament", CubeListBuilder.create().texOffs(32, 5).addBox(0.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 13).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cores.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		heads.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bases.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		baubles.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ornaments.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}