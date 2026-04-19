package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

import static com.sammy.malum.registry.common.MalumTags.Blocks.BLIGHTED_PLANTS;
import static net.minecraft.tags.BlockTags.LEAVES;
import static net.minecraft.tags.BlockTags.SAPLINGS;

public class MalumWoodBlockProperties {

    public static LodestoneBlockProperties RUNEWOOD() {
        return new LodestoneBlockProperties()
                .strength(1.75F, 4.0F)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .sound(MalumBlockSoundEvents.RUNEWOOD)
                .needsAxe();
    }

    public static LodestoneBlockProperties RUNEWOOD_SAPLING() {
        return new LodestoneBlockProperties()
                .addTag(SAPLINGS)
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .sound(SoundType.GRASS)
                .setCutout()
                .noCollission()
                .noOcclusion()
                .randomTicks()
                .instabreak();
    }

    public static LodestoneBlockProperties RUNEWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .setCutout()
                .addTag(LEAVES)
                .strength(0.2F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .sound(MalumBlockSoundEvents.RUNEWOOD_LEAVES)
                .needsHoe();
    }

    public static LodestoneBlockProperties HANGING_RUNEWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .setCutout()
                .strength(0.05F)
                .randomTicks()
                .noOcclusion()
                .noCollission()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .dynamicShape()
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .sound(MalumBlockSoundEvents.RUNEWOOD_LEAVES)
                .needsHoe();
    }

    public static LodestoneBlockProperties SOULWOOD() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_PURPLE)
                .sound(MalumBlockSoundEvents.SOULWOOD)
                .strength(1.75F, 4.0F)
                .instrument(NoteBlockInstrument.BASS)
                .needsAxe();
    }

    public static LodestoneBlockProperties SOULWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_RED)
                .setCutout()
                .addTag(LEAVES)
                .needsHoe()
                .strength(0.2F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .sound(MalumBlockSoundEvents.SOULWOOD_LEAVES);
    }

    public static LodestoneBlockProperties HANGING_SOULWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_RED)
                .setCutout()
                .needsHoe()
                .strength(0.05F)
                .randomTicks()
                .noOcclusion()
                .noCollission()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .dynamicShape()
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .sound(MalumBlockSoundEvents.SOULWOOD_LEAVES);
    }

    public static LodestoneBlockProperties SOULWOOD_SAPLING() {
        return new LodestoneBlockProperties()
                .addTag(BLIGHTED_PLANTS)
                .sound(MalumBlockSoundEvents.BLIGHTED_FOLIAGE)
                .mapColor(MapColor.TERRACOTTA_BLACK)
                .setCutout()
                .noCollission()
                .randomTicks()
                .noOcclusion()
                .instabreak();
    }
}
