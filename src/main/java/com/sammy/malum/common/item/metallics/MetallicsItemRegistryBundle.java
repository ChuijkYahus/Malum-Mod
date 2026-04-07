package com.sammy.malum.common.item.metallics;

import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.block.properties.MalumOreBlockProperties;
import com.sammy.malum.registry.common.block.properties.MalumStorageBlockProperties;
import com.sammy.malum.registry.common.item.MalumItems;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import net.minecraft.resources.ResourceLocation;
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
import net.neoforged.neoforge.registries.DeferredHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneDirectionalBlock;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS;

public class MetallicsItemRegistryBundle {

    public static List<MetallicsItemRegistryBundle> getMalumMetallics() {
        return List.of(MalumItems.IRON_METALLICS,
                MalumItems.COPPER_METALLICS,
                MalumItems.GOLD_METALLICS,
                MalumItems.ZINC_METALLICS,
                MalumItems.LEAD_METALLICS,
                MalumItems.SILVER_METALLICS,
                MalumItems.ALUMINIUM_METALLICS,
                MalumItems.NICKEL_METALLICS);
    }

    protected final String id;

    protected final TagKey<Item> nuggetTag;
    protected final TagKey<Item> ingotTag;

    protected final DeferredHolder<Item, Item> impetus;
    protected final DeferredHolder<Item, Item> fracturedImpetus;
    protected final DeferredHolder<Item, Item> node;

    protected final DeferredHolder<Item, Item> derealizedMetal;
    protected final DeferredHolder<Item, Item> harmonizedMetal;

    protected final MalumBlockSoundType oreSound;
    protected final MalumBlockSoundType deepslateOreSound;

    protected final MalumBlockSoundType derealizedBlockSound;
    protected final MalumBlockSoundType harmonizedBlockSound;

    protected final DeferredHolder<Block, Block> ore;
    protected final DeferredHolder<Block, Block> deepslateOre;
    protected final DeferredHolder<Block, Block> derealizedStorageBlock;
    protected final DeferredHolder<Block, Block> harmonizedStorageBlock;

    protected final DeferredHolder<Item, Item> oreItem;
    protected final DeferredHolder<Item, Item> deepslateOreItem;
    protected final DeferredHolder<Item, Item> derealizedStorageBlockItem;
    protected final DeferredHolder<Item, Item> harmonizedStorageBlockItem;

    public MetallicsItemRegistryBundle(String id) {
        this.id = id;

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

        impetus = MalumItems.register(impetusName, MalumItems::IMPETUS_PROPERTIES, MetalImpetusItem::new);
        fracturedImpetus = MalumItems.register(fracturedImpetusName, MalumItems::IMPETUS_PROPERTIES, FracturedMetalImpetusItem::new);
        node = MalumItems.register(nodeName, MalumItems::IMPETUS_PROPERTIES, MetalNodeItem::new);

        derealizedMetal = MalumItems.register(derealized, MalumItems::DEFAULT_PROPERTIES, Item::new);
        harmonizedMetal = MalumItems.register(harmonized, MalumItems::DEFAULT_PROPERTIES, Item::new);

        oreSound = new MalumBlockSoundType(oreName);
        deepslateOreSound = new MalumBlockSoundType(deepslateOreName);
        derealizedBlockSound = new MalumBlockSoundType(derealizedBlockName);
        harmonizedBlockSound = new MalumBlockSoundType(harmonizedBlockName);

        ore = MalumBlocks.BLOCKS.register(oreName, () -> new DropExperienceBlock(UniformInt.of(2, 4), makeOreProperties(false)));
        deepslateOre = MalumBlocks.BLOCKS.register(deepslateOreName, () -> new DropExperienceBlock(UniformInt.of(3, 6), makeOreProperties(true)));
        derealizedStorageBlock = registerBlock(derealizedBlockName, () -> new LodestoneDirectionalBlock(makeStorageBlockProperties(false)));
        harmonizedStorageBlock = registerBlock(harmonizedBlockName, () -> new LodestoneDirectionalBlock(makeStorageBlockProperties(true)));

        oreItem = MalumItems.register(oreName, MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(ore.get(), p));
        deepslateOreItem = MalumItems.register(deepslateOreName, MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(deepslateOre.get(), p));
        derealizedStorageBlockItem = MalumItems.register(derealizedBlockName, MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(derealizedStorageBlock.get(), p));
        harmonizedStorageBlockItem = MalumItems.register(harmonizedBlockName, MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(harmonizedStorageBlock.get(), p));
    }

    public String getId() {
        return id;
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.addItems(oreItem, deepslateOreItem, derealizedStorageBlockItem, harmonizedStorageBlockItem, derealizedMetal, harmonizedMetal, fracturedImpetus, impetus, node);
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

    public DeferredHolder<Block, Block> registerBlock(String name, Supplier<Block> block) {
        return MalumBlocks.BLOCKS.register(name, block);
    }

    protected TagKey<Item> createNuggetTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/" + id));
    }

    protected TagKey<Item> createIngotTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/" + id));
    }

    public TagKey<Item> getNuggetTag() {
        return nuggetTag;
    }

    public TagKey<Item> getIngotTag() {
        return ingotTag;
    }

    public DeferredHolder<Item, Item> getImpetus() {
        return impetus;
    }

    public DeferredHolder<Item, Item> getFracturedImpetus() {
        return fracturedImpetus;
    }

    public DeferredHolder<Item, Item> getNode() {
        return node;
    }

    public DeferredHolder<Item, Item> getDerealizedMetal() {
        return derealizedMetal;
    }

    public DeferredHolder<Item, Item> getHarmonizedMetal() {
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

    public DeferredHolder<Block, Block> getOre() {
        return ore;
    }

    public DeferredHolder<Block, Block> getDeepslateOre() {
        return deepslateOre;
    }

    public DeferredHolder<Block, Block> getDerealizedStorageBlock() {
        return derealizedStorageBlock;
    }

    public DeferredHolder<Block, Block> getHarmonizedStorageBlock() {
        return harmonizedStorageBlock;
    }

    public DeferredHolder<Item, Item> getOreItem() {
        return oreItem;
    }

    public DeferredHolder<Item, Item> getDeepslateOreItem() {
        return deepslateOreItem;
    }

    public DeferredHolder<Item, Item> getDerealizedStorageBlockItem() {
        return derealizedStorageBlockItem;
    }

    public DeferredHolder<Item, Item> getHarmonizedStorageBlockItem() {
        return harmonizedStorageBlockItem;
    }
}
