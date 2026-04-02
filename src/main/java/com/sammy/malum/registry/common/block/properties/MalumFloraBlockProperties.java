package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

import static com.sammy.malum.registry.common.MalumTags.BlockTags.BLIGHTED_PLANTS;
import static com.sammy.malum.registry.common.MalumTags.BlockTags.HEAT_SOURCES;
import static net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS;
import static net.minecraft.tags.BlockTags.REPLACEABLE_BY_TREES;
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
                .addTag(MalumTags.BlockTags.EBONY_PLANTABLE_ON)
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
                .addTag(MalumTags.BlockTags.EBONY_PLANTABLE_ON)
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
