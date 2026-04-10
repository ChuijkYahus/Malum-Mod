package com.sammy.malum.registry.common.util;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.block.decor.ColumnBlock;
import com.sammy.malum.common.block.storage.pedestal.ItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneStairBlock;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;

public class RockBlockSet {

    private final String id;

    private String name(String suffix) {
        return suffix.replace("rock", id + "_rock");
    }

    protected final TagKey<Block> blocksTag;
    protected final TagKey<Block> slabsTag;
    protected final TagKey<Block> stairsTag;
    protected final TagKey<Block> wallsTag;

    protected final BlockBlockItemHolder<Block, BlockItem> rock;
    protected final BlockBlockItemHolder<Block, BlockItem> rockSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> rockStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> rockWall;

    protected final BlockBlockItemHolder<Block, BlockItem> polishedRock;
    protected final BlockBlockItemHolder<Block, BlockItem> polishedRockSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> polishedRockStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> polishedRockWall;

    protected final BlockBlockItemHolder<Block, BlockItem> bricks;
    protected final BlockBlockItemHolder<Block, BlockItem> bricksSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> bricksStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> bricksWall;

    protected final BlockBlockItemHolder<Block, BlockItem> tiles;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesWall;

    protected final BlockBlockItemHolder<Block, BlockItem> mosaic;
    protected final BlockBlockItemHolder<Block, BlockItem> mosaicSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> mosaicStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> mosaicWall;

    protected final BlockBlockItemHolder<Block, BlockItem> column;
    protected final BlockBlockItemHolder<Block, BlockItem> altar;
    protected final BlockBlockItemHolder<Block, BlockItem> chiseled;
    protected final BlockBlockItemHolder<Block, BlockItem> cut;

    protected final BlockBlockItemHolder<Block, BlockItem> button;
    protected final BlockBlockItemHolder<Block, BlockItem> pressurePlate;

    protected final BlockBlockItemHolder<Block, BlockItem> itemStand;
    protected final BlockBlockItemHolder<Block, BlockItem> itemPedestal;


