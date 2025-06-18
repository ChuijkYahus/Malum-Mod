package com.sammy.malum.common.block.curiosities.banner;

import com.sammy.malum.common.data.component.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.common.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.systems.blockentity.*;

import javax.annotation.*;

public class SoulwovenBannerBlockEntity extends LodestoneBlockEntity {

    public MalumSpiritType spirit;
    public SoulwovenBannerPatternDataComponent patternData;
    public boolean intense;

    public SoulwovenBannerBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.SOULWOVEN_BANNER.get(), pos, state);
        this.patternData = SoulwovenBannerPatternDataComponent.DEFAULT;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(MalumDataComponents.SOULWOVEN_BANNER_PATTERN, patternData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        patternData = componentInput.get(MalumDataComponents.SOULWOVEN_BANNER_PATTERN);
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player pPlayer, ItemStack pStack, InteractionHand pHand) {
        if (level instanceof ServerLevel serverLevel) {
            if (pStack.getItem() instanceof SpiritShardItem shardItem) {
                if (spirit != null && shardItem.matches(spirit) && intense) {
                    return super.onUseWithItem(pPlayer, pStack, pHand);
                }
                if (shardItem.matches(MalumSpiritTypes.UMBRAL_SPIRIT)) {
                    return super.onUseWithItem(pPlayer, pStack, pHand);
                }
                if (!pPlayer.isCreative()) {
                    pStack.shrink(1);
                }
                setSpirit(serverLevel, shardItem.unwrapSpirit());
                pPlayer.swing(pHand, true);
            }
            if (pStack.canPerformAction(ItemAbilities.SHEARS_DISARM)) {
                if (spirit != null) {
                    setSpirit(serverLevel, null);
                    pPlayer.swing(pHand, true);
                }
            }
        }
        return super.onUseWithItem(pPlayer, pStack, pHand);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (spirit != null) {
            tag.putString("spirit", spirit.asTag());
        }
        tag.putBoolean("intense", intense);
        patternData.save(tag);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        spirit = SpiritHolder.getSpiritType(pTag).orElse(null);
        intense = pTag.getBoolean("intense");
        patternData = SoulwovenBannerPatternDataComponent.load(pTag);
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("pattern");
    }

    public void setSpirit(ServerLevel level, @Nullable MalumSpiritType spirit) {
        level.playSound(null, worldPosition, MalumSoundEvents.TOTEM_ENGRAVE.get(), SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        level.playSound(null, worldPosition, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1, Mth.nextFloat(level.random, 0.9f, 1.1f));
        if (spirit != null) {
            MalumParticleEffectTypes.APPLY_SOULWOVEN_BANNER_GLOW
                    .createEffect(worldPosition)
                    .color(spirit)
                    .spawn(level);
        }
        else if (this.spirit != null) {
            MalumParticleEffectTypes.REMOVE_SOULWOVEN_BANNER_GLOW
                    .createEffect(worldPosition)
                    .color(this.spirit)
                    .spawn(level);
        }
        intense = this.spirit == spirit;
        this.spirit = spirit;
        BlockStateHelper.updateState(level, worldPosition);
    }
}