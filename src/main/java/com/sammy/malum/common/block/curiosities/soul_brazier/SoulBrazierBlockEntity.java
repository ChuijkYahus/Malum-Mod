package com.sammy.malum.common.block.curiosities.soul_brazier;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

import javax.annotation.*;
import java.util.*;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

public class SoulBrazierBlockEntity extends LodestoneBlockEntity {

    public SoulBrazierBlockEntity(BlockEntityType<? extends SoulBrazierBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SoulBrazierBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.SOUL_BRAZIER.get(), pos, state);
    }
}