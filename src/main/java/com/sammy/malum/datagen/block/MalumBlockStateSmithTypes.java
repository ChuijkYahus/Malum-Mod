package com.sammy.malum.datagen.block;

import com.sammy.malum.*;
import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.blight.scarstone.*;
import com.sammy.malum.common.block.curiosities.banner.*;
import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlock;
import com.sammy.malum.common.block.curiosities.repair_pylon.*;
import com.sammy.malum.common.block.curiosities.totem.TotemPoleBlock;
import com.sammy.malum.common.block.curiosities.totem.anchor.*;
import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.*;
import com.sammy.malum.common.block.ether.EtherBrazierBlock;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import com.sammy.malum.datagen.item.MalumItemModelSmithTypes;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import team.lodestar.lodestone.systems.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.systems.datagen.statesmith.BlockStateSmith;

import java.util.function.Function;

import static com.sammy.malum.MalumMod.malumPath;

public class MalumBlockStateSmithTypes {

    //TODO: Move this goober to lodestone
    public static BlockStateSmith<FlowerPotBlock> POTTED_PLANT = new BlockStateSmith<>(FlowerPotBlock.class, MalumItemModelSmithTypes.NO_DATAGEN, (block, provider) -> {
        String name = provider.getBlockName(block);
        ResourceLocation texture = provider.getBlockTexture(name.replace("potted_", ""));
        provider.simpleBlock(block, provider.models().withExistingParent(name, ResourceLocation.withDefaultNamespace("block/flower_pot_cross")).texture("plant", texture));
    });

