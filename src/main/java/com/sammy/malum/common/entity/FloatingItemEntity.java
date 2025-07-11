package com.sammy.malum.common.entity;

import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.nbt.*;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.Vec3;

public abstract class FloatingItemEntity extends FloatingEntity {

    protected static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(FloatingItemEntity.class, EntityDataSerializers.ITEM_STACK);
    protected static final EntityDataAccessor<String> DATA_SPIRIT_GLOW = SynchedEntityData.defineId(FloatingItemEntity.class, EntityDataSerializers.STRING);

    public FloatingItemEntity(EntityType<? extends FloatingItemEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_ITEM_STACK, ItemStack.EMPTY);
        builder.define(DATA_SPIRIT_GLOW, MalumSpiritTypes.ARCANE_SPIRIT.getRegistryName().toString());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        ItemStack item = getItem();
        if (!item.isEmpty()) {
            pCompound.put("item", item.save(this.registryAccess()));
        }

        var spirit = getSpiritType();
        if (spirit != null) {
            pCompound.putString("spirit", spirit.getRegistryName().toString());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setItem(ItemStack.parse(registryAccess(), pCompound.getCompound("item")).orElse(ItemStack.EMPTY));
        setSpirit(pCompound.getString("spirit"));
    }

    @Override
    public void tick() {
        super.tick();
        if (level() instanceof ServerLevel level) {
            if (age >= 40) {
                if (getDestination() != null && getDestination().isValid(level)) {
                    return;
                }
                var clipResult = level.clip(new ClipContext(position(), position().add(getDeltaMovement()), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
                if (clipResult.isInside()) {
                    Vec3 itemPos = position().subtract(getDeltaMovement().scale(0.75f));
                    ItemEntity entity = new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, getItem());
                    entity.setDeltaMovement(getDeltaMovement().scale(-0.4f));
                    level.addFreshEntity(entity);
                    discard();
                }
            }
        }
    }

    public ItemStack getItem() {
        return getEntityData().get(DATA_ITEM_STACK);
    }

    public void setItem(ItemStack pStack) {
        this.getEntityData().set(DATA_ITEM_STACK, pStack);
    }

    public SpiritArcanaType getSpiritType() {
        return SpiritHolder.getSpiritType(getEntityData().get(DATA_SPIRIT_GLOW)).orElse(MalumSpiritTypes.ARCANE_SPIRIT.get());
    }

    public void setSpirit(SpiritArcanaType spirit) {
        setSpirit(spirit.getRegistryName().toString());
    }

    public void setSpirit(String spirit) {
        getEntityData().set(DATA_SPIRIT_GLOW, spirit);
    }
}