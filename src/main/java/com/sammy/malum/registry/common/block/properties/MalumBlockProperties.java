package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.sound.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.level.block.Blocks.COPPER_BLOCK;

public class MalumBlockProperties {

    public static LodestoneBlockProperties POTTED_PLANT() {
        return new LodestoneBlockProperties()
                .setCutout()
                .addTag(FLOWER_POTS)
                .instabreak()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY);
    }

    public static LodestoneBlockProperties  RUNEWOOD_IMPLEMENT() {
        return MalumWoodBlockProperties.RUNEWOOD()
                .strength(2.5f, 64f)
                .instrument(NoteBlockInstrument.XYLOPHONE)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .setCutout()
                .noOcclusion();
    }

    public static LodestoneBlockProperties COPPER_ARTIFICE() {
        return new LodestoneBlockProperties()
                .addTag(CREATE_WRENCH_PICKUP)
                .addTag(PREFERRED_SOULSTONE_BUD_SURFACE)
                .mapColor(COPPER_BLOCK.defaultMapColor())
                .strength(4.0F, 32.0F)
                .sound(MalumBlockSoundEvents.SPIRIT_DIODE)
                .requiresCorrectToolForDrops()
                .isRedstoneConductor(Blocks::never)
                .needsPickaxe()
                .needsAxe();
    }

    public static LodestoneBlockProperties SPIRIT_JAR() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.GOLD)
                .strength(1f, 64f)
                .sound(MalumBlockSoundEvents.BLOCK_OF_HALLOWED_GOLD)
                .setCutout()
                .noOcclusion();
    }

    public static LodestoneBlockProperties SOUL_BRAZIER() {
        return new LodestoneBlockProperties()
                .strength(2f, 64f)
                .instrument(NoteBlockInstrument.XYLOPHONE)
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .sound(MalumBlockSoundEvents.RUNEWOOD)
                .setCutout()
                .noOcclusion()
                .needsAxe();
    }

    public static LodestoneBlockProperties SPIRITED_GLASS() {
        return new LodestoneBlockProperties()
                .instrument(NoteBlockInstrument.HAT)
                .isRedstoneConductor(Blocks::never)
                .isViewBlocking(Blocks::never)
                .isSuffocating(Blocks::never)
                .isValidSpawn(Blocks::never)
                .sound(SoundType.GLASS)
                .setTranslucent()
                .strength(0.3F)
                .needsPickaxe()
                .noOcclusion();
    }

    public static LodestoneBlockProperties TRODDEN_STONE() {
        return new LodestoneBlockProperties()
                .strength(1F, 9.0F)
                .sound(MalumBlockSoundEvents.TRODDEN_STONE)
                .mapColor(MapColor.COLOR_GRAY)
                .requiresCorrectToolForDrops()
                .needsPickaxe();
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
                .setCutout()
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

    public static LodestoneBlockProperties SOULWOVEN_BANNER() {
        return MalumWoodBlockProperties.RUNEWOOD()
                .noOcclusion()
                .noCollission()
                .setCutout();
    }

    public static LodestoneBlockProperties PRIMORDIAL_SOUP() {
        return new LodestoneBlockProperties()
                .strength(-1.0F, 3600000.0F)
                .addTags(FEATURES_CANNOT_REPLACE, WEEPING_WELL)
                .sound(MalumBlockSoundEvents.BLIGHTED_EARTH)
                .mapColor(MapColor.TERRACOTTA_BLACK)
                .pushReaction(PushReaction.BLOCK)
                .isSuffocating(Blocks::never)
                .setCutout();
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
