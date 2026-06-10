package com.sammy.malum.registry.common.util.building;

import com.sammy.malum.common.block.geode.BuddingGeodeBlock;
import com.sammy.malum.common.block.geode.CrystalGeodeBlock;
import com.sammy.malum.common.block.geode.CrystalLampBlock;
import com.sammy.malum.common.block.geode.GeodeCrystalClusterBlock;
import com.sammy.malum.common.data.component.SoulwovenBannerPatternDataComponent;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.block.properties.MalumOreBlockProperties;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import com.sammy.malum.registry.common.util.MalumRegistrySet;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import team.lodestar.lodestone.modules.core.util.BlockItemTagKey;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static com.sammy.malum.common.data.component.SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS;
import static com.sammy.malum.registry.common.MalumContent.*;

public class MinorBuildingSet extends MalumRegistrySet {

    public static List<MinorBuildingSet> getMalumSets() {
        return List.of(BlockSets.TRODDEN_STONE, BlockSets.SEED_QUARTZ, BlockSets.EBONSTONE);
    }

    private final BlockBundle raw;
    private final BlockBundle polished;
    private final BlockBundleWithWall bricks;
    private final BlockBundleWithWall tiles;

    public MinorBuildingSet(String id, Supplier<LodestoneBlockProperties> properties, Supplier<LodestoneBlockProperties> polishedProperties) {
        super(id);

        raw = new BlockBundle(id, properties);
        polished = new BlockBundle(name("polished_%s"), polishedProperties);

        bricks = new BlockBundleWithWall(name("%s_bricks"), polishedProperties);
        tiles = new BlockBundleWithWall(name("%s_tiles"), polishedProperties);
    }

    protected BlockItemTagKey createTag(String tag) {
        return BlockBundle.createTag(id, tag);
    }

    public static void addCommonRock(CreativeTabCategoryBuilder builder) {
        for (MinorBuildingSet set : getMalumSets()) {
            set.addToCreativeTab(builder);
        }
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.nextLine().addItems(
                        raw.block, polished.block, bricks.block, tiles.block,
                        raw.stairs, polished.stairs, bricks.stairs, tiles.stairs,
                        raw.slab, polished.slab, bricks.slab, tiles.slab
                )
                .bake();
    }

    public BlockBundle getRaw() {
        return raw;
    }

    public BlockBundle getPolished() {
        return polished;
    }

    public BlockBundleWithWall getBricks() {
        return bricks;
    }

    public BlockBundleWithWall getTiles() {
        return tiles;
    }
}