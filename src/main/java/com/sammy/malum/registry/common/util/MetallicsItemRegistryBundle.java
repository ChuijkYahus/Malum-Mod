package com.sammy.malum.registry.common.util;

import com.sammy.malum.common.item.metallics.FracturedMetalImpetusItem;
import com.sammy.malum.common.item.metallics.MetalImpetusItem;
import com.sammy.malum.common.item.metallics.MetalNodeItem;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.properties.MalumOreBlockProperties;
import com.sammy.malum.registry.common.block.properties.MalumStorageBlockProperties;
import com.sammy.malum.registry.common.item.MalumItemProperties;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneDirectionalBlock;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS;

public class MetallicsItemRegistryBundle {

    public static List<MetallicsItemRegistryBundle> getMalumMetallics() {
        return List.of(MalumContent.AlchemyAndMetallics.IRON_METALLICS,
                MalumContent.AlchemyAndMetallics.COPPER_METALLICS,
                MalumContent.AlchemyAndMetallics.GOLD_METALLICS,
                MalumContent.AlchemyAndMetallics.ZINC_METALLICS,
                MalumContent.AlchemyAndMetallics.LEAD_METALLICS,
                MalumContent.AlchemyAndMetallics.SILVER_METALLICS,
                MalumContent.AlchemyAndMetallics.ALUMINIUM_METALLICS,
                MalumContent.AlchemyAndMetallics.NICKEL_METALLICS);
    }

    protected final String id;

    protected final TagKey<Block> oreTag;
    protected final TagKey<Block> deepslateOreTag;
    protected final TagKey<Item> nuggetTag;
    protected final TagKey<Item> ingotTag;

    protected final DeferredItem<Item> impetus;
    protected final DeferredItem<Item> fracturedImpetus;
    protected final DeferredItem<Item> node;

    protected final DeferredItem<Item> derealizedMetal;
    protected final DeferredItem<Item> harmonizedMetal;

    protected final MalumBlockSoundType oreSound;
    protected final MalumBlockSoundType deepslateOreSound;

    protected final MalumBlockSoundType derealizedBlockSound;
    protected final MalumBlockSoundType harmonizedBlockSound;

    protected final BlockBlockItemHolder<Block, BlockItem> ore;
    protected final BlockBlockItemHolder<Block, BlockItem> deepslateOre;
    protected final BlockBlockItemHolder<Block, BlockItem> derealizedStorageBlock;
    protected final BlockBlockItemHolder<Block, BlockItem> harmonizedStorageBlock;


    public MetallicsItemRegistryBundle(String id) {
        this.id = id;

        oreTag = createOreTag(false);
        deepslateOreTag = createOreTag(true);
        nuggetTag = createNuggetTag();
        ingotTag = createIngotTag();

        var impetusName = id + "_impetus";
        var fracturedImpetusName = "fractured_" + impetusName;
        var nodeName = id + "_node";

        var derealized = "derealized_" + id;
        var harmonized = "harmonized_" + id;

        var oreName = derealized + "_ore";
        var deepslateOreName = "deepslate_" + oreName;
        var derealizedBlockName = "block_of_" + derealized;
        var harmonizedBlockName = "block_of_" + harmonized;

        impetus = MalumContent.register(impetusName, MalumItemProperties::IMPETUS, MetalImpetusItem::new);
        fracturedImpetus = MalumContent.register(fracturedImpetusName, MalumItemProperties::IMPETUS, FracturedMetalImpetusItem::new);
        node = MalumContent.register(nodeName, MalumItemProperties::IMPETUS, MetalNodeItem::new);

        derealizedMetal = MalumContent.register(derealized, MalumItemProperties::DEFAULT, Item::new);
        harmonizedMetal = MalumContent.register(harmonized, MalumItemProperties::DEFAULT, Item::new);

        oreSound = new MalumBlockSoundType(oreName);
        deepslateOreSound = new MalumBlockSoundType(deepslateOreName);
        derealizedBlockSound = new MalumBlockSoundType(derealizedBlockName);
        harmonizedBlockSound = new MalumBlockSoundType(harmonizedBlockName);

        ore = registerBlock(oreName, () -> new DropExperienceBlock(UniformInt.of(2, 4), makeOreProperties(false)));
        deepslateOre = registerBlock(deepslateOreName, () -> new DropExperienceBlock(UniformInt.of(3, 6), makeOreProperties(true)));
        derealizedStorageBlock = registerBlock(derealizedBlockName, () -> new LodestoneDirectionalBlock(makeStorageBlockProperties(false)));
        harmonizedStorageBlock = registerBlock(harmonizedBlockName, () -> new LodestoneDirectionalBlock(makeStorageBlockProperties(true)));
    }

