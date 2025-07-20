package com.sammy.malum.registry.common.block;

import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlock;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.common.Tags;
import team.lodestar.lodestone.systems.block.*;

import java.awt.*;

import static com.sammy.malum.registry.common.MalumTags.BlockTags.*;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.level.block.Blocks.COPPER_BLOCK;
import static net.neoforged.neoforge.common.Tags.Blocks.STORAGE_BLOCKS;

public class MalumBlockProperties {

    public static LodestoneBlockProperties STORAGE_BLOCK(SoundType soundType, DyeColor mapColor) {
        return new LodestoneBlockProperties()
                .strength(2F, 6.0F)
                .addTags(STORAGE_BLOCKS)
                .mapColor(mapColor)
                .sound(soundType);
    }


    public static LodestoneBlockProperties POTTED_PLANT() {
        return new LodestoneBlockProperties()
                .setCutoutRenderType()
                .addTag(FLOWER_POTS)
                .instabreak()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY);
    }

    public static LodestoneBlockProperties SPIRIT_ALTAR() {
        return new LodestoneBlockProperties()
                .strength(2F, 30.0F)
                .instrument(NoteBlockInstrument.XYLOPHONE)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .sound(MalumSoundEvents.RUNEWOOD)
                .setCutoutRenderType()
                .noOcclusion()
                .needsAxe();
    }

    public static LodestoneBlockProperties SOUL_BRAZIER() {
        return new LodestoneBlockProperties()
                .lightLevel(b -> b.getValue(SoulBrazierBlock.LIT) ? 8 : 0)
                .strength(2F, 30.0F)
                .instrument(NoteBlockInstrument.XYLOPHONE)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .sound(MalumSoundEvents.RUNEWOOD)
                .setCutoutRenderType()
                .noOcclusion()
                .needsAxe();
    }


    public static LodestoneBlockProperties SPIRITED_GLASS() {
        return new LodestoneBlockProperties()
                .setRenderType(() -> RenderType::translucent)
                .instrument(NoteBlockInstrument.HAT)
                .sound(SoundType.GLASS)
                .strength(0.3F)
                .needsPickaxe()
                .noOcclusion();
    }

    public static LodestoneBlockProperties TAINTED_ROCK() {
        return new LodestoneBlockProperties()
                .addTag(MalumTags.BlockTags.TAINTED_ROCK)
                .strength(1.25F, 9.0F)
                .sound(MalumSoundEvents.TAINTED_ROCK)
                .mapColor(MapColor.COLOR_GRAY)
                .requiresCorrectToolForDrops()
                .needsPickaxe();
    }

    public static LodestoneBlockProperties TAINTED_ROCK_BRICKS() {
        return TAINTED_ROCK().sound(MalumSoundEvents.TAINTED_ROCK_BRICKS);
    }

    public static LodestoneBlockProperties TAINTED_ROCK_ARTIFICE() {
        return TAINTED_ROCK_BRICKS()
                .strength(2.5f, 30.0F)
                .setCutoutRenderType()
                .noOcclusion();
    }

    public static LodestoneBlockProperties RITE_ANCHOR() {
        return TAINTED_ROCK_BRICKS()
                .strength(2F, 30.0F)
                .addTag(IS_RITE_IMMUNE);
    }

    public static LodestoneBlockProperties TWISTED_ROCK() {
        return new LodestoneBlockProperties()
                .addTag(MalumTags.BlockTags.TWISTED_ROCK)
                .strength(1.25F, 9.0F)
                .sound(MalumSoundEvents.TWISTED_ROCK)
                .mapColor(MapColor.COLOR_BLACK)
                .requiresCorrectToolForDrops()
                .needsPickaxe();
    }

    public static LodestoneBlockProperties TWISTED_ROCK_BRICKS() {
        return TWISTED_ROCK().sound(MalumSoundEvents.TWISTED_ROCK_BRICKS);
    }

    public static final Color RUNEWOOD_LEAVES_YELLOW = new Color(251, 193, 76);
    public static final Color RUNEWOOD_LEAVES_ORANGE = new Color(217, 110, 23);
    public static final Color AZURE_RUNEWOOD_LEAVES_CYAN = new Color(176, 234, 255);
    public static final Color AZURE_RUNEWOOD_LEAVES_BLUE = new Color(64, 95, 157);

    public static LodestoneBlockProperties RUNEWOOD() {
        return new LodestoneBlockProperties()
                .strength(1.75F, 4.0F)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .sound(MalumSoundEvents.RUNEWOOD)
                .needsAxe();
    }

    public static LodestoneBlockProperties RUNEWOOD_PLANKS() {
        return RUNEWOOD().addTag(PLANKS);
    }

    public static LodestoneBlockProperties RUNEWOOD_SLABS() {
        return RUNEWOOD().addTags(SLABS, WOODEN_SLABS);
    }

    public static LodestoneBlockProperties RUNEWOOD_STAIRS() {
        return RUNEWOOD().addTags(STAIRS, WOODEN_STAIRS);
    }

    public static LodestoneBlockProperties RUNEWOOD_DOOR() {
        return RUNEWOOD().addTags(DOORS, WOODEN_DOORS).setCutoutRenderType().noOcclusion();
    }

    public static LodestoneBlockProperties RUNEWOOD_TRAPDOOR() {
        return RUNEWOOD().addTags(TRAPDOORS, WOODEN_TRAPDOORS).setCutoutRenderType().noOcclusion();
    }

    public static LodestoneBlockProperties RUNEWOOD_SAPLING() {
        return new LodestoneBlockProperties()
                .addTag(net.minecraft.tags.BlockTags.SAPLINGS)
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .sound(SoundType.GRASS)
                .setCutoutRenderType()
                .noCollission()
                .noOcclusion()
                .randomTicks()
                .instabreak();
    }

    public static LodestoneBlockProperties RUNEWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .addTag(net.minecraft.tags.BlockTags.LEAVES)
                .strength(0.2F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating((a, b, c) -> false)
                .isViewBlocking((a, b, c) -> false)
                .setCutoutRenderType()
                .sound(MalumSoundEvents.RUNEWOOD_LEAVES)
                .needsHoe();
    }

    public static LodestoneBlockProperties HANGING_RUNEWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(0.05F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating((a, b, c) -> false)
                .isViewBlocking((a, b, c) -> false)
                .setCutoutRenderType()
                .sound(MalumSoundEvents.RUNEWOOD_LEAVES)
                .needsHoe();
    }

    public static LodestoneBlockProperties SOULWOOD() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_PURPLE)
                .sound(MalumSoundEvents.SOULWOOD)
                .strength(1.75F, 4.0F)
                .instrument(NoteBlockInstrument.BASS)
                .needsAxe();
    }

    public static LodestoneBlockProperties SOULWOOD_PLANKS() {
        return SOULWOOD().addTag(PLANKS);
    }

    public static LodestoneBlockProperties SOULWOOD_SLABS() {
        return SOULWOOD().addTags(SLABS, WOODEN_SLABS);
    }

    public static LodestoneBlockProperties SOULWOOD_STAIRS() {
        return SOULWOOD().addTags(STAIRS, WOODEN_STAIRS);
    }

    public static LodestoneBlockProperties SOULWOOD_DOOR() {
        return SOULWOOD().addTags(DOORS, WOODEN_DOORS).setCutoutRenderType().noOcclusion();
    }

    public static LodestoneBlockProperties SOULWOOD_TRAPDOOR() {
        return SOULWOOD().addTags(TRAPDOORS, WOODEN_TRAPDOORS).setCutoutRenderType().noOcclusion();
    }

    public static LodestoneBlockProperties SOULWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_RED)
                .addTag(net.minecraft.tags.BlockTags.LEAVES)
                .needsHoe()
                .strength(0.2F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating((a, b, c) -> false)
                .isViewBlocking((a, b, c) -> false)
                .sound(MalumSoundEvents.SOULWOOD_LEAVES);
    }

    public static LodestoneBlockProperties HANGING_SOULWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_RED)
                .needsHoe()
                .strength(0.05F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating((a, b, c) -> false)
                .isViewBlocking((a, b, c) -> false)
                .sound(MalumSoundEvents.SOULWOOD_LEAVES);
    }

    public static LodestoneBlockProperties SOULWOVEN_BANNER() {
        return RUNEWOOD()
                .noOcclusion()
                .noCollission()
                .setCutoutRenderType();
    }

    public static LodestoneBlockProperties SOULWOOD_SAPLING() {
        return new LodestoneBlockProperties()
                .addTag(MalumTags.BlockTags.BLIGHTED_PLANTS)
                .sound(MalumSoundEvents.BLIGHTED_FOLIAGE)
                .mapColor(MapColor.TERRACOTTA_BLACK)
                .setCutoutRenderType()
                .noCollission()
                .randomTicks()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

    public static LodestoneBlockProperties SCARSTONE() {
        return new LodestoneBlockProperties()
                .sound(MalumSoundEvents.SCARSTONE)
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
                .sound(MalumSoundEvents.STRANGE_CRYSTAL)
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .requiresCorrectToolForDrops()
                .setCutoutRenderType()
                .noCollission()
                .lightLevel(b -> 7)
                .needsPickaxe()
                .noOcclusion()
                .randomTicks();
    }

    public static LodestoneBlockProperties STRANGEROOT() {
        return new LodestoneBlockProperties()
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .sound(MalumSoundEvents.STRANGE_CRYSTAL)
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .setCutoutRenderType()
                .lightLevel(b -> 4)
                .noCollission()
                .noOcclusion()
                .replaceable()
                .randomTicks();
    }

    public static LodestoneBlockProperties BLIGHT() {
        return new LodestoneBlockProperties()
                .addTags(MalumTags.BlockTags.BLIGHT)
                .mapColor(MapColor.COLOR_BLACK);
    }

    public static LodestoneBlockProperties BLIGHTED_EARTH() {
        return BLIGHT()
                .strength(1.2f, 0f)
                .sound(MalumSoundEvents.BLIGHTED_EARTH)
                .addTag(BLIGHT_PLACEABLE_ON)
                .needsPickaxe()
                .needsAxe()
                .needsShovel()
                .needsHoe();
    }

    public static LodestoneBlockProperties BLIGHTED_COVERING() {
        return BLIGHT()
                .sound(MalumSoundEvents.BLIGHTED_FOLIAGE)
                .addTag(REPLACEABLE_BY_TREES)
                .setCutoutRenderType()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

    public static LodestoneBlockProperties BLIGHTED_PLANTS() {
        return BLIGHT()
                .addTags(MalumTags.BlockTags.BLIGHTED_PLANTS)
                .sound(MalumSoundEvents.BLIGHTED_FOLIAGE)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .addTag(REPLACEABLE_BY_TREES)
                .setCutoutRenderType()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

    public static LodestoneBlockProperties CLINGING_BLIGHT() {
        return BLIGHT()
                .addTags(MalumTags.BlockTags.BLIGHTED_PLANTS)
                .sound(MalumSoundEvents.BLIGHTED_FOLIAGE)
                .addTag(REPLACEABLE_BY_TREES)
                .setCutoutRenderType()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

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
                .sound(isDeepslate ? MalumSoundEvents.DEEPSLATE_SOULSTONE : MalumSoundEvents.SOULSTONE);
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
                .addTag(Tags.Blocks.ORE_RATES_SINGULAR)
                .strength(isDeepslate ? 6f : 4f, 3f)
                .sound(isDeepslate ? MalumSoundEvents.DEEPSLATE_QUARTZ_ORE : MalumSoundEvents.QUARTZ_ORE);
    }

    public static LodestoneBlockProperties NATURAL_QUARTZ_CLUSTER() {
        return new LodestoneBlockProperties()
                .addTag(Tags.Blocks.CLUSTERS)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(1.5F)
                .sound(MalumSoundEvents.QUARTZ_CLUSTER);
    }

    public static LodestoneBlockProperties BLAZING_QUARTZ_ORE() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .addTag(Tags.Blocks.ORE_RATES_SINGULAR)
                .addTag(Tags.Blocks.ORES)
                .addTag(Tags.Blocks.ORES_IN_GROUND_NETHERRACK)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .sound(MalumSoundEvents.BLAZING_QUARTZ_ORE);
    }

    public static LodestoneBlockProperties BLAZING_QUARTZ_CLUSTER() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(Tags.Blocks.CLUSTERS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(1.5F)
                .sound(MalumSoundEvents.BLAZING_QUARTZ_CLUSTER);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_ORE() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(Tags.Blocks.ORE_RATES_DENSE)
                .addTag(Tags.Blocks.ORES)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(10f, 9999f)
                .sound(MalumSoundEvents.CTHONIC_GOLD);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_CLUSTER() {
        return new LodestoneBlockProperties()
                .addTag(Tags.Blocks.CLUSTERS)
                .mapColor(MapColor.COLOR_YELLOW)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(4f, 9999f)
                .sound(MalumSoundEvents.CTHONIC_GOLD);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(10f, 9999f)
                .sound(MalumSoundEvents.CTHONIC_GOLD);
    }

    public static LodestoneBlockProperties ETHER() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(MalumTags.BlockTags.TRAY_HEAT_SOURCES)
                .sound(MalumSoundEvents.ETHER)
                .noCollission()
                .instabreak()
                .setCutoutRenderType()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties ETHER_TORCH() {
        return RUNEWOOD()
                .addTag(net.minecraft.tags.BlockTags.WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(MalumTags.BlockTags.TRAY_HEAT_SOURCES)
                .noCollission()
                .instabreak()
                .setCutoutRenderType()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties TAINTED_ETHER_BRAZIER() {
        return TAINTED_ROCK()
                .addTag(net.minecraft.tags.BlockTags.WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(MalumTags.BlockTags.TRAY_HEAT_SOURCES)
                .setCutoutRenderType()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties TWISTED_ETHER_BRAZIER() {
        return TWISTED_ROCK()
                .addTag(net.minecraft.tags.BlockTags.WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(MalumTags.BlockTags.TRAY_HEAT_SOURCES)
                .setCutoutRenderType()
                .lightLevel((b) -> 14);
    }

    public static LodestoneBlockProperties WAVEFORM_DIODE() {
        return new LodestoneBlockProperties()
                .addTag(MalumTags.BlockTags.CREATE_WRENCH_PICKUP)
                .mapColor(COPPER_BLOCK.defaultMapColor())
                .strength(3.0F, 6.0F)
                .sound(MalumSoundEvents.SPIRIT_DIODE)
                .requiresCorrectToolForDrops()
                .isRedstoneConductor(Blocks::never)
                .needsPickaxe()
                .needsAxe();
    }

    public static LodestoneBlockProperties MANA_MOTE_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_LIGHT_BLUE)
                .setRenderType(() -> RenderType::cutout)
                .noOcclusion()
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(8.0F, 8.0f)
                .lightLevel((b) -> 8)
                .sound(MalumSoundEvents.BLAZING_QUARTZ_BLOCK);
    }

    public static LodestoneBlockProperties SOULSTONE_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_PURPLE)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 3.0F)
                .sound(MalumSoundEvents.SOULSTONE);
    }

    public static LodestoneBlockProperties BLAZING_QUARTZ_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_ORANGE)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .addTags(MalumTags.BlockTags.HEAT_SOURCES)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(MalumSoundEvents.BLAZING_QUARTZ_BLOCK);
    }

    public static LodestoneBlockProperties BRILLIANCE_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_GREEN)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 3.0F)
                .sound(MalumSoundEvents.BRILLIANCE_BLOCK);
    }

    public static LodestoneBlockProperties ARCANE_CHARCOAL_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_GRAY)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(MalumSoundEvents.ARCANE_CHARCOAL_BLOCK);
    }

    public static LodestoneBlockProperties SOUL_STAINED_STEEL_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_PURPLE)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .requiresCorrectToolForDrops()
                .needsPickaxe()
                .sound(MalumSoundEvents.SOUL_STAINED_STEEL)
                .strength(5f, 64.0f);
    }

    public static LodestoneBlockProperties HALLOWED_GOLD() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.GOLD)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .requiresCorrectToolForDrops()
                .needsPickaxe()
                .sound(MalumSoundEvents.HALLOWED_GOLD)
                .noOcclusion()
                .strength(2F, 16.0F);
    }

    public static LodestoneBlockProperties MALIGNANT_LEAD_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_LIGHT_BLUE)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .requiresCorrectToolForDrops()
                .needsPickaxe()
                .sound(MalumSoundEvents.MALIGNANT_LEAD)
                .strength(10f, 9999f);
    }

    public static LodestoneBlockProperties MALIGNANT_PEWTER_BLOCK() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_LIGHT_BLUE)
                .addTag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS)
                .requiresCorrectToolForDrops()
                .needsPickaxe()
                .sound(MalumSoundEvents.MALIGNANT_PEWTER)
                .strength(10f, 9999f);
    }

    public static LodestoneBlockProperties SPIRIT_JAR() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.GOLD)
                .strength(1f, 64f)
                .sound(MalumSoundEvents.HALLOWED_GOLD)
                .noOcclusion();
    }

    public static LodestoneBlockProperties WEEPING_WELL() {
        return new LodestoneBlockProperties()
                .strength(-1.0F, 3600000.0F)
                .sound(MalumSoundEvents.WEEPING_WELL_BRICKS)
                .isRedstoneConductor((a,b,c) -> false)
                .addTags(FEATURES_CANNOT_REPLACE, WEEPING_WELL)
                .mapColor(MapColor.COLOR_GRAY)
                .isValidSpawn(Blocks::never)
                .noLootTable();
    }

    public static LodestoneBlockProperties PRIMORDIAL_SOUP() {
        return new LodestoneBlockProperties()
                .strength(-1.0F, 3600000.0F)
                .sound(MalumSoundEvents.BLIGHTED_EARTH)
                .mapColor(MapColor.TERRACOTTA_BLACK)
                .pushReaction(PushReaction.BLOCK)
                .addTags(FEATURES_CANNOT_REPLACE, WEEPING_WELL)
                .setCutoutRenderType();
    }
}
