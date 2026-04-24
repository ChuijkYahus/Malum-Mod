package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.common.block.soulstone.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

import static com.sammy.malum.registry.common.MalumTags.Blocks.NATURAL_SOULSTONE_BUD_SURFACE;
import static com.sammy.malum.registry.common.sound.MalumBlockSoundEvents.*;
import static net.minecraft.world.level.material.MapColor.*;
import static net.neoforged.neoforge.common.Tags.Blocks.*;

public class MalumOreBlockProperties {

    public static LodestoneBlockProperties ORE_PROPERTIES(boolean isDeepslate) {
        return new LodestoneBlockProperties()
                .addTag(isDeepslate ? ORES_IN_GROUND_DEEPSLATE : ORES_IN_GROUND_STONE)
                .addTag(ORES)
                .needsPickaxe()
                .requiresCorrectToolForDrops();
    }

    public static LodestoneBlockProperties SOULSTONE_BUD() {
        return new LodestoneBlockProperties()
                .requiresCorrectToolForDrops()
                .needsPickaxe()
                .needsIron()
                .noOcclusion()
                .randomTicks()
                .dynamicShape()
                .mapColor(TERRACOTTA_PURPLE)
                .offsetFunction(SoulstoneBudCommons.BUD_OFFSET)
                .strength(4F, 3.0F)
                .sound(SOULSTONE_BUD);
    }

    public static LodestoneBlockProperties ARCHAIC_SOULSTONE_BUD() {
        return new LodestoneBlockProperties()
                .requiresCorrectToolForDrops()
                .needsPickaxe()
                .needsIron()
                .noOcclusion()
                .randomTicks()
                .dynamicShape()
                .mapColor(TERRACOTTA_PURPLE)
                .offsetFunction(SoulstoneBudCommons.BUD_OFFSET)
                .strength(4F, 3.0F)
                .sound(SOULSTONE_BUD);
    }

    public static LodestoneBlockProperties SOULSTONE_ORE(boolean isDeepslate) {
        return ORE_PROPERTIES(isDeepslate)
                .needsStone()
                .mapColor(TERRACOTTA_PURPLE)
                .addTag(ORE_RATES_SINGULAR)
                .addTag(NATURAL_SOULSTONE_BUD_SURFACE)
                .strength(isDeepslate ? 7.0f : 5.0F, 3.0F)
                .sound(isDeepslate ? DEEPSLATE_SOULSTONE_ORE : SOULSTONE_ORE);
    }

    public static LodestoneBlockProperties BRILLIANCE_ORE(boolean isDeepslate) {
        return ORE_PROPERTIES(isDeepslate)
                .mapColor(COLOR_GREEN)
                .addTag(ORE_RATES_SINGULAR)
                .strength(isDeepslate ? 5f : 3f, 3f)
                .sound(isDeepslate ? DEEPSLATE_BRILLIANCE_ORE : BRILLIANCE_ORE);
    }

    public static LodestoneBlockProperties NATURAL_QUARTZ_ORE(boolean isDeepslate) {
        return ORE_PROPERTIES(isDeepslate)
                .mapColor(TERRACOTTA_WHITE)
                .addTags(ORE_RATES_SINGULAR, ORES_QUARTZ)
                .strength(isDeepslate ? 6f : 4f, 3f)
                .sound(isDeepslate ? NATURAL_DEEPSLATE_QUARTZ_ORE : NATURAL_QUARTZ_ORE);
    }

    public static LodestoneBlockProperties NATURAL_QUARTZ_CLUSTER() {
        return new LodestoneBlockProperties()
                .addTag(CLUSTERS)
                .mapColor(TERRACOTTA_WHITE)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(1.5F)
                .sound(NATURAL_QUARTZ_CLUSTER);
    }

    public static LodestoneBlockProperties BLAZING_QUARTZ_ORE() {
        return new LodestoneBlockProperties()
                .mapColor(TERRACOTTA_ORANGE)
                .addTags(ORE_RATES_SINGULAR, ORES, ORES_IN_GROUND_NETHERRACK)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .sound(BLAZING_QUARTZ_ORE);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_ORE() {
        return new LodestoneBlockProperties()
                .mapColor(COLOR_YELLOW)
                .addTag(ORE_RATES_DENSE)
                .addTag(ORES)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(10f, 9999f)
                .sound(CTHONIC_GOLD_ORE);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_CLUSTER() {
        return new LodestoneBlockProperties()
                .addTag(CLUSTERS)
                .mapColor(COLOR_YELLOW)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(4f, 9999f)
                .sound(CTHONIC_GOLD_CLUSTER);
    }
}