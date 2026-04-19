package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.common.block.ether.EtherCandleBlock;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.ETHER_TORCH;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.IRIDESCENT_ETHER_TORCH;
import static com.sammy.malum.registry.common.MalumTags.Blocks.FD_TRAY_HEAT_SOURCES;
import static net.minecraft.tags.BlockTags.CANDLES;
import static net.minecraft.tags.BlockTags.WALL_POST_OVERRIDE;

public class MalumEtherBlockProperties {

    public static LodestoneBlockProperties ETHER() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(FD_TRAY_HEAT_SOURCES)
                .sound(MalumBlockSoundEvents.ETHER)
                .noCollission()
                .instabreak()
                .setCutout()
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
                .setCutout();
    }

    public static LodestoneBlockProperties ETHER_TORCH() {
        return MalumWoodBlockProperties.RUNEWOOD()
                .addTag(WALL_POST_OVERRIDE)
                .mapColor(MapColor.COLOR_YELLOW)
                .addTag(FD_TRAY_HEAT_SOURCES)
                .noCollission()
                .instabreak()
                .setCutout()
                .lightLevel((b) -> 14);
    }

    public static LodestoneBlockProperties WALL_ETHER_TORCH() {
        return ETHER_TORCH().lootFrom(ETHER_TORCH);
    }

    public static LodestoneBlockProperties IRIDESCENT_WALL_ETHER_TORCH() {
        return ETHER_TORCH().lootFrom(IRIDESCENT_ETHER_TORCH);
    }

    public static LodestoneBlockProperties ETHER_BRAZIER() {
        return new LodestoneBlockProperties()
                .strength(1.5F, 9.0F)
                .mapColor(MapColor.COLOR_YELLOW)
                .requiresCorrectToolForDrops()
                .addTag(FD_TRAY_HEAT_SOURCES)
                .addTag(WALL_POST_OVERRIDE)
                .sound(SoundType.LANTERN)
                .needsPickaxe()
                .setCutout()
                .noOcclusion()
                .lightLevel((b) -> 14);
    }

    public static LodestoneBlockProperties ETHER_CRESSET() {
        return new LodestoneBlockProperties()
                .strength(1.5F, 9.0F)
                .mapColor(MapColor.COLOR_YELLOW)
                .requiresCorrectToolForDrops()
                .sound(SoundType.LANTERN)
                .needsPickaxe()
                .setCutout()
                .noOcclusion()
                .lightLevel((b) -> 14);
    }
}
