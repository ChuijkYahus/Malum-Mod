package com.sammy.malum.registry.common.content.block.properties;

import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlock;
import com.sammy.malum.common.block.ether.EtherCandleBlock;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.common.Tags;
import team.lodestar.lodestone.modules.toolkit.block.*;

import java.awt.*;

import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.level.block.Blocks.COPPER_BLOCK;

public class MalumBlockProperties {

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
                .sound(MalumBlockSoundEvents.RUNEWOOD)
                .setCutoutRenderType()
                .noOcclusion()
                .needsAxe();
    }

    public static LodestoneBlockProperties SPIRIT_JAR() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.GOLD)
                .strength(1f, 64f)
                .sound(MalumBlockSoundEvents.BLOCK_OF_HALLOWED_GOLD)
                .noOcclusion();
    }

    public static LodestoneBlockProperties SOUL_BRAZIER() {
        return new LodestoneBlockProperties()
                .lightLevel(b -> b.getValue(SoulBrazierBlock.LIT) ? 8 : 0)
                .strength(2F, 30.0F)
                .instrument(NoteBlockInstrument.XYLOPHONE)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .sound(MalumBlockSoundEvents.RUNEWOOD)
                .setCutoutRenderType()
                .noOcclusion()
                .needsAxe();
    }

    public static LodestoneBlockProperties SPIRITED_GLASS() {
        return new LodestoneBlockProperties()
                .setRenderType(() -> RenderType::translucent)
                .instrument(NoteBlockInstrument.HAT)
                .isRedstoneConductor(Blocks::never)
                .isViewBlocking(Blocks::never)
                .isSuffocating(Blocks::never)
                .isValidSpawn(Blocks::never)
                .sound(SoundType.GLASS)
                .strength(0.3F)
                .needsPickaxe()
                .noOcclusion();
    }

    public static LodestoneBlockProperties TAINTED_ROCK() {
        return new LodestoneBlockProperties()
                .addTag(TAINTED_ROCK)
                .strength(1.25F, 9.0F)
                .sound(MalumBlockSoundEvents.TAINTED_ROCK)
                .mapColor(MapColor.COLOR_GRAY)
                .requiresCorrectToolForDrops()
                .needsPickaxe();
    }

    public static LodestoneBlockProperties TAINTED_ROCK_BRICKS() {
        return TAINTED_ROCK().sound(MalumBlockSoundEvents.TAINTED_ROCK_BRICKS);
    }

    public static LodestoneBlockProperties CHISELED_TAINTED_ROCK() {
        return TAINTED_ROCK().sound(MalumBlockSoundEvents.CHISELED_TAINTED_ROCK);
    }

    public static LodestoneBlockProperties TWISTED_ROCK() {
        return new LodestoneBlockProperties()
                .addTag(TWISTED_ROCK)
                .strength(1.25F, 9.0F)
                .sound(MalumBlockSoundEvents.TWISTED_ROCK)
                .mapColor(MapColor.COLOR_BLACK)
                .requiresCorrectToolForDrops()
                .needsPickaxe();
    }

    public static LodestoneBlockProperties TWISTED_ROCK_BRICKS() {
        return TWISTED_ROCK().sound(MalumBlockSoundEvents.TWISTED_ROCK_BRICKS);
    }

    public static LodestoneBlockProperties CHISELED_TWISTED_ROCK() {
        return TWISTED_ROCK().sound(MalumBlockSoundEvents.CHISELED_TWISTED_ROCK);
    }

    public static LodestoneBlockProperties DROSS_STONE() {
        return new LodestoneBlockProperties()
                .addTag(DROSS_STONE)
                .strength(2F, -1.0F)
                .sound(MalumBlockSoundEvents.DROSS_STONE)
                .mapColor(MapColor.COLOR_BROWN)
                .requiresCorrectToolForDrops()
                .needsPickaxe();
    }

    public static LodestoneBlockProperties DROSS_STONE_BRICKS() {
        return DROSS_STONE().sound(MalumBlockSoundEvents.DROSS_STONE_BRICKS);
    }

    public static LodestoneBlockProperties CHISELED_DROSS_STONE() {
        return DROSS_STONE().sound(MalumBlockSoundEvents.CHISELED_DROSS_STONE);
    }

    public static LodestoneBlockProperties ARCANE_ROCK_ARTIFICE() {
        return TAINTED_ROCK_BRICKS()
                .strength(2.5f, 30.0F)
                .sound(MalumBlockSoundEvents.ARCANE_ROCK_ARTIFICE)
                .setCutoutRenderType()
                .noOcclusion();
    }

    public static LodestoneBlockProperties TAINTED_ROCK_TOTEMANCY() {
        return TAINTED_ROCK_BRICKS()
                .strength(2F, 30.0F)
                .isRedstoneConductor(Blocks::never)
                .addTag(IS_RITE_IMMUNE);
    }

    public static LodestoneBlockProperties TWISTED_ROCK_TOTEMANCY() {
        return TWISTED_ROCK_BRICKS()
                .strength(2F, 30.0F)
                .isRedstoneConductor(Blocks::never)
                .addTag(IS_RITE_IMMUNE);
    }

    public static LodestoneBlockProperties VARNISHED_TERRACOTTA(DyeColor color) {
        return new LodestoneBlockProperties()
                .sound(MalumBlockSoundEvents.VARNISHED_TERRACOTTA)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .pushReaction(PushReaction.PUSH_ONLY)
                .requiresCorrectToolForDrops()
                .mapColor(color)
                .strength(1.4F)
                .needsPickaxe();
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
                .sound(MalumBlockSoundEvents.RUNEWOOD)
                .needsAxe();
    }

    public static LodestoneBlockProperties RUNEWOOD_LOGS() {
        return RUNEWOOD().addTags(LOGS, OVERWORLD_NATURAL_LOGS, RUNEWOOD_LOGS);
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
                .addTag(SAPLINGS)
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
                .addTag(LEAVES)
                .strength(0.2F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .setCutoutRenderType()
                .sound(MalumBlockSoundEvents.RUNEWOOD_LEAVES)
                .needsHoe();
    }

    public static LodestoneBlockProperties HANGING_RUNEWOOD_LEAVES() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(0.05F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .setCutoutRenderType()
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
    public static LodestoneBlockProperties SOULWOOD_LOGS() {
        return SOULWOOD().addTags(LOGS, SOULWOOD_LOGS);
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
                .needsHoe()
                .strength(0.05F)
                .randomTicks()
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .sound(MalumBlockSoundEvents.SOULWOOD_LEAVES);
    }

    public static LodestoneBlockProperties SOULWOVEN_BANNER() {
        return RUNEWOOD()
                .noOcclusion()
                .noCollission()
                .setCutoutRenderType();
    }

    public static LodestoneBlockProperties SOULWOOD_SAPLING() {
        return new LodestoneBlockProperties()
                .addTag(BLIGHTED_PLANTS)
                .sound(MalumBlockSoundEvents.BLIGHTED_FOLIAGE)
                .mapColor(MapColor.TERRACOTTA_BLACK)
                .setCutoutRenderType()
                .noCollission()
                .randomTicks()
                .noOcclusion()
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
                .sound(MalumBlockSoundEvents.STRANGE_CRYSTAL)
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
                .setCutoutRenderType()
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
                .setCutoutRenderType()
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
                .setCutoutRenderType()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .instabreak();
    }

    public static LodestoneBlockProperties NATURAL_QUARTZ_CLUSTER() {
        return new LodestoneBlockProperties()
                .addTag(Tags.Blocks.CLUSTERS)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(1.5F)
                .sound(MalumBlockSoundEvents.NATURAL_QUARTZ_CLUSTER);
    }

    public static LodestoneBlockProperties BLAZING_QUARTZ_CLUSTER() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(Tags.Blocks.CLUSTERS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(1.5F)
                .sound(MalumBlockSoundEvents.BLAZING_QUARTZ_CLUSTER);
    }

    public static LodestoneBlockProperties CTHONIC_GOLD_CLUSTER() {
        return new LodestoneBlockProperties()
                .addTag(Tags.Blocks.CLUSTERS)
                .mapColor(MapColor.COLOR_YELLOW)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(4f, 9999f)
                .sound(MalumBlockSoundEvents.CTHONIC_GOLD_CLUSTER);
    }

    public static LodestoneBlockProperties ETHER() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(FD_TRAY_HEAT_SOURCES)
                .sound(MalumBlockSoundEvents.ETHER)
                .noCollission()
                .instabreak()
                .setCutoutRenderType()
                .lightLevel((b) -> 14);
    }

    public static LodestoneBlockProperties ETHER_CANDLE() {
        return new LodestoneBlockProperties()
                .addTag(CANDLES)
                .mapColor(MapColor.COLOR_YELLOW)
                .noOcclusion()
                .strength(0.1F)
                .sound(SoundType.CANDLE)
                .lightLevel(s -> 3 * s.getValue(EtherCandleBlock.CANDLES))
                .pushReaction(PushReaction.DESTROY)
                .setCutoutRenderType();
    }
    public static LodestoneBlockProperties ETHER_TORCH() {
        return RUNEWOOD()
                .addTag(WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(FD_TRAY_HEAT_SOURCES)
                .noCollission()
                .instabreak()
                .setCutoutRenderType()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties TAINTED_ETHER_BRAZIER() {
        return TAINTED_ROCK()
                .addTag(WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(FD_TRAY_HEAT_SOURCES)
                .setCutoutRenderType()
                .noOcclusion()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties TWISTED_ETHER_BRAZIER() {
        return TWISTED_ROCK()
                .addTag(WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(FD_TRAY_HEAT_SOURCES)
                .setCutoutRenderType()
                .noOcclusion()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties DROSS_ETHER_BRAZIER() {
        return DROSS_STONE()
                .addTag(WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(FD_TRAY_HEAT_SOURCES)
                .setCutoutRenderType()
                .noOcclusion()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties TAINTED_ETHER_CRESSET() {
        return TAINTED_ROCK()
                .sound(SoundType.LANTERN)
                .mapColor(MapColor.COLOR_YELLOW)
                .setCutoutRenderType()
                .noOcclusion()
                .noCollission()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties TWISTED_ETHER_CRESSET() {
        return TWISTED_ROCK()
                .sound(SoundType.LANTERN)
                .mapColor(MapColor.COLOR_YELLOW)
                .setCutoutRenderType()
                .noOcclusion()
                .noCollission()
                .lightLevel((b) -> 14);
    }
    public static LodestoneBlockProperties DROSS_ETHER_CRESSET() {
        return DROSS_STONE()
                .sound(SoundType.LANTERN)
                .mapColor(MapColor.COLOR_YELLOW)
                .setCutoutRenderType()
                .noOcclusion()
                .noCollission()
                .lightLevel((b) -> 14);
    }

    public static LodestoneBlockProperties WAVEFORM_DIODE() {
        return new LodestoneBlockProperties()
                .addTag(CREATE_WRENCH_PICKUP)
                .mapColor(COPPER_BLOCK.defaultMapColor())
                .strength(3.0F, 32.0F)
                .sound(MalumBlockSoundEvents.SPIRIT_DIODE)
                .requiresCorrectToolForDrops()
                .isRedstoneConductor(Blocks::never)
                .needsPickaxe()
                .needsAxe();
    }

    public static LodestoneBlockProperties GUST_TECH() {
        return new LodestoneBlockProperties()
                .addTag(CREATE_WRENCH_PICKUP)
                .mapColor(COPPER_BLOCK.defaultMapColor())
                .strength(4.0F, 32.0F)
                .sound(MalumBlockSoundEvents.SPIRIT_DIODE)
                .requiresCorrectToolForDrops()
                .isRedstoneConductor(Blocks::never)
                .needsPickaxe();
    }

    public static LodestoneBlockProperties PRIMORDIAL_SOUP() {
        return new LodestoneBlockProperties()
                .strength(-1.0F, 3600000.0F)
                .addTags(FEATURES_CANNOT_REPLACE, WEEPING_WELL)
                .sound(MalumBlockSoundEvents.BLIGHTED_EARTH)
                .mapColor(MapColor.TERRACOTTA_BLACK)
                .pushReaction(PushReaction.BLOCK)
                .isSuffocating(Blocks::never)
                .setCutoutRenderType();
    }

    public static LodestoneBlockProperties WEEPING_WELL() {
        return new LodestoneBlockProperties()
                .strength(-1.0F, 3600000.0F)
                .sound(MalumBlockSoundEvents.WEEPING_WELL_BRICKS)
                .addTags(FEATURES_CANNOT_REPLACE, WEEPING_WELL)
                .mapColor(MapColor.COLOR_GRAY)
                .isValidSpawn(Blocks::never)
                .noLootTable();
    }
}
