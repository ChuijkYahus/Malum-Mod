package com.sammy.malum.common.entity.soulTag;

import com.sammy.malum.common.item.soulTags.SoulTagItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

import static com.sammy.malum.registry.common.entity.MalumEntityTypes.SOUL_TAG_ENTITY;

public class SoulTagEntity extends Entity {

    private static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(SoulTagEntity.class, EntityDataSerializers.ITEM_STACK);

    private static final int FLOAT_TIME = 30;

    private int age;

    private UUID targetUUID;

    public SoulTagEntity(Level level) {
        super(
                SOUL_TAG_ENTITY.get(),
                level
        );
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(ITEM, ItemStack.EMPTY);
    }

    public ItemStack getItem() {
        return entityData.get(ITEM);
    }

    public void setItem(ItemStack stack) {
        entityData.set(ITEM, stack.copy());
    }

    public UUID getTargetUUID() {
        return targetUUID;
    }

    public void setTargetUUID(UUID targetUUID) {
        this.targetUUID = targetUUID;
    }

    @Override
    public void tick() {
        super.tick();

        age++;

        setNoGravity(true);

        if (age < FLOAT_TIME) {

            Vec3 motion = getDeltaMovement();

            setDeltaMovement(
                    motion.scale(0.92D)
            );

        } else {
            setDeltaMovement(Vec3.ZERO);
        }
    }
    public void createSoulTag(LivingEntity target) {
        if (level().isClientSide()) {
            return;
        }

        ItemStack stack = getItem().copy();

        UUID uuid = target.getUUID();
        Component name = target.getDisplayName();

        // Store on the entity.
        setTargetUUID(uuid);

        // Store on the resulting item.
        SoulTagItem.setTarget(stack, uuid);

        SoulTagItem.setTargetName(stack, name);

        ItemEntity itemEntity = new ItemEntity(level(), getX(), getY(), getZ(), stack);

        itemEntity.setPickUpDelay(10);

        level().addFreshEntity(itemEntity);

        discard();
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {

        tag.put("Item", getItem().save(registryAccess()));

        tag.putInt("Age",age);

        if (targetUUID != null) {
            tag.putUUID("TargetUUID", targetUUID);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {

        if (tag.contains("Item")) {
            ItemStack.parse(registryAccess(), tag.getCompound("Item")).ifPresent(this::setItem);
        }

        age = tag.getInt("Age");

        if (tag.hasUUID("TargetUUID")) {
            targetUUID = tag.getUUID("TargetUUID");
        }
    }
}