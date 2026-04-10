package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

import static net.neoforged.neoforge.common.Tags.Blocks.STORAGE_BLOCKS;

public class MalumFloraBlockProperties {

    public static LodestoneBlockProperties EBONY_SAPLING() {
        return new LodestoneBlockProperties()
                .strength(1F, 6.0F)
                .needsPickaxe()
                .noOcclusion()
                .setCutoutRenderType()
                .dynamicShape()
                .randomTicks()
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .addTag(MalumTags.Blocks.EBONY_PLANTABLE_ON)
                .sound(MalumBlockSoundEvents.EBONY_SAPLING);
    }

    public static LodestoneBlockProperties EBONY() {
        return new LodestoneBlockProperties()
                .strength(2F, 6.0F)
                .needsPickaxe()
                .noOcclusion()
                .setCutoutRenderType()
                .dynamicShape()
                .randomTicks()
                .addTag(MalumTags.Blocks.EBONY_PLANTABLE_ON)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .sound(MalumBlockSoundEvents.EBONY);
    }

    public static LodestoneBlockProperties WITCHHAZEL_CROP() {
        return new LodestoneBlockProperties()
                .strength(0.25F, 6.0F)
                .needsHoe()
                .addTags(STORAGE_BLOCKS)
                .mapColor(MapColor.COLOR_BLACK)
                .sound(MalumBlockSoundEvents.WITCHHAZEL);
    }

    public static LodestoneBlockProperties WILD_WITCHHAZEL() {
        return new LodestoneBlockProperties()
                .strength(0.5F, 6.0F)
                .needsHoe()
                .noOcclusion()
                .noCollission()
                .setCutoutRenderType()
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .sound(MalumBlockSoundEvents.WILD_WITCHHAZEL);
    }
}
