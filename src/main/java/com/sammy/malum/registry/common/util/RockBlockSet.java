package com.sammy.malum.registry.common.util;

import com.sammy.malum.common.block.decor.ColumnBlock;
import com.sammy.malum.common.block.ether.EtherBrazierBlock;
import com.sammy.malum.common.block.ether.EtherCressetBlock;
import com.sammy.malum.common.block.storage.pedestal.ItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.content.block.MalumBlockEntities;
import com.sammy.malum.registry.common.content.block.MalumBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneStairBlock;

import java.util.function.Supplier;

import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;

public class RockBlockSet {

    private final String prefix;

    private String name(String suffix) {
        return prefix + "_" + suffix;
    }

    protected final BlockBlockItemHolder<Block, BlockItem> rock;
    protected final BlockBlockItemHolder<Block, BlockItem> rockStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> polishedRock;
    protected final BlockBlockItemHolder<Block, BlockItem> polishedRockStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> bricks;
    protected final BlockBlockItemHolder<Block, BlockItem> bricksStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> tiles;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesStairs;
    protected final BlockBlockItemHolder<Block, BlockItem> mosaic;
    protected final BlockBlockItemHolder<Block, BlockItem> mosaicStairs;

    protected final BlockBlockItemHolder<Block, BlockItem> column;
    protected final BlockBlockItemHolder<Block, BlockItem> chiseled;
    protected final BlockBlockItemHolder<Block, BlockItem> cut;
    protected final BlockBlockItemHolder<Block, BlockItem> altar;

    protected final BlockBlockItemHolder<Block, BlockItem> slab;
    protected final BlockBlockItemHolder<Block, BlockItem> polishedSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> bricksSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesSlab;
    protected final BlockBlockItemHolder<Block, BlockItem> mosaicSlab;

    protected final BlockBlockItemHolder<Block, BlockItem> wall;
    protected final BlockBlockItemHolder<Block, BlockItem> polishedWall;
    protected final BlockBlockItemHolder<Block, BlockItem> bricksWall;
    protected final BlockBlockItemHolder<Block, BlockItem> tilesWall;
    protected final BlockBlockItemHolder<Block, BlockItem> mosaicWall;

    protected final BlockBlockItemHolder<Block, BlockItem> button;
    protected final BlockBlockItemHolder<Block, BlockItem> pressurePlate;

    protected final BlockBlockItemHolder<Block, BlockItem> itemStand;
    protected final BlockBlockItemHolder<Block, BlockItem> itemPedestal;

    protected final BlockBlockItemHolder<Block, BlockItem> etherBrazier;
    protected final BlockBlockItemHolder<Block, BlockItem> iridescentEtherBrazier;

    protected final BlockBlockItemHolder<Block, BlockItem> etherCresset;
    protected final BlockBlockItemHolder<Block, BlockItem> iridescentEtherCresset;