    public String getId() {
        return id;
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.addItems(ore, deepslateOre, derealizedStorageBlock, harmonizedStorageBlock, derealizedMetal, harmonizedMetal, fracturedImpetus, impetus, node);
    }

    public LodestoneBlockProperties makeOreProperties(boolean isDeepslate) {
        return MalumOreBlockProperties.ORE_PROPERTIES(isDeepslate)
                .mapColor(MapColor.TERRACOTTA_GRAY)
                .addTag(Tags.Blocks.ORE_RATES_SINGULAR)
                .strength(isDeepslate ? 5.0f : 3.0F, 2.0F)
                .sound(isDeepslate ? deepslateOreSound : oreSound);
    }

    public LodestoneBlockProperties makeStorageBlockProperties(boolean isHarmonized) {
        return MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(isHarmonized ? harmonizedBlockSound : derealizedBlockSound, DyeColor.GRAY)
                .addTag(BEACON_BASE_BLOCKS)
                .needsPickaxe()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 3.0F);
    }

    public BlockBlockItemHolder<Block, BlockItem> registerBlock(String name, Supplier<Block> block) {
        return MalumContent.registerBlock(name, block);
    }

    protected TagKey<Block> createOreTag(boolean isDeepslate) {
        var path = (isDeepslate ? "ores/deepslate/" : "ores/");
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path + id));
    }

    protected TagKey<Item> createNuggetTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/" + id));
    }

    protected TagKey<Item> createIngotTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/" + id));
    }

    public TagKey<Block> getOreTag() {
        return oreTag;
    }

    public TagKey<Item> getNuggetTag() {
        return nuggetTag;
    }

    public TagKey<Item> getIngotTag() {
        return ingotTag;
    }

    public DeferredItem<Item> getImpetus() {
        return impetus;
    }

    public DeferredItem<Item> getFracturedImpetus() {
        return fracturedImpetus;
    }

    public DeferredItem<Item> getNode() {
        return node;
    }

    public DeferredItem<Item> getDerealizedMetal() {
        return derealizedMetal;
    }

    public DeferredItem<Item> getHarmonizedMetal() {
        return harmonizedMetal;
    }

    public MalumBlockSoundType getOreSound() {
        return oreSound;
    }

    public MalumBlockSoundType getDeepslateOreSound() {
        return deepslateOreSound;
    }

    public MalumBlockSoundType getDerealizedBlockSound() {
        return derealizedBlockSound;
    }

    public MalumBlockSoundType getHarmonizedBlockSound() {
        return harmonizedBlockSound;
    }

    public BlockBlockItemHolder<Block, BlockItem> getOre() {
        return ore;
    }

    public BlockBlockItemHolder<Block, BlockItem> getDeepslateOre() {
        return deepslateOre;
    }

    public BlockBlockItemHolder<Block, BlockItem> getDerealizedStorageBlock() {
        return derealizedStorageBlock;
    }

    public BlockBlockItemHolder<Block, BlockItem> getHarmonizedStorageBlock() {
        return harmonizedStorageBlock;
    }

}
