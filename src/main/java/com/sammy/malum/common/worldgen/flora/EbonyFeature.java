package com.sammy.malum.common.worldgen.flora;

import com.mojang.serialization.Codec;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class EbonyFeature extends Feature<NoneFeatureConfiguration> {
    public EbonyFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }


    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
//        System.out.println("EBONY FEATURE CALLED");
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int radius = random.nextIntBetweenInclusive(3, 6);
        int count = random.nextIntBetweenInclusive(3, 8);

        boolean placed = false;

        for (int i = 0; i < count; i++) {
            int x = origin.getX() + random.nextInt(radius * 2 + 1) - radius;
            int y = origin.getY() + random.nextInt(5) - 2;
            int z = origin.getZ() + random.nextInt(radius * 2 + 1) - radius;

            BlockPos pos = new BlockPos(x, y, z);

            if (canPlace(level, pos)) {
                level.setBlock(
                        pos,
                        MalumContent.Materials.EBONY_SAPLING
                                .get()
                                .defaultBlockState(),
                        3
                );

                ebonifyArea(level, pos, random);

                placed = true;
            }
        }

        return placed;
    }

    private void ebonifyArea(WorldGenLevel level, BlockPos centre, RandomSource random) {
        int radius = random.nextIntBetweenInclusive(4, 7);

        BlockState ebonstone = MalumContent.BlockSets.EBONSTONE.getRaw().block.getDefaultState();
        BlockState stalk = MalumContent.Materials.EBONY_STALK.get().defaultBlockState();

        for (BlockPos pos : BlockPos.betweenClosed(
                centre.offset(-radius, -radius, -radius),
                centre.offset(radius, radius, radius))) {

            double randomRadius = radius + random.nextDouble() * 1.5 - 0.75;

            if (pos.distSqr(centre) > randomRadius * randomRadius) { //this can be more efficient
                continue;
            }

            BlockState state = level.getBlockState(pos);

            if (!state.is(MalumTags.Blocks.EBONY_REPLACEABLE)) {
                continue;
            }

            //replace blocks in tag with ebonstone
            level.setBlock(pos, ebonstone, 3);

            //chance for ebonstone to have stalk
            if (random.nextFloat() >= 0.15f) {
                continue;
            }

            BlockPos stalkPos = pos.above();
            if (!level.getBlockState(stalkPos).isAir() || !stalk.canSurvive(level, stalkPos)) {
                continue;
            }

            level.setBlock(stalkPos, stalk, 3);

            //chance for next segment
            float chance = 0.65f;
            for (int i = 1; i < 4; i++) {
                if (random.nextFloat() >= chance) {
                    break;
                }

                stalkPos = stalkPos.above();

                if (!level.getBlockState(stalkPos).isAir() || !stalk.canSurvive(level, stalkPos)) {
                    break;
                }

                level.setBlock(stalkPos, stalk, 3);

                chance *= 0.5f;
            }
        }

        //TUFF LOOP
        BlockState tuff = Blocks.TUFF.defaultBlockState();
        int outerRadius = radius + 2;

        for (BlockPos pos : BlockPos.betweenClosed(
                centre.offset(-outerRadius, -outerRadius, -outerRadius),
                centre.offset(outerRadius, outerRadius, outerRadius))) {

            double randomRadius = outerRadius + random.nextDouble() * 1.5 - 0.75;

            if (pos.distSqr(centre) > randomRadius * randomRadius) {
                continue;
            }

            BlockState state = level.getBlockState(pos);

            if (!state.is(MalumTags.Blocks.EBONY_REPLACEABLE)) {
                continue;
            }

            if (state.is(ebonstone.getBlock())) {
                continue;
            }

            level.setBlock(pos, tuff, 3);
        }
    }

    private boolean canPlace(WorldGenLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);

        if (!state.isAir()) {
            return false;
        }

        return MalumContent.Materials.EBONY_STALK
                .get()
                .defaultBlockState()
                .canSurvive(level, pos);
    }
}
