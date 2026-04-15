package com.sammy.malum.registry.common.util;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.block.curiosities.decor.ColumnBlock;
import com.sammy.malum.common.block.storage.pedestal.ItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;

public class RockBlockSet {

    private final String id;

    public String name(String name) {
        return name.replace("%s", id);
    }

    public final TagKey<Block> blocksTag;
    public final TagKey<Block> stairsTag;
    public final TagKey<Block> slabsTag;
    public final TagKey<Block> wallsTag;

    public final BlockBundleWithWall rock, polishedRock, bricks;
    public final BlockBundleWithWall tiles, grid, mosaic;

    public final BlockBlockItemHolder<Block, BlockItem> column, altar;

    public final BlockBlockItemHolder<Block, BlockItem> button, pressurePlate;

    public final BlockBlockItemHolder<Block, BlockItem> itemPedestal, itemStand;


    public RockBlockSet(String id,
                        Supplier<LodestoneBlockProperties> rockProperties,
                        Supplier<LodestoneBlockProperties> brickProperties,
                        Supplier<LodestoneBlockProperties> chiseledProperties) {
        this.id = id;

        blocksTag = createTag("%s_blocks");
        stairsTag = createTag("%s_stairs");
        slabsTag = createTag("%s_slabs");
        wallsTag = createTag("%s_walls");

        rock = new BlockBundleWithWall(name("%s"), rockProperties, blocksTag, slabsTag, stairsTag, wallsTag);
        polishedRock = new BlockBundleWithWall(name("polished_%s"), rockProperties, blocksTag, slabsTag, stairsTag, wallsTag);
        bricks = new BlockBundleWithWall(name("%s_bricks"), brickProperties, blocksTag, slabsTag, stairsTag, wallsTag);
        tiles = new BlockBundleWithWall(name("%s_tiles"), brickProperties, blocksTag, slabsTag, stairsTag, wallsTag);
        grid = new BlockBundleWithWall(name("%s_grid"), brickProperties, blocksTag, slabsTag, stairsTag, wallsTag);
        mosaic = new BlockBundleWithWall(name("%s_mosaic"), brickProperties, blocksTag, slabsTag, stairsTag, wallsTag);

        column = MalumContent.registerBlock(name("%s_column"), () -> new ColumnBlock(chiseledProperties.get()));
        altar = MalumContent.registerBlock(name("%s_altar"), () -> new Block(chiseledProperties.get().addTag(EIDOLON_ALTAR_BLOCK)));

        button = MalumContent.registerBlock(name("%s_button"), () -> new ButtonBlock(BlockSetType.STONE, 20, rockProperties.get().noCollission().addTag(BUTTONS)));

        pressurePlate = MalumContent.registerBlock(name("%s_pressure_plate"), () -> new PressurePlateBlock(BlockSetType.STONE, rockProperties.get().noCollission().addTag(PRESSURE_PLATES)));

        itemPedestal = MalumContent.registerBlock(name("%s_item_pedestal"), () -> new ItemPedestalBlock<>(rockProperties.get().noOcclusion()));

        itemStand = MalumContent.registerBlock(name("%s_item_stand"), () -> new ItemStandBlock<>(rockProperties.get().noOcclusion()));

    }

    protected TagKey<Block> createTag(String tag) {
        return BlockTags.create(MalumMod.malumPath(name("%s_" + tag)));
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder
                .addItems(
                        rock.block,
                        polishedRock.block,
                        bricks.block,
                        tiles.block,
                        mosaic.block,

                        column,
                        altar
                ).nextLine()
                .addItems(
                        rock.stairs,
                        polishedRock.stairs,
                        bricks.stairs,
                        tiles.stairs,
                        mosaic.stairs,

                        itemPedestal,
                        itemStand
                ).nextLine()
                .addItems(
                        rock.slab,
                        polishedRock.slab,
                        bricks.slab,
                        tiles.slab,
                        mosaic.slab,

                        pressurePlate,
                        button
                ).nextLine()
                .addItems(
                        rock.wall,
                        polishedRock.wall,
                        bricks.wall,
                        tiles.wall,
                        mosaic.wall
                );
    }
}