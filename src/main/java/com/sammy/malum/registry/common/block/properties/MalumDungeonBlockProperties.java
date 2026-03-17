package com.sammy.malum.registry.common.block.properties;

import com.sammy.malum.registry.common.sound.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import team.lodestar.lodestone.modules.toolkit.block.*;

import static net.minecraft.tags.BlockTags.*;

public class MalumDungeonBlockProperties {

    public static LodestoneBlockProperties MEDITATING_EFFIGY() {
        return MalumBlockProperties.CHISELED_DROSS_STONE()
                .addTag(WALL_POST_OVERRIDE)
                .setCutoutRenderType()
                .noOcclusion()
                .strength(3.5F, -1.0F);
    }

    public static LodestoneBlockProperties OMINOUS_CRAFT() {
        return MalumBlockProperties.CHISELED_DROSS_STONE()
                .setCutoutRenderType()
                .noOcclusion()
                .strength(3.5F, -1.0F);
    }

    public static LodestoneBlockProperties ODD_SCRIPTURES() {
        return new LodestoneBlockProperties()
                .sound(MalumBlockSoundEvents.ODD_SCRIPTURES)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .pushReaction(PushReaction.PUSH_ONLY)
                .requiresCorrectToolForDrops()
                .mapColor(DyeColor.BLACK)
                .strength(2.5F)
                .needsPickaxe();
    }

    public static LodestoneBlockProperties FLESH() {
        return new LodestoneBlockProperties()
                .mapColor(MapColor.COLOR_BLACK);
    }

    public static LodestoneBlockProperties FLESH_BLOCK() {
        return FLESH()
                .sound(MalumBlockSoundEvents.FLESH)
                .strength(1.5F, -1.0F);
    }

    public static LodestoneBlockProperties FLESHBULB() {
        return FLESH_BLOCK()
                .lightLevel(s -> 6);
    }

    public static LodestoneBlockProperties WRITHING_FLESH() {
        return FLESH()
                .sound(MalumBlockSoundEvents.WRITHING_FLESH)
                .addTag(REPLACEABLE_BY_TREES)
                .setCutoutRenderType()
                .strength(0.5F, -1.0F)
                .noCollission()
                .noOcclusion();
    }
}