package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.sound.*;
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.common.*;
import team.lodestar.lodestone.systems.block.*;

public class MalumOreBlockProperties {

    public static LodestoneBlockProperties ORE_PROPERTIES(boolean isDeepslate) {
        return new LodestoneBlockProperties()
                .addTag(isDeepslate ? Tags.Blocks.ORES_IN_GROUND_DEEPSLATE : Tags.Blocks.ORES_IN_GROUND_STONE)
                .addTag(Tags.Blocks.ORES)
                .needsPickaxe()
                .requiresCorrectToolForDrops();
    }

    public static LodestoneBlockProperties SOULSTONE_ORE(boolean isDeepslate) {
        return ORE_PROPERTIES(isDeepslate)
                .mapColor(MapColor.TERRACOTTA_PURPLE)
                .addTag(Tags.Blocks.ORE_RATES_SINGULAR)
                .strength(isDeepslate ? 7.0f : 5.0F, 3.0F)
                .sound(isDeepslate ? MalumSoundEvents.DEEPSLATE_SOULSTONE_ORE : MalumSoundEvents.SOULSTONE_ORE);
    }

    public static LodestoneBlockProperties BRILLIANCE_ORE(boolean isDeepslate) {
        return ORE_PROPERTIES(isDeepslate)
                .mapColor(MapColor.COLOR_GREEN)
                .addTag(Tags.Blocks.ORE_RATES_SINGULAR)
                .strength(isDeepslate ? 5f : 3f, 3f)
                .sound(isDeepslate ? MalumSoundEvents.DEEPSLATE_BRILLIANCE_ORE : MalumSoundEvents.BRILLIANCE_ORE);
    }

    public static LodestoneBlockProperties NATURAL_QUARTZ_ORE(boolean isDeepslate) {
        return ORE_PROPERTIES(isDeepslate)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .addTags(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Blocks.ORES_QUARTZ)
                .strength(isDeepslate ? 6f : 4f, 3f)
                .sound(isDeepslate ? MalumSoundEvents.NATURAL_DEEPSLATE_QUARTZ_ORE : MalumSoundEvents.NATURAL_QUARTZ_ORE);
    }

    public static LodestoneBlockProperties BLAZING_QUARTZ_ORE() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .addTags(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Blocks.ORES, Tags.Blocks.ORES_IN_GROUND_NETHERRACK)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .sound(MalumSoundEvents.BLAZING_QUARTZ_ORE);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_ORE() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(Tags.Blocks.ORE_RATES_DENSE)
                .addTag(Tags.Blocks.ORES)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(10f, 9999f)
                .sound(MalumSoundEvents.CTHONIC_GOLD_ORE);
    }
}