    //TODO: Move this goober to lodestone
    public static BlockStateSmith<BlightedCoverageBlock> COVERING_BLOCK = new BlockStateSmith<>(BlightedCoverageBlock.class, ItemModelSmithTypes.BLOCK_TEXTURE_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ModelFile model = provider.models().withExistingParent(name, MalumMod.malumPath("block/templates/template_covering"))
                .texture("covering", provider.getBlockTexture(name));
        MultiPartBlockStateBuilder multipartBuilder = provider.getMultipartBuilder(block);
        for (Direction direction : Direction.values()) {
            BooleanProperty property = (BooleanProperty) block.defaultBlockState().getProperties().stream().filter(p -> p.getName().equals(direction.getName())).findFirst().orElseThrow();
            int yRotation = ((int) direction.toYRot() + 180) % 360;
            int xRotation = 0;
            if (direction.getAxis().isVertical()) {
                xRotation = direction.equals(Direction.UP) ? 270 : 90;
            }
            multipartBuilder.part().modelFile(model).rotationY(yRotation).rotationX(xRotation).addModel()
                    .condition(property, true).end();

            //handles the situation where the block is all alone, not connected to anything
            final MultiPartBlockStateBuilder.PartBuilder partBuilder = multipartBuilder.part().modelFile(model).rotationY(yRotation).rotationX(xRotation).addModel();
            for (Direction again : Direction.values()) {
                property = (BooleanProperty) block.defaultBlockState().getProperties().stream().filter(p -> p.getName().equals(again.getName())).findFirst().orElseThrow();
                partBuilder.condition(property, false);
            }
            partBuilder.end();
        }
    });

    public static BlockStateSmith<TotemPoleBlock> TOTEM_POLE = new BlockStateSmith<>(TotemPoleBlock.class, ItemModelSmithTypes.NO_DATAGEN, (block, provider) -> {
        String name = provider.getBlockName(block);
        String woodName = name.substring(0, 8);
        ResourceLocation parent = malumPath("block/templates/template_totem_pole");
        ResourceLocation side = provider.getBlockTexture(woodName + "_log");
        ResourceLocation top = provider.getBlockTexture(woodName + "_log_top");
        provider.getVariantBuilder(block).forAllStates(s -> {
            String type = s.getValue(TotemPoleBlock.SPIRIT_TYPE);
            ResourceLocation front = MalumMod.malumPath("block/totem_poles/" + type + "_" + woodName + "_cutout");
            ModelFile pole = provider.models().withExistingParent(name + "_" + type, parent)
                    .texture("side", side)
                    .texture("top", top)
                    .texture("front", front);
            return ConfiguredModel.builder().modelFile(pole).rotationY(((int) s.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build();
        });
    });

    public static BlockStateSmith<SoulwovenBannerBlock> SOULWOVEN_BANNER = new BlockStateSmith<>(SoulwovenBannerBlock.class, MalumItemModelSmithTypes.SOULWOVEN_BANNER, (block, provider) -> {
        ResourceLocation hanging = malumPath("block/soulwoven_banner");
        ResourceLocation mounted = malumPath("block/soulwoven_banner_directional");
        provider.getVariantBuilder(block).forAllStates(s -> {
            var value = s.getValue(SoulwovenBannerBlock.BANNER_TYPE);
            boolean isVertical = value.direction.getAxis().isVertical();
            Direction direction = isVertical ? (value.equals(SoulwovenBannerBlock.BannerType.HANGING_Z) ? Direction.NORTH : Direction.WEST) : value.direction;
            ResourceLocation model = isVertical ? hanging : mounted;
            return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(model)).rotationY(((int) direction.toYRot()) % 360).build();
        });
    });

    public static BlockStateSmith<Block> RITE_ANCHOR_BLOCK = new BlockStateSmith<>(Block.class, MalumItemModelSmithTypes.BLOCK_MODEL_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ResourceLocation top = provider.getBlockTexture("rite_anchor_top");
        ResourceLocation side = provider.getBlockTexture("rite_anchor_side");
        ResourceLocation bottom = provider.getBlockTexture("rite_anchor_bottom");
        ModelFile model = provider.models().cubeBottomTop(name, side, bottom, top);
        provider.getVariantBuilder(block)
                .forAllStates(state -> {
                    ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
                    if (state.hasProperty(RiteAnchorBlock.SPIRIT_TYPE)) {
                        SpiritHolder<SpiritArcanaType> spiritType = SpiritTypeProperty.getSpiritType(state);
                        ResourceLocation spiritTop = top.withSuffix("_" + spiritType.getName());
                        BlockModelBuilder spiritModel = provider.models().cubeBottomTop(name + "_" + spiritType.getName(), side, bottom, top).texture("particle", spiritTop);
                        builder.modelFile(spiritModel);
                    }
                    else {
                        builder.modelFile(model);
                    }
                    return builder.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build();
                });
    });

    public static BlockStateSmith<SpiritDiodeBlock> SPIRIT_DIODE = new BlockStateSmith<>(SpiritDiodeBlock.class, ItemModelSmithTypes.BLOCK_MODEL_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ResourceLocation top = provider.getBlockTexture("runewood_frame_top");
        ResourceLocation opened = provider.getBlockTexture("runewood_frame_top_open");
        ResourceLocation bottom = provider.getBlockTexture("runewood_frame_bottom");
        ResourceLocation locked = provider.getBlockTexture("runewood_frame_locked");
        ResourceLocation input = provider.getBlockTexture("runewood_frame_input");
        ResourceLocation output = provider.getBlockTexture(name + "_output");
        BlockModelBuilder model = provider.models().cube(name, bottom, top, output, input, locked, locked).texture("particle", output);
        BlockModelBuilder openModel = provider.models().cube(name + "_open", bottom, opened, output, input, locked, locked).texture("particle", output);
        provider.getVariantBuilder(block).forAllStates(s -> {
            var direction = s.getValue(SpiritDiodeBlock.FACING);
            return ConfiguredModel.builder().modelFile(s.getValue(SpiritDiodeBlock.OPEN) ? openModel : model).rotationY(((int) direction.toYRot()) % 360).build();
        });
    });

    public static BlockStateSmith<RepairPylonComponentBlock> REPAIR_PYLON_COMPONENT = new BlockStateSmith<>(RepairPylonComponentBlock.class, ItemModelSmithTypes.NO_DATAGEN, (block, provider) -> {
        ModelFile model = provider.models().getExistingFile(malumPath("block/repair_pylon_component_middle"));
        ModelFile topModel = provider.models().getExistingFile(malumPath("block/repair_pylon_component_top"));
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder().modelFile(s.getValue(RepairPylonComponentBlock.TOP) ? topModel : model).build());
    });


    public static BlockStateSmith<WeepingWellBlock> WEEPING_WELL_BLOCK = new BlockStateSmith<>(WeepingWellBlock.class, MalumItemModelSmithTypes.WEEPING_WELL_BLOCK_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ModelFile model = provider.models().getExistingFile(MalumMod.malumPath("block/weeping_well/" + name));
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder().modelFile(model).build());
    });

    public static BlockStateSmith<WeepingWellLayeredBlock> WEEPING_WELL_LAYERED_BLOCK = new BlockStateSmith<>(WeepingWellLayeredBlock.class, MalumItemModelSmithTypes.LAYERED_WEEPING_WELL_BLOCK_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);

        provider.getVariantBuilder(block).forAllStates(s -> {
            ModelFile model = provider.models().getExistingFile(MalumMod.malumPath("block/weeping_well/" + name + "_" + s.getValue(WeepingWellLayeredBlock.LAYER)));
            var direction = s.getValue(WeepingWellLayeredBlock.FACING);
            return ConfiguredModel.builder().modelFile(model).rotationY((int) (direction.toYRot() % 360)).build();
        });
    });

    public static BlockStateSmith<WeepingWellDirectionalBlock> WEEPING_WELL_DIRECTIONAL_BLOCK = new BlockStateSmith<>(WeepingWellDirectionalBlock.class, MalumItemModelSmithTypes.WEEPING_WELL_BLOCK_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ModelFile model = provider.models().getExistingFile(MalumMod.malumPath("block/weeping_well/" + name));
        provider.getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
                            .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.toYRot())) % 360)
                            .build();
                });
    });


    public static BlockStateSmith<PrimordialSoupBlock> PRIMORDIAL_SOUP = new BlockStateSmith<>(PrimordialSoupBlock.class, ItemModelSmithTypes.BLOCK_MODEL_ITEM.addTextureNameAffix("_top"), (block, provider) -> {
        String name = provider.getBlockName(block);
        ModelFile model = provider.models().withExistingParent(name, ResourceLocation.withDefaultNamespace("block/powder_snow")).texture("texture", malumPath("block/weeping_well/" + name));
        ModelFile topModel = provider.models().getExistingFile(malumPath("block/" + name + "_top"));
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder().modelFile(s.getValue(PrimordialSoupBlock.TOP) ? topModel : model).build());
    });


    public static BlockStateSmith<LargeStrangeCrystalBlock> LARGE_STRANGE_CRYSTAL = new BlockStateSmith<>(LargeStrangeCrystalBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        provider.getVariantBuilder(block)
                .forAllStates(state -> {
                    ResourceLocation upper = provider.getBlockTexture(name+"_upper");
                    ResourceLocation lower = provider.getBlockTexture(name+"_lower");
                    final DoubleBlockHalf half = state.getValue(LargeStrangeCrystalBlock.HALF);
                    boolean isTop = half.equals(DoubleBlockHalf.UPPER);
                    var model = provider.models().cross(name+"_"+half.getSerializedName(), isTop ? upper : lower);
                    return ConfiguredModel.builder().modelFile(model).build();
                });
    });

    public static BlockStateSmith<CreepingBlightBlock> CREEPING_BLIGHT = new BlockStateSmith<>(CreepingBlightBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ResourceLocation roots = malumPath("block/templates/blight/template_soulwood_roots");
        ResourceLocation spike = malumPath("block/templates/blight/template_soulwood_spike");
        ResourceLocation clinging = malumPath("block/templates/blight/template_clinging_blight");
        ResourceLocation hanging = malumPath("block/templates/blight/template_hanging_blight");

        provider.getVariantBuilder(block).forAllStates(s -> {
            CreepingBlightBlock.BlightType value = s.getValue(CreepingBlightBlock.BLIGHT_TYPE);
            String valueName = value.getSerializedName();
            ResourceLocation parent = switch (value) {
                case SOULWOOD_ROOTS -> roots;
                case SOULWOOD_SPIKE -> spike;
                case CLINGING_BLIGHT -> clinging;
                case HANGING_BLIGHT -> hanging;
            };
            ResourceLocation large = provider.getBlockTexture(valueName+"_large");
            ResourceLocation largeExtension = provider.getBlockTexture(valueName+"_large_extension");
            ResourceLocation side = provider.getBlockTexture(valueName +"_small");
            var model = provider.models().withExistingParent(name+"_"+ valueName, parent)
                    .texture("large", large)
                    .texture("large_extension", largeExtension)
                    .texture("small", side)
                    .texture("particle", large);
            if (parent.equals(roots)) {
                ResourceLocation small_extension = provider.getBlockTexture(valueName +"_small_extension");
                model.texture("small_extension", small_extension);
            }
            else {
                ResourceLocation bracingTexture = provider.getBlockTexture(valueName +"_bracing");
                model.texture("bracing", bracingTexture);
            }
            return ConfiguredModel.builder().modelFile(model).rotationY(((int) s.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build();
        });
    });

    public static BlockStateSmith<Block> BLIGHTED_GROWTH = new BlockStateSmith<>(Block.class, ItemModelSmithTypes.NO_DATAGEN, (block, provider) -> {
        String name = provider.getBlockName(block);
        Function<Integer, ModelFile> tumorFunction = (i) -> provider.models().withExistingParent(name + "_" + i, ResourceLocation.withDefaultNamespace("block/cross")).texture("cross", malumPath("block/" + name + "_" + i));

        ConfiguredModel.Builder<VariantBlockStateBuilder> builder = provider.getVariantBuilder(block).partialState().modelForState();
        for (int i = 0; i < 10; i++) {
            builder = builder.modelFile(tumorFunction.apply(i));
            if (i != 9) {
                builder = builder.nextModel();
            }
        }
        builder.addModel();
    });

    public static BlockStateSmith<EtherBrazierBlock> BRAZIER_BLOCK = new BlockStateSmith<>(EtherBrazierBlock.class, MalumItemModelSmithTypes.ETHER_BRAZIER_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replaceFirst("_iridescent", "");
        String particleName = textureName.replaceFirst("_ether_brazier", "") + "_rock";
        ModelFile brazier = provider.models().withExistingParent(name, malumPath("block/templates/template_ether_brazier")).texture("brazier", provider.getBlockTexture(textureName)).texture("particle", provider.getBlockTexture(particleName));
        ModelFile brazier_hanging = provider.models().withExistingParent(name + "_hanging", malumPath("block/templates/template_ether_brazier_hanging")).texture("brazier", provider.getBlockTexture(textureName)).texture("particle", provider.getBlockTexture(particleName));

        provider.getVariantBuilder(block)
                .partialState().with(EtherBrazierBlock.HANGING, false).modelForState().modelFile(brazier).addModel()
                .partialState().with(EtherBrazierBlock.HANGING, true).with(EtherBrazierBlock.ROTATED, false).modelForState().modelFile(brazier_hanging).addModel()
                .partialState().with(EtherBrazierBlock.HANGING, true).with(EtherBrazierBlock.ROTATED, true).modelForState().modelFile(brazier_hanging).rotationY(90).addModel();
    });

    public static BlockStateSmith<EtherBrazierBlock> IRIDESCENT_BRAZIER_BLOCK = new BlockStateSmith<>(EtherBrazierBlock.class, MalumItemModelSmithTypes.IRIDESCENT_ETHER_BRAZIER_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replaceFirst("_iridescent", "");
        String particleName = textureName.replaceFirst("_ether_brazier", "") + "_rock";
        ModelFile brazier = provider.models().withExistingParent(name, malumPath("block/templates/template_ether_brazier")).texture("brazier", provider.getBlockTexture(textureName)).texture("particle", provider.getBlockTexture(particleName));
        ModelFile brazier_hanging = provider.models().withExistingParent(name + "_hanging", malumPath("block/templates/template_ether_brazier_hanging")).texture("brazier", provider.getBlockTexture(textureName)).texture("particle", provider.getBlockTexture(particleName));

        provider.getVariantBuilder(block)
                .partialState().with(EtherBrazierBlock.HANGING, false).modelForState().modelFile(brazier).addModel()
                .partialState().with(EtherBrazierBlock.HANGING, true).with(EtherBrazierBlock.ROTATED, false).modelForState().modelFile(brazier_hanging).addModel()
                .partialState().with(EtherBrazierBlock.HANGING, true).with(EtherBrazierBlock.ROTATED, true).modelForState().modelFile(brazier_hanging).rotationY(90).addModel();
    });
}
