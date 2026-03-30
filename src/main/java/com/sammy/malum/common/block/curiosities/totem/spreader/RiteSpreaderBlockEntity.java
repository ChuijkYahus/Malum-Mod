package com.sammy.malum.common.block.curiosities.totem.spreader;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;

public class RiteSpreaderBlockEntity extends LodestoneBlockEntity implements RiteSparkInteractable {

    public RiteSpreaderBlockEntity(LodestoneBlockEntityType<? extends RiteSpreaderBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RiteSpreaderBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RITE_SPREADER.get(), pos, state);
    }

    @Override
    public void travel(ServerLevel level, BlockRiteEffectActivator spark) {
        var direction = getBlockState().getValue(RiteSpreaderBlock.FACING);
        var entity = new BlockRiteEffectWaveActivator(level, spark.getEffect(), worldPosition, direction);
        entity.setSpirit(spark.getSpiritType());
        var attributes = entity.attributes;
        attributes.copyFrom(spark.attributes);
        attributes.getSpeed().modify(2);
        attributes.getDistance().setTier(0);
        level.addFreshEntity(entity);
    }

    @Override
    public int getTravelCost(ServerLevel level, BlockRiteEffectActivator spark) {
        return Mth.floor(16 / spark.attributes.getPotency().getValue());
    }
}