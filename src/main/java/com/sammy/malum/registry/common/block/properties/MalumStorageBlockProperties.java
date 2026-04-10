package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.sound.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

import static com.sammy.malum.registry.common.MalumTags.Blocks.FD_HEAT_SOURCES;
import static net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS;
import static net.neoforged.neoforge.common.Tags.Blocks.STORAGE_BLOCKS;

public class MalumStorageBlockProperties {

    public static LodestoneBlockProperties GENERIC_STORAGE_BLOCK(SoundType soundType, DyeColor mapColor) {
        return new LodestoneBlockProperties()
                .strength(2F, 6.0F)
                .addTags(STORAGE_BLOCKS)
                .mapColor(mapColor)
                .sound(soundType);
    }

    //TODO: move this once mana motes are cool
    public static LodestoneBlockProperties MANA_MOTE_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_LIGHT_BLUE)
                .setRenderType(() -> RenderType::cutout)
                .noOcclusion()
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(8.0F, 8.0f)
                .lightLevel((b) -> 8)
                .sound(MalumBlockSoundEvents.BLOCK_OF_BLAZING_QUARTZ);
    }

    public static LodestoneBlockProperties SOULSTONE_BLOCK(boolean raw) {
        return GENERIC_STORAGE_BLOCK(raw ? MalumBlockSoundEvents.BLOCK_OF_RAW_SOULSTONE : MalumBlockSoundEvents.BLOCK_OF_SOULSTONE, DyeColor.PURPLE)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 3.0F);
    }

    public static LodestoneBlockProperties BRILLIANCE_BLOCK(boolean raw) {
        return GENERIC_STORAGE_BLOCK(raw ? MalumBlockSoundEvents.BLOCK_OF_RAW_BRILLIANCE : MalumBlockSoundEvents.BLOCK_OF_BRILLIANCE, DyeColor.GREEN)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 3.0F);
    }

    public static LodestoneBlockProperties NATURAL_QUARTZ_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_NATURAL_QUARTZ, DyeColor.WHITE)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(1f);
    }

    public static LodestoneBlockProperties BLAZING_QUARTZ_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_BLAZING_QUARTZ, DyeColor.ORANGE)
                .addTag(BEACON_BASE_BLOCKS)
                .addTags(FD_HEAT_SOURCES)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_CTHONIC_GOLD, DyeColor.YELLOW)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(10f, 9999f);
    }

    public static LodestoneBlockProperties ARCANE_CHARCOAL_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_ARCANE_CHARCOAL, DyeColor.GRAY)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F);
    }

    public static LodestoneBlockProperties EBONY_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_EBONY, DyeColor.BLACK)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F);
    }

    public static LodestoneBlockProperties WITCHHAZEL_CRATE() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.RUNEWOOD, DyeColor.PURPLE)
                .needsAxe()
                .strength(2.0f, 3.0F);
    }

    public static LodestoneBlockProperties SOUL_STAINED_STEEL_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_SOUL_STAINED_STEEL, DyeColor.PURPLE)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5f, 64.0f);
    }

    public static LodestoneBlockProperties HALLOWED_GOLD() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_HALLOWED_GOLD, DyeColor.YELLOW)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .strength(2F, 16.0F);
    }

    public static LodestoneBlockProperties MALIGNANT_LEAD_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_MALIGNANT_LEAD, DyeColor.LIGHT_BLUE)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(10f, 9999f);
    }

    public static LodestoneBlockProperties MALIGNANT_PEWTER_BLOCK() {
        return GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.BLOCK_OF_MALIGNANT_PEWTER, DyeColor.LIGHT_BLUE)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(10f, 9999f);
    }
}
