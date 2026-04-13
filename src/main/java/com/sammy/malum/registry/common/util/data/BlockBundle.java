package com.sammy.malum.registry.common.util.data;

import net.minecraft.tags.TagKey;
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

    public BlockBundle(String name, Supplier<LodestoneBlockProperties> properties,
                       TagKey<Block> blocksTag, TagKey<Block> slabsTag, TagKey<Block> stairsTag) {
        block = registerBlock(name, () -> new Block(properties.get().addTag(blocksTag)));
        slab = registerBlock(name + "_slab", () -> new SlabBlock(properties.get().addTag(slabsTag)));
        stairs = registerBlock(name + "_stairs", () -> new LodestoneStairBlock(properties.get().addTag(stairsTag)));
    }
}
