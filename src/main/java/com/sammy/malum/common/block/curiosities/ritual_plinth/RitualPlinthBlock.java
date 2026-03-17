package com.sammy.malum.common.block.curiosities.ritual_plinth;

import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import team.lodestar.lodestone.modules.toolkit.block.*;

public class RitualPlinthBlock<T extends RitualPlinthBlockEntity> extends WaterLoggedEntityBlock<T> {

    public RitualPlinthBlock(Properties properties) {
        super(properties);
    }
}