package com.sammy.malum.registry.common.util;

import com.sammy.malum.common.block.geode.BuddingGeodeBlock;
import com.sammy.malum.common.block.geode.CrystalGeodeBlock;
import com.sammy.malum.common.block.geode.GeodeCrystalClusterBlock;
import com.sammy.malum.registry.common.block.properties.MalumOreBlockProperties;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import team.lodestar.lodestone.modules.core.util.BlockItemTagKey;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.List;

import static com.sammy.malum.registry.common.MalumContent.*;

public class GeodeCrystalSet {

    public static List<GeodeCrystalSet> getMalumCrystals() {
        return List.of(Materials.MUNDANE_QUARTZ, Materials.VIVID_AMETRINE, Materials.MARINE_AGATE, Materials.RUGGED_CITRINE, Materials.JAGGED_ONYX, Materials.PERFECT_QUARTZ, Materials.BLAZING_CARNELIAN);
    }

    public final String id;

    public String name(String name) {
        return name.replace("%s", id);
    }

    private final MalumBlockSoundType clusterSound;
    private final MalumBlockSoundType geodeSound;

    private final BlockBlockItemHolder<GeodeCrystalClusterBlock, BlockItem> cluster;
    private final BlockBlockItemHolder<Block, BlockItem> geode;
    private final BlockBlockItemHolder<Block, BlockItem> budding;
    private final BlockBlockItemHolder<Block, BlockItem> polished;

    public GeodeCrystalSet(String id) {
        this.id = id;

        clusterSound = new MalumBlockSoundType(name("%s_cluster"));
        geodeSound = new MalumBlockSoundType(name("%s_geode"));

        var clusterProperties = MalumOreBlockProperties.CRYSTAL_CLUSTER(clusterSound);
        var geodeProperties = MalumOreBlockProperties.CRYSTAL_GEODE(geodeSound);
        cluster = registerItemNameBlock(name("%s_cluster"), id, () -> new GeodeCrystalClusterBlock(clusterProperties));
        geode = registerBlock(name("%s_geode"), () -> new CrystalGeodeBlock(geodeProperties));
        budding = registerBlock(name("budding_%s"), () -> new BuddingGeodeBlock(geodeProperties, cluster.block().get()));
        polished = registerBlock(name("polished_%s"), () -> new CrystalGeodeBlock(geodeProperties));
    }

    protected BlockItemTagKey createTag(String tag) {
        return BlockBundle.createTag(id, tag);
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.addItems(
                        polished,
                        budding,
                        geode,
                        cluster
                )
                .bake();
    }

    public MalumBlockSoundType getClusterSound() {
        return clusterSound;
    }

    public MalumBlockSoundType getGeodeSound() {
        return geodeSound;
    }

    public BlockBlockItemHolder<GeodeCrystalClusterBlock, BlockItem> getCluster() {
        return cluster;
    }

    public BlockBlockItemHolder<Block, BlockItem> getGeode() {
        return geode;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBudding() {
        return budding;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPolished() {
        return polished;
    }
}