package com.sammy.malum.datagen.block;

import com.sammy.malum.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.neoforged.neoforge.client.model.generators.*;
import team.lodestar.lodestone.modules.datagen.DatagenSystemCommons;
import team.lodestar.lodestone.modules.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.modules.datagen.providers.block.LodestoneBlockStateSystem;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSmith;
import team.lodestar.lodestone.modules.datagen.smith.itemmodel.*;

import java.util.ArrayList;

public class VariedBlockStateSmithTypes {

    public static final ConfiguredItemModelSmith AFFIXED = ItemModelSmithTypes.BLOCK_MODEL_ITEM.addModelParentAffix("0");

    public static BlockStateSmith<Block> VARIED_FULL_BLOCK = new BlockStateSmith<>(Block.class, AFFIXED, VariedBlockStateSmithTypes::variedBlock);

    public static BlockStateSmith<StairBlock> VARIED_STAIRS_BLOCK = new BlockStateSmith<>(StairBlock.class, AFFIXED, VariedBlockStateSmithTypes::variedStairsBlock);

    public static BlockStateSmith<SlabBlock> VARIED_SLAB_BLOCK = new BlockStateSmith<>(SlabBlock.class, AFFIXED, VariedBlockStateSmithTypes::variedSlabBlock);

    public static BlockStateSmith<WallBlock> VARIED_WALL_BLOCK = new BlockStateSmith<>(WallBlock.class, AFFIXED, VariedBlockStateSmithTypes::variedWallBlock);

    public static void variedBlock(Block block, LodestoneBlockStateSystem provider) {
        var name = provider.getBlockName(block);

        var textures = gatherTextures(provider, name);

        int amount = textures.size();
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        for (int i = 0; i < amount; i++) {
            var texture = textures.get(i);
            var modelFile = provider.models().cubeAll(name+i, texture);
            builder.modelFile(modelFile);
            if (i != amount -1) {
                builder = builder.nextModel();
            }
        }
        provider.getVariantBuilder(block).partialState().addModels(builder.build());
    }

    public static void variedSlabBlock(SlabBlock block, LodestoneBlockStateSystem provider) {
        var name = provider.getBlockName(block);
        var baseTextureName = name.replace("_slab", "");

        var textures = gatherTextures(provider, baseTextureName);

        ArrayList<ModelFile> models = new ArrayList<>();
        for (int i = 0; i < textures.size(); i++) {
            var texture = textures.get(i);
            var modelName = name + i;
            models.add(new ModelFile.UncheckedModelFile(MalumMod.malumPath(baseTextureName + i)));
            models.add(provider.models().slab(modelName, texture, texture, texture));
            models.add(provider.models().slabTop(modelName + "_top", texture, texture, texture));
        }

        int amount = textures.size();
        provider.getVariantBuilder(block).forAllStates(state -> {
            var type = state.getValue(SlabBlock.TYPE);
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            for (int i = 0; i < amount; i++) {
                int index = i * 3;
                ModelFile modelFile = switch (type) {
                    case DOUBLE -> models.get(index);
                    case BOTTOM -> models.get(index+1);
                    case TOP -> models.get(index+2);
                };
                builder.modelFile(modelFile);
                if (i != amount -1) {
                    builder = builder.nextModel();
                }
            }
            return builder.build();
        });
    }

    public static void variedStairsBlock(StairBlock block, LodestoneBlockStateSystem provider) {
        var name = provider.getBlockName(block);
        var baseTextureName = name.replace("_stairs", "");

        ArrayList<ResourceLocation> textures = gatherTextures(provider, baseTextureName);

        ArrayList<ModelFile> models = new ArrayList<>();
        for (int i = 0, texturesSize = textures.size(); i < texturesSize; i++) {
            var texture = textures.get(i);
            var modelName = name + i;
            models.add(provider.models().stairs(modelName, texture, texture, texture));
            models.add(provider.models().stairsInner(modelName + "_inner", texture, texture, texture));
            models.add(provider.models().stairsOuter(modelName + "_outer", texture, texture, texture));
        }
        int amount = textures.size();
        provider.getVariantBuilder(block).forAllStatesExcept((state) -> {
            Direction facing = state.getValue(StairBlock.FACING);
            Half half = state.getValue(StairBlock.HALF);
            StairsShape shape = state.getValue(StairBlock.SHAPE);
            int yRot = (int)facing.getClockWise().toYRot();
            if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
                yRot += 270;
            }

            if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
                yRot += 90;
            }

            yRot %= 360;
            boolean uvlock = yRot != 0 || half == Half.TOP;
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            for (int i = 0; i < amount; i++) {
                int index = i * 3;
                var stairs = models.get(index);
                var stairsInner = models.get(index+1);
                var stairsOuter = models.get(index+2);
                ModelFile modelFile = shape == StairsShape.STRAIGHT ? stairs : (shape != StairsShape.INNER_LEFT && shape != StairsShape.INNER_RIGHT ? stairsOuter : stairsInner);
                builder.modelFile(modelFile).rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).uvLock(uvlock);
                if (i != amount-1) {
                    builder = builder.nextModel();
                }
            }
            return builder.build();
        }, StairBlock.WATERLOGGED);
    }

    public static void variedWallBlock(WallBlock block, LodestoneBlockStateSystem provider) {
        var name = provider.getBlockName(block);
        var baseTextureName = name.replace("_wall", "");

        var textures = gatherTextures(provider, baseTextureName);
        int amount = textures.size();

        var postModels = new ArrayList<ModelFile>();
        var propModels = new ArrayList<ModelFile>();
        for (int i = 0, texturesSize = textures.size(); i < texturesSize; i++) {
            var texture = textures.get(i);
            var modelName = name + i;

            postModels.add(provider.models().wallPost(modelName + "_post", texture));
            propModels.add(provider.models().wallSide(modelName + "_side", texture));
            propModels.add(provider.models().wallSideTall(modelName + "_side_tall", texture));
        }


        var builder = provider.getMultipartBuilder(block);
        var part = builder.part();

        for (int i = 0; i < amount; i++) {
            var modelFile = postModels.get(i);
            part.modelFile(modelFile);
            if (i != amount -1) {
                part = part.nextModel();
            }
        }

        part.addModel().condition(WallBlock.UP, true).end();
        BlockStateProvider.WALL_PROPS.forEach((key, value) -> {
            var sidePart = builder.part();
            var tallSidePart = builder.part();
            for (int i = 0; i < amount; i++) {
                var sideModel = propModels.get(i * 2);
                int rotation = (((int) key.toYRot()) + 180) % 360;
                var tallSideModel = propModels.get(i * 2 + 1);
                sidePart.modelFile(sideModel).rotationY(rotation).uvLock(true);
                tallSidePart.modelFile(tallSideModel).rotationY(rotation).uvLock(true);
                if (i != amount - 1) {
                    sidePart = sidePart.nextModel();
                    tallSidePart = tallSidePart.nextModel();
                }
            }

            sidePart.addModel().condition(value, WallSide.LOW);
            tallSidePart.addModel().condition(value, WallSide.TALL);
        });
    }

    public static ArrayList<ResourceLocation> gatherTextures(LodestoneBlockStateSystem provider, String baseTextureName) {
        int gatheredTextures = 0;
        ArrayList<ResourceLocation> textures = new ArrayList<>();
        while (true) {
            var texture = provider.getBlockTexture(baseTextureName + gatheredTextures);
            ResourceLocation checkAhead = DatagenSystemCommons.BLOCK_TEXTURE.apply(texture, "block/").orElseThrow();
            if (!provider.models().existingFileHelper.exists(checkAhead, ModelProvider.TEXTURE)) {
                break;
            }
            textures.add(texture);
            gatheredTextures++;
        }
        return textures;
    }
}