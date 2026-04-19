package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.REPLACEABLE_BY_TREES;

public class MalumBlightBlockProperties {

    public static LodestoneBlockProperties BLIGHT() {
        return new LodestoneBlockProperties()
                .addTags(BLIGHT)
                .mapColor(MapColor.COLOR_BLACK);
    }

    public static LodestoneBlockProperties BLIGHTED_EARTH() {
        return BLIGHT()
                .strength(1.2f, 0f)
                .sound(MalumBlockSoundEvents.BLIGHTED_EARTH)
                .addTag(BLIGHT_PLACEABLE_ON)
                .needsPickaxe()
                .needsAxe()
                .needsShovel()
                .needsHoe();
    }

    public static LodestoneBlockProperties BLIGHTED_COVERING() {
        return BLIGHT()
                .sound(MalumBlockSoundEvents.BLIGHTED_FOLIAGE)
                .addTag(REPLACEABLE_BY_TREES)
                .setCutout()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

    public static LodestoneBlockProperties BLIGHTED_PLANTS() {
        return BLIGHT()
                .addTags(BLIGHTED_PLANTS)
                .sound(MalumBlockSoundEvents.BLIGHTED_FOLIAGE)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .addTag(REPLACEABLE_BY_TREES)
                .setCutout()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

    public static LodestoneBlockProperties CLINGING_BLIGHT() {
        return BLIGHT()
                .addTags(BLIGHTED_PLANTS)
                .sound(MalumBlockSoundEvents.BLIGHTED_FOLIAGE)
                .addTag(REPLACEABLE_BY_TREES)
                .setCutout()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

    public static LodestoneBlockProperties SCARSTONE() {
        return new LodestoneBlockProperties()
                .sound(MalumBlockSoundEvents.SCARSTONE)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(4f, 3f)
                .requiresCorrectToolForDrops()
                .addTag(BLIGHT_PLACEABLE_ON)
                .needsPickaxe();
    }

    public static LodestoneBlockProperties STRANGE_CRYSTAL() {
        return new LodestoneBlockProperties()
                .strength(3f, 3f)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .sound(MalumBlockSoundEvents.STRANGE_CRYSTAL)
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .requiresCorrectToolForDrops()
                .setCutout()
                .noCollission()
                .lightLevel(b -> 7)
                .needsPickaxe()
                .noOcclusion()
                .randomTicks();
    }

    public static LodestoneBlockProperties STRANGEROOT() {
        return new LodestoneBlockProperties()
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .sound(MalumBlockSoundEvents.STRANGE_CRYSTAL)
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .setCutout()
                .lightLevel(b -> 4)
                .noCollission()
                .noOcclusion()
                .replaceable()
                .randomTicks();
    }
}