    public RockBlockSet(String id,
                        Supplier<LodestoneBlockProperties> rockProperties,
                        Supplier<LodestoneBlockProperties> brickProperties,
                        Supplier<LodestoneBlockProperties> chiseledProperties) {
        this.id = id;

        blocksTag = createTag("blocks");
        slabsTag = createTag("slabs");
        stairsTag = createTag("stairs");
        wallsTag = createTag("walls");

        rock = MalumContent.registerBlock(name("rock"),
                () -> new Block(rockProperties.get().addTag(blocksTag)));
        rockSlab = MalumContent.registerBlock(name("rock_slab"),
                () -> new SlabBlock(rockProperties.get().addTags(slabsTag)));
        rockStairs = MalumContent.registerBlock(name("rock_stairs"),
                () -> new LodestoneStairBlock(rockProperties.get().addTag(stairsTag)));
        rockWall = MalumContent.registerBlock(name("rock_wall"),
                () -> new WallBlock(rockProperties.get().addTags(wallsTag)));

        polishedRock = MalumContent.registerBlock(name("polished_rock"),
                () -> new Block(rockProperties.get().addTag(blocksTag)));
        polishedRockStairs = MalumContent.registerBlock(name("polished_rock_stairs"),
                () -> new LodestoneStairBlock(rockProperties.get().addTag(stairsTag)));
        polishedRockSlab = MalumContent.registerBlock(name("polished_rock_slab"),
                () -> new SlabBlock(rockProperties.get().addTags(slabsTag)));
        polishedRockWall = MalumContent.registerBlock(name("polished_rock_wall"),
                () -> new WallBlock(rockProperties.get().addTags(wallsTag)));

        bricks = MalumContent.registerBlock(name("rock_bricks"),
                () -> new Block(brickProperties.get().addTag(blocksTag)));
        bricksStairs = MalumContent.registerBlock(name("rock_bricks_stairs"),
                () -> new LodestoneStairBlock(brickProperties.get().addTag(stairsTag)));
        bricksSlab = MalumContent.registerBlock(name("rock_bricks_slab"),
                () -> new SlabBlock(brickProperties.get().addTags(slabsTag)));
        bricksWall = MalumContent.registerBlock(name("rock_bricks_wall"),
                () -> new WallBlock(brickProperties.get().addTags(wallsTag)));

        tiles = MalumContent.registerBlock(name("rock_tiles"),
                () -> new Block(brickProperties.get().addTag(blocksTag)));
        tilesStairs = MalumContent.registerBlock(name("rock_tiles_stairs"),
                () -> new LodestoneStairBlock(brickProperties.get().addTag(stairsTag)));
        tilesSlab = MalumContent.registerBlock(name("rock_tiles_slab"),
                () -> new SlabBlock(brickProperties.get().addTags(slabsTag)));
        tilesWall = MalumContent.registerBlock(name("rock_tiles_wall"),
                () -> new WallBlock(brickProperties.get().addTags(wallsTag)));

        mosaic = MalumContent.registerBlock(name("rock_mosaic"),
                () -> new Block(brickProperties.get().addTag(blocksTag)));
        mosaicStairs = MalumContent.registerBlock(name("rock_mosaic_stairs"),
                () -> new LodestoneStairBlock(brickProperties.get().addTag(stairsTag)));
        mosaicSlab = MalumContent.registerBlock(name("rock_mosaic_slab"),
                () -> new SlabBlock(brickProperties.get().addTags(slabsTag)));
        mosaicWall = MalumContent.registerBlock(name("rock_mosaic_wall"),
                () -> new WallBlock(brickProperties.get().addTags(wallsTag)));


        column = MalumContent.registerBlock(name("rock_column"),
                () -> new ColumnBlock(chiseledProperties.get()));

        altar = MalumContent.registerBlock(name("rock_altar"),
                () -> new Block(chiseledProperties.get().addTag(EIDOLON_ALTAR_BLOCK)));

        chiseled = MalumContent.registerBlock(name("chiseled_rock"),
                () -> new Block(chiseledProperties.get()));

        cut = MalumContent.registerBlock(name("cut_rock"),
                () -> new Block(chiseledProperties.get()));

        button = MalumContent.registerBlock(name("rock_button"),
                () -> new ButtonBlock(BlockSetType.STONE, 20, rockProperties.get().noCollission().addTag(BUTTONS)));

        pressurePlate = MalumContent.registerBlock(name("rock_pressure_plate"),
                () -> new PressurePlateBlock(BlockSetType.STONE, rockProperties.get().noCollission().addTag(PRESSURE_PLATES)));

        itemStand = MalumContent.registerBlock(name("rock_item_stand"),
                () -> new ItemStandBlock<>(rockProperties.get().noOcclusion())
                        .setBlockEntity(MalumBlockEntities.ITEM_STAND));

        itemPedestal = MalumContent.registerBlock(name("rock_item_pedestal"),
                () -> new ItemPedestalBlock<>(rockProperties.get().noOcclusion())
                        .setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));

    }

    protected TagKey<Block> createTag(String tag) {
        return BlockTags.create(MalumMod.malumPath(id + "_rock_" + tag));
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder
                .addItems(
                        rock,
                        polishedRock,
                        bricks,
                        tiles,
                        mosaic
                )
                .addItems(
                        column,
                        altar,
                        cut,
                        chiseled
                ).nextLine()
                .addItems(
                        rockStairs,
                        polishedRockStairs,
                        bricksStairs,
                        tilesStairs,
                        mosaicStairs
                )
                .addItems(
                        itemPedestal,
                        itemStand,
                        pressurePlate,
                        button
                ).nextLine()
                .addItems(
                        rockSlab,
                        polishedRockSlab,
                        bricksSlab,
                        tilesSlab,
                        mosaicSlab
                ).nextLine()
                .addItems(
                        rockWall,
                        polishedRockWall,
                        bricksWall,
                        tilesWall,
                        mosaicWall
                );
    }

    public TagKey<Block> getBlocksTag() {
        return blocksTag;
    }

    public TagKey<Block> getSlabsTag() {
        return slabsTag;
    }

    public TagKey<Block> getStairsTag() {
        return stairsTag;
    }

    public TagKey<Block> getWallsTag() {
        return wallsTag;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRock() {
        return rock;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRockStairs() {
        return rockStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPolishedRock() {
        return polishedRock;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPolishedRockStairs() {
        return polishedRockStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBricks() {
        return bricks;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBricksStairs() {
        return bricksStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTiles() {
        return tiles;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTilesStairs() {
        return tilesStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getMosaic() {
        return mosaic;
    }

    public BlockBlockItemHolder<Block, BlockItem> getMosaicStairs() {
        return mosaicStairs;
    }

    public BlockBlockItemHolder<Block, BlockItem> getColumn() {
        return column;
    }

    public BlockBlockItemHolder<Block, BlockItem> getChiseled() {
        return chiseled;
    }

    public BlockBlockItemHolder<Block, BlockItem> getCut() {
        return cut;
    }

    public BlockBlockItemHolder<Block, BlockItem> getAltar() {
        return altar;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRockSlab() {
        return rockSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPolishedRockSlab() {
        return polishedRockSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBricksSlab() {
        return bricksSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTilesSlab() {
        return tilesSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getMosaicSlab() {
        return mosaicSlab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getRockWall() {
        return rockWall;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPolishedRockWall() {
        return polishedRockWall;
    }

    public BlockBlockItemHolder<Block, BlockItem> getBricksWall() {
        return bricksWall;
    }

    public BlockBlockItemHolder<Block, BlockItem> getTilesWall() {
        return tilesWall;
    }

    public BlockBlockItemHolder<Block, BlockItem> getMosaicWall() {
        return mosaicWall;
    }

    public BlockBlockItemHolder<Block, BlockItem> getButton() {
        return button;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPressurePlate() {
        return pressurePlate;
    }

    public BlockBlockItemHolder<Block, BlockItem> getItemStand() {
        return itemStand;
    }

    public BlockBlockItemHolder<Block, BlockItem> getItemPedestal() {
        return itemPedestal;
    }
}