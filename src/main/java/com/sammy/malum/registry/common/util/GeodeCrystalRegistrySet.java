package com.sammy.malum.registry.common.util;

import com.sammy.malum.common.block.geode.BuddingGeodeBlock;
import com.sammy.malum.common.block.geode.CrystalGeodeBlock;
import com.sammy.malum.common.block.geode.CrystalLampBlock;
import com.sammy.malum.common.block.geode.GeodeCrystalClusterBlock;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.block.properties.MalumOreBlockProperties;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import team.lodestar.lodestone.modules.core.util.BlockItemTagKey;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.List;

import static com.sammy.malum.registry.common.MalumContent.*;

public class GeodeCrystalRegistrySet extends MalumRegistrySet {

    public static List<GeodeCrystalRegistrySet> getMalumCrystals() {
        return List.of(Materials.MUNDANE_QUARTZ, Materials.VIVID_AMETRINE, Materials.MARINE_AGATE, Materials.RUGGED_CITRINE);
    }

    private final MalumBlockSoundType clusterSound;
    private final MalumBlockSoundType geodeSound;
    private final MalumBlockSoundType lampSound;

    private final BlockBlockItemHolder<GeodeCrystalClusterBlock, BlockItem> cluster;
    private final BlockBlockItemHolder<Block, BlockItem> geode;
    private final BlockBlockItemHolder<Block, BlockItem> budding;
    private final BlockBlockItemHolder<Block, BlockItem> polished;
    private final BlockBlockItemHolder<Block, BlockItem> lamp;

    private final TagKey<Biome> biomeTag;

    public GeodeCrystalRegistrySet(String id) {
        super(id);

        clusterSound = new MalumBlockSoundType(name("%s_cluster"));
        geodeSound = new MalumBlockSoundType(name("%s_geode"));
        lampSound = new MalumBlockSoundType(name("%s_crystal_lamp"));

        var clusterProperties = MalumOreBlockProperties.CRYSTAL_CLUSTER(clusterSound);
        var geodeProperties = MalumOreBlockProperties.CRYSTAL_GEODE(geodeSound);
        var buddingProperties = MalumOreBlockProperties.CRYSTAL_GEODE(geodeSound).randomTicks();
        var lampProperties = MalumOreBlockProperties.CRYSTAL_LAMP(lampSound);
        cluster = registerItemNameBlock(name("%s_cluster"), id, () -> new GeodeCrystalClusterBlock(clusterProperties));
        geode = registerBlock(name("%s_geode"), () -> new CrystalGeodeBlock(geodeProperties));
        budding = registerBlock(name("budding_%s"), () -> new BuddingGeodeBlock(buddingProperties, cluster.block().get()));
        polished = registerBlock(name("polished_%s"), () -> new Block(geodeProperties));
        lamp = registerBlock(name("%s_lamp"), () -> new CrystalLampBlock(lampProperties));

        biomeTag = MalumTags.Biomes.tag(name("has_%s_geode"));
    }

    protected BlockItemTagKey createTag(String tag) {
        return BlockBundle.createTag(id, tag);
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.addItems(
                        polished,
                        budding,
                        geode,
                        cluster,
                        lamp
                )
                .bake();
    }

    public TagKey<Biome> getBiomeTag() {
        return biomeTag;
    }

    public MalumBlockSoundType getClusterSound() {
        return clusterSound;
    }

    public MalumBlockSoundType getGeodeSound() {
        return geodeSound;
    }

    public MalumBlockSoundType getLampSound() {
        return lampSound;
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

    public BlockBlockItemHolder<Block, BlockItem> getLamp() {
        return lamp;
    }
}