    public RockBlockSet(String prefix,
                        Supplier<LodestoneBlockProperties> rockProperties,
                        Supplier<LodestoneBlockProperties> brickProperties,
                        Supplier<LodestoneBlockProperties> chiseledProperties,
                        Supplier<LodestoneBlockProperties> brazierProperties,
                        Supplier<LodestoneBlockProperties> cressetProperties) {
        this.prefix = prefix;

        rock = MalumBlocks.registerBlock(name("rock"),
                () -> new Block(rockProperties.get().addTag(TAINTED_ROCK_BLOCKS)));

        rockStairs = MalumBlocks.registerBlock(name("rock_stairs"),
                () -> new LodestoneStairBlock(rockProperties.get().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));

        polishedRock = MalumBlocks.registerBlock(name("polished_rock"),
                () -> new Block(rockProperties.get().addTag(TAINTED_ROCK_BLOCKS)));

        polishedRockStairs = MalumBlocks.registerBlock(name("polished_rock_stairs"),
                () -> new LodestoneStairBlock(rockProperties.get().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));

        bricks = MalumBlocks.registerBlock(name("rock_bricks"),
                () -> new Block(brickProperties.get().addTag(TAINTED_ROCK_BLOCKS)));

        bricksStairs = MalumBlocks.registerBlock(name("rock_bricks_stairs"),
                () -> new LodestoneStairBlock(brickProperties.get().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));

        tiles = MalumBlocks.registerBlock(name("rock_tiles"),
                () -> new Block(brickProperties.get().addTag(TAINTED_ROCK_BLOCKS)));

        tilesStairs = MalumBlocks.registerBlock(name("rock_tiles_stairs"),
                () -> new LodestoneStairBlock(brickProperties.get().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));

        mosaic = MalumBlocks.registerBlock(name("rock_mosaic"),
                () -> new Block(brickProperties.get().addTag(TAINTED_ROCK_BLOCKS)));

        mosaicStairs = MalumBlocks.registerBlock(name("rock_mosaic_stairs"),
                () -> new LodestoneStairBlock(brickProperties.get().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));

        column = MalumBlocks.registerBlock(name("rock_column"),
                () -> new ColumnBlock(chiseledProperties.get()));

        chiseled = MalumBlocks.registerBlock(name("chiseled_rock"),
                () -> new Block(chiseledProperties.get()));

        cut = MalumBlocks.registerBlock(name("cut_rock"),
                () -> new Block(chiseledProperties.get()));

        altar = MalumBlocks.registerBlock(name("rock_altar"),
                () -> new Block(chiseledProperties.get().addTag(EIDOLON_ALTAR_BLOCK)));

        slab = MalumBlocks.registerBlock(name("rock_slab"),
                () -> new SlabBlock(rockProperties.get().addTags(SLABS, TAINTED_ROCK_SLABS)));

        polishedSlab = MalumBlocks.registerBlock(name("polished_rock_slab"),
                () -> new SlabBlock(rockProperties.get().addTags(SLABS, TAINTED_ROCK_SLABS)));

        bricksSlab = MalumBlocks.registerBlock(name("rock_bricks_slab"),
                () -> new SlabBlock(brickProperties.get().addTags(SLABS, TAINTED_ROCK_SLABS)));

        tilesSlab = MalumBlocks.registerBlock(name("rock_tiles_slab"),
                () -> new SlabBlock(brickProperties.get().addTags(SLABS, TAINTED_ROCK_SLABS)));

        mosaicSlab = MalumBlocks.registerBlock(name("rock_mosaic_slab"),
                () -> new SlabBlock(brickProperties.get().addTags(SLABS, TAINTED_ROCK_SLABS)));

        wall = MalumBlocks.registerBlock(name("rock_wall"),
                () -> new WallBlock(rockProperties.get().addTags(WALLS, TAINTED_ROCK_WALLS)));

        polishedWall = MalumBlocks.registerBlock(name("polished_rock_wall"),
                () -> new WallBlock(rockProperties.get().addTags(WALLS, TAINTED_ROCK_WALLS)));

        bricksWall = MalumBlocks.registerBlock(name("rock_bricks_wall"),
                () -> new WallBlock(brickProperties.get().addTags(WALLS, TAINTED_ROCK_WALLS)));

        tilesWall = MalumBlocks.registerBlock(name("rock_tiles_wall"),
                () -> new WallBlock(brickProperties.get().addTags(WALLS, TAINTED_ROCK_WALLS)));

        mosaicWall = MalumBlocks.registerBlock(name("rock_mosaic_wall"),
                () -> new WallBlock(brickProperties.get().addTags(WALLS, TAINTED_ROCK_WALLS)));

        button = MalumBlocks.registerBlock(name("rock_button"),
                () -> new ButtonBlock(BlockSetType.STONE, 20, rockProperties.get().noCollission().addTag(BUTTONS)));

        pressurePlate = MalumBlocks.registerBlock(name("rock_pressure_plate"),
                () -> new PressurePlateBlock(BlockSetType.STONE, rockProperties.get().noCollission().addTag(PRESSURE_PLATES)));

        itemStand = MalumBlocks.registerBlock(name("rock_item_stand"),
                () -> new ItemStandBlock<>(rockProperties.get().noOcclusion())
                        .setBlockEntity(MalumBlockEntities.ITEM_STAND));

        itemPedestal = MalumBlocks.registerBlock(name("rock_item_pedestal"),
                () -> new ItemPedestalBlock<>(rockProperties.get().noOcclusion())
                        .setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));

        etherBrazier = MalumBlocks.registerBlock(name("ether_brazier"),
                () -> new EtherBrazierBlock<>(brazierProperties.get())
                        .setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));

        iridescentEtherBrazier = MalumBlocks.registerBlock(name("iridescent_ether_brazier"),
                () -> new EtherBrazierBlock<>(brazierProperties.get())
                        .setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));

        etherCresset = MalumBlocks.registerBlock(name("ether_cresset"),
                () -> new EtherCressetBlock<>(cressetProperties.get())
                        .setBlockEntity(MalumBlockEntities.ETHER_CRESSET));

        iridescentEtherCresset = MalumBlocks.registerBlock(name("iridescent_ether_cresset"),
                () -> new EtherCressetBlock<>(cressetProperties.get())
                        .setBlockEntity(MalumBlockEntities.ETHER_CRESSET));

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

    public BlockBlockItemHolder<Block, BlockItem> getSlab() {
        return slab;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPolishedSlab() {
        return polishedSlab;
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

    public BlockBlockItemHolder<Block, BlockItem> getWall() {
        return wall;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPolishedWall() {
        return polishedWall;
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

    public BlockBlockItemHolder<Block, BlockItem> getEtherBrazier() {
        return etherBrazier;
    }

    public BlockBlockItemHolder<Block, BlockItem> getIridescentEtherBrazier() {
        return iridescentEtherBrazier;
    }

    public BlockBlockItemHolder<Block, BlockItem> getEtherCresset() {
        return etherCresset;
    }

    public BlockBlockItemHolder<Block, BlockItem> getIridescentEtherCresset() {
        return iridescentEtherCresset;
    }
}