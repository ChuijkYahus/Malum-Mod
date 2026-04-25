package com.sammy.malum.registry.common.util.data;

import com.sammy.malum.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneStairBlock;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumContent.registerBlock;

public class BlockBundle {

    public final BlockBlockItemHolder<Block, BlockItem> block;
    public final BlockBlockItemHolder<Block, BlockItem> slab;
    public final BlockBlockItemHolder<Block, BlockItem> stairs;

    public BlockBundle(String name, Supplier<LodestoneBlockProperties> properties) {
        this(name, properties, createTag(name, "blocks"), createTag(name, "slabs"), createTag(name, "stairs"));
    }

    public BlockBundle(String name, Supplier<LodestoneBlockProperties> properties,
                       TagKey<Block> blocksTag, TagKey<Block> slabsTag, TagKey<Block> stairsTag) {
        block = registerBlock(name, () -> new Block(properties.get().addTag(blocksTag)));
        slab = registerBlock(name + "_slab", () -> new SlabBlock(properties.get().addTag(slabsTag)));
        stairs = registerBlock(name + "_stairs", () -> new LodestoneStairBlock(properties.get().addTag(stairsTag)));
    }

    protected static TagKey<Block> createTag(String name, String tag) {
        return BlockTags.create(MalumMod.malumPath(name + "_" + tag));
    }
}