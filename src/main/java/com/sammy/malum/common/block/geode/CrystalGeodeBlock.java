package com.sammy.malum.common.block.geode;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.state.BlockBehaviour.simpleCodec;

public class CrystalGeodeBlock extends Block {

    public static final MapCodec<CrystalGeodeBlock> CODEC = simpleCodec(CrystalGeodeBlock::new);

    @Override
    public MapCodec<? extends CrystalGeodeBlock> codec() {
        return CODEC;
    }

    public CrystalGeodeBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
}