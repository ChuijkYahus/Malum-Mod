package com.sammy.malum.datagen.block;

import com.sammy.malum.*;
import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.blight.scarstone.*;
import com.sammy.malum.common.block.curiosities.banner.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlock;
import com.sammy.malum.common.block.curiosities.repair_pylon.*;
import com.sammy.malum.common.block.curiosities.totem.TotemPoleBlock;
import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.*;
import com.sammy.malum.common.block.decor.ColumnBlock;
import com.sammy.malum.common.block.dungeon.WrithingFleshBlock;
import com.sammy.malum.common.block.ether.EtherBrazierBlock;
import com.sammy.malum.common.block.ether.EtherCressetBlock;
import com.sammy.malum.datagen.item.MalumItemModelSmithTypes;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import team.lodestar.lodestone.systems.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneBlockStateProvider;
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



    public static BlockStateSmith<ColumnBlock> COLUMN = new BlockStateSmith<>(ColumnBlock.class, MalumItemModelSmithTypes.BLOCK_MODEL_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        provider.getVariantBuilder(block).forAllStates(s -> {
            var upper = s.getValue(ColumnBlock.TOP);
            var lower = s.getValue(ColumnBlock.BOTTOM);
            var axis = s.getValue(ColumnBlock.AXIS);
            String affix = "";
            if (upper && lower) {
                affix = "_segment";
            }
            else if (upper) {
                affix = "_lower";
            }
            else if (lower) {
                affix = "_upper";
            }
            ResourceLocation side = provider.getBlockTexture(name + affix);
            ResourceLocation end = provider.getBlockTexture(name + "_end");
            BlockModelBuilder model = provider.models().cubeColumn(name + affix, side, end);
            int y = axis.equals(Direction.Axis.X) ? 90 : 180;
            int x = axis.equals(Direction.Axis.Y) ? 0 : 90;
            return ConfiguredModel.builder().modelFile(model).rotationX(x).rotationY(y).build();
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

    public static BlockStateSmith<AbstractGustGizmoBlock> GUST_TECH_BLOCK = new BlockStateSmith<>(AbstractGustGizmoBlock.class, ItemModelSmithTypes.BLOCK_MODEL_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ResourceLocation top = provider.getBlockTexture(name);
        ResourceLocation openTop = provider.getBlockTexture(name + "_open");
        ResourceLocation side = provider.getBlockTexture(name + "_side");
        ResourceLocation openSide = provider.getBlockTexture(name + "_side_open");
        ResourceLocation bottom = provider.getBlockTexture(name + "_bottom");
        BlockModelBuilder model = provider.models().cubeBottomTop(name, side, bottom, top).texture("particle", top);
        BlockModelBuilder openModel = provider.models().cubeBottomTop(name + "_open", openSide, bottom, openTop).texture("particle", top);
        provider.getVariantBuilder(block).forAllStates(s -> {
            var direction = s.getValue(AbstractGustGizmoBlock.FACING);
            var isOpen = s.getValue(AbstractGustGizmoBlock.OPEN) || !s.getValue(AbstractGustGizmoBlock.POWERED);
            return ConfiguredModel.builder().modelFile(isOpen ? openModel : model)
                    .rotationX(direction == Direction.DOWN ? 180 : direction.getAxis().isHorizontal() ? 90 : 0)
                    .rotationY(direction.getAxis().isVertical() ? 0 : (((int) direction.toYRot() + 180)) % 360)
                    .build();
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
        var roots = malumPath("block/templates/blight/template_soulwood_roots");
        var spike = malumPath("block/templates/blight/template_soulwood_spike");
        var clinging = malumPath("block/templates/blight/template_clinging_blight");
        var hanging = malumPath("block/templates/blight/template_hanging_blight");

        provider.getVariantBuilder(block).forAllStates(s -> {
            CreepingBlightBlock.BlightType value = s.getValue(CreepingBlightBlock.BLIGHT_TYPE);
            String valueName = value.getSerializedName();
            var parent = switch (value) {
                case SOULWOOD_ROOTS -> roots;
                case SOULWOOD_SPIKE -> spike;
                case CLINGING_BLIGHT -> clinging;
                case HANGING_BLIGHT -> hanging;
            };
            var largeStart = provider.getBlockTexture(valueName+"_large_start");
            var largeEnd = provider.getBlockTexture(valueName+"_large_end");
            var model = provider.models().withExistingParent(name+"_"+ valueName, parent)
                    .texture("large_start", largeStart)
                    .texture("large_end", largeEnd)
                    .texture("particle", largeStart);
            if (parent.equals(roots)) {
                var smallStart = provider.getBlockTexture(valueName +"_small_start");
                var smallEnd = provider.getBlockTexture(valueName +"_small_end");
                model.texture("small_start", smallStart);
                model.texture("small_end", smallEnd);
            }
            else {
                var bracing = provider.getBlockTexture(valueName +"_bracing");
                var small = provider.getBlockTexture(valueName +"_small");
                model.texture("bracing", bracing);
                model.texture("small", small);
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



    public static BlockStateSmith<WrithingFleshBlock> WRITHING_FLESH = new BlockStateSmith<>(WrithingFleshBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        var writhing = malumPath("block/templates/flesh/template_writhing_flesh");
        var clinging = malumPath("block/templates/flesh/template_clinging_flesh");
        var hanging = malumPath("block/templates/flesh/template_hanging_flesh");

        provider.getVariantBuilder(block).forAllStates(s -> {
            WrithingFleshBlock.FleshType value = s.getValue(WrithingFleshBlock.FLESH_TYPE);
            String valueName = value.getSerializedName();
            var parent = switch (value) {
                case WRITHING_FLESH -> writhing;
                case CLINGING_FLESH -> clinging;
                case HANGING_FLESH -> hanging;
            };
            var largeStart = provider.getBlockTexture(valueName+"_large_start");
            var largeEnd = provider.getBlockTexture(valueName+"_large_end");
            var model = provider.models().withExistingParent(name+"_"+ valueName, parent)
                    .texture("large_start", largeStart)
                    .texture("large_end", largeEnd)
                    .texture("particle", largeStart);
            if (parent.equals(writhing)) {
                var smallStart = provider.getBlockTexture(valueName +"_small_start");
                var smallEnd = provider.getBlockTexture(valueName +"_small_end");
                model.texture("small_start", smallStart);
                model.texture("small_end", smallEnd);
            }
            else {
                var small = provider.getBlockTexture(valueName +"_small");
                model.texture("small", small);
            }
            return ConfiguredModel.builder().modelFile(model).rotationY(((int) s.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build();
        });
    });
    public static BlockStateSmith<EtherBrazierBlock> BRAZIER_BLOCK = new BlockStateSmith<>(EtherBrazierBlock.class, MalumItemModelSmithTypes.ETHER_CONTAINING_ITEM.apply("ether_brazier"), MalumBlockStateSmithTypes::makeEtherBrazier);

    public static BlockStateSmith<EtherBrazierBlock> IRIDESCENT_BRAZIER_BLOCK = new BlockStateSmith<>(EtherBrazierBlock.class, MalumItemModelSmithTypes.IRIDESCENT_ETHER_CONTAINING_ITEM.apply("ether_brazier"), MalumBlockStateSmithTypes::makeEtherBrazier);

    public static void makeEtherBrazier(EtherBrazierBlock block, LodestoneBlockStateProvider provider) {
        var name = provider.getBlockName(block);
        var textureName = name.replaceFirst("_iridescent", "");
        var brazier = provider.getBlockTexture(textureName);
        var brazierTemplate = malumPath("block/templates/template_ether_brazier");
        var hangingTemplate = malumPath("block/templates/template_ether_brazier_hanging");
        var model = provider.models().withExistingParent(name, brazierTemplate).texture("brazier", brazier);
        var hanging = provider.models().withExistingParent(name + "_hanging", hangingTemplate).texture("brazier", brazier);

        provider.getVariantBuilder(block)
                .partialState().with(EtherBrazierBlock.HANGING, false).modelForState().modelFile(model).addModel()
                .partialState().with(EtherBrazierBlock.HANGING, true).with(EtherBrazierBlock.ROTATED, false).modelForState().modelFile(hanging).addModel()
                .partialState().with(EtherBrazierBlock.HANGING, true).with(EtherBrazierBlock.ROTATED, true).modelForState().modelFile(hanging).rotationY(90).addModel();
    }

    public static BlockStateSmith<EtherCressetBlock> CRESSET_BLOCK = new BlockStateSmith<>(EtherCressetBlock.class, MalumItemModelSmithTypes.ETHER_CONTAINING_ITEM.apply("ether_cresset"), MalumBlockStateSmithTypes::makeEtherCresset);

    public static BlockStateSmith<EtherCressetBlock> IRIDESCENT_CRESSET_BLOCK = new BlockStateSmith<>(EtherCressetBlock.class, MalumItemModelSmithTypes.IRIDESCENT_ETHER_CONTAINING_ITEM.apply("ether_cresset"), MalumBlockStateSmithTypes::makeEtherCresset);

    public static void makeEtherCresset(EtherCressetBlock block, LodestoneBlockStateProvider provider) {
        var name = provider.getBlockName(block);
        var textureName = name.replaceFirst("_iridescent", "");
        var parent = malumPath("block/templates/template_ether_cresset");
        var top = provider.getBlockTexture(textureName + "_top");
        var bottom = provider.getBlockTexture(textureName + "_bottom");
        var model = provider.models().withExistingParent(name, parent).texture("top", top).texture("bottom", bottom);
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder().modelFile(model).build());
    }
}
