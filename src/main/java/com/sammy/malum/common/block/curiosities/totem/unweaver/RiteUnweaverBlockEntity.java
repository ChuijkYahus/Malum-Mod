package com.sammy.malum.common.block.curiosities.totem.unweaver;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.systems.blockentity.*;

public class RiteUnweaverBlockEntity extends LodestoneBlockEntity implements RiteSparkInteractable {

    public RiteUnweaverBlockEntity(BlockEntityType<? extends RiteUnweaverBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RiteUnweaverBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RITE_UNWEAVER.get(), pos, state);
    }

    @Override
    public void travel(ServerLevel level, BlockRiteEffectActivator spark) {
        spark.discard();
        playSound(MalumSoundEvents.SPARK_UNWOVEN.get());
        MalumParticleEffectTypes.RITE_UNWEAVER_EFFECT.createEffect(getBlockPos().above())
                .color(spark.getSpiritType())
                .spawn(level);
    }
}