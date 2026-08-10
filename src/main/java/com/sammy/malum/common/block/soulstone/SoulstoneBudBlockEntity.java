package com.sammy.malum.common.block.soulstone;

import com.sammy.malum.common.data.component.soulstone.*;
import com.sammy.malum.common.item.ether.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.nbt.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import org.apache.commons.lang3.mutable.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.modules.core.easing.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.rendering.particle.standard.builder.*;
import team.lodestar.lodestone.modules.rendering.particle.standard.data.*;
import team.lodestar.lodestone.modules.rendering.particle.standard.data.color.*;

import java.awt.*;

@SuppressWarnings("NullableProblems")
public class SoulstoneBudBlockEntity extends LodestoneBlockEntity {

    public SoulstoneBudDataComponent budData = SoulstoneBudDataComponent.DEFAULT;

    public SoulstoneBudBlockEntity(LodestoneBlockEntityType<? extends SoulstoneBudBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SoulstoneBudBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.SOULSTONE_BUD.get(), pos, state);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(MalumDataComponents.SOULSTONE_BUD_DATA, budData);
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        budData = componentInput.getOrDefault(MalumDataComponents.SOULSTONE_BUD_DATA, budData);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("budData");
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        budData = SoulstoneBudDataComponent.CODEC.parse(NbtOps.INSTANCE, tag.get("budData")).result().orElse(budData);
        super.loadAdditional(tag, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (budData != null) {
            tag.put("budData", SoulstoneBudDataComponent.CODEC.encodeStart(NbtOps.INSTANCE, budData).getOrThrow());
        }
        super.saveAdditional(tag, registries);
    }
}