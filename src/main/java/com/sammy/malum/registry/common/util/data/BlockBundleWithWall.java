package com.sammy.malum.registry.common.util.data;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumContent.registerBlock;

public class BlockBundleWithWall extends BlockBundle {

    public final BlockBlockItemHolder<Block, BlockItem> wall;

    public BlockBundleWithWall(String name, Supplier<LodestoneBlockProperties> properties,
                               TagKey<Block> blocksTag, TagKey<Block> slabsTag, TagKey<Block> stairsTag, TagKey<Block> wallsTag) {
        super(name, properties, blocksTag, slabsTag, stairsTag);
        wall = registerBlock(name + "_wall", () -> new WallBlock(properties.get().addTag(wallsTag)));
    }

}