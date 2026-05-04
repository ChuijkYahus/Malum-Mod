package com.sammy.malum.registry.common.util.data;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import team.lodestar.lodestone.modules.core.util.BlockItemTagKey;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumContent.registerBlock;

public class BlockBundleWithWall extends BlockBundle {

    public final BlockBlockItemHolder<Block, BlockItem> wall;

    public BlockBundleWithWall(String name, Supplier<LodestoneBlockProperties> properties) {
        this(name, properties, createTag(name, "blocks"), createTag(name, "slabs"), createTag(name, "stairs"), createTag(name, "walls"));
    }
    public BlockBundleWithWall(String name, Supplier<LodestoneBlockProperties> properties,
                               BlockItemTagKey blocksTag, BlockItemTagKey slabsTag, BlockItemTagKey stairsTag, BlockItemTagKey wallsTag) {
        super(name, properties, blocksTag, slabsTag, stairsTag);
        wall = registerBlock(name + "_wall", () -> new WallBlock(properties.get().addTag(wallsTag)));
    }

}