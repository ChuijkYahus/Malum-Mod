package com.sammy.malum.registry.common.util.data;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import team.lodestar.lodestone.modules.core.util.BlockItemTagKey;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneStairBlock;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumContent.registerBlock;
import static com.sammy.malum.registry.common.MalumContent.registerBlockNoItem;

public class ItemlessBlockBundle {

    public final DeferredBlock<Block> block;
    public final DeferredBlock<Block> slab;
    public final DeferredBlock<Block> stairs;

    public ItemlessBlockBundle(String name, Supplier<LodestoneBlockProperties> properties,
                               BlockItemTagKey blocksTag, BlockItemTagKey slabsTag, BlockItemTagKey stairsTag) {
        block = registerBlockNoItem(name, () -> new Block(properties.get().addTag(blocksTag)));
        slab = registerBlockNoItem(name + "_slab", () -> new SlabBlock(properties.get().addTag(slabsTag)));
        stairs = registerBlockNoItem(name + "_stairs", () -> new LodestoneStairBlock(properties.get().addTag(stairsTag)));
    }
}
