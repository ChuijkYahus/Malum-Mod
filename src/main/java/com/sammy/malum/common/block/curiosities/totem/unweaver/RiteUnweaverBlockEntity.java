package com.sammy.malum.common.block.curiosities.totem.unweaver;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

public class RiteUnweaverBlockEntity extends LodestoneBlockEntity implements RiteSparkInteractable {

    public RiteUnweaverBlockEntity(BlockEntityType<? extends RiteUnweaverBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RiteUnweaverBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RITE_UNWEAVER.get(), pos, state);
    }

    @Override
    public void travel(BlockRiteEffectActivatorEntity spark) {
        spark.discard();
    }
}