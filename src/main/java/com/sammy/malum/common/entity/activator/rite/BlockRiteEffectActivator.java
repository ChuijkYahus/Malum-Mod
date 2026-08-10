package com.sammy.malum.common.entity.activator.rite;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import java.util.function.*;

public class BlockRiteEffectActivator extends MovingEntity implements ILociAttributeBearer {

    public final TrailPointBuilder trail = TrailPointBuilder.create(6);
    public final TrailPointBuilder longTrail = TrailPointBuilder.create(20);
    private final NonNullList<ItemStack> storedItems = NonNullList.withSize(27, ItemStack.EMPTY);

    protected static final EntityDataAccessor<SpiritArcanaType> DATA_SPIRIT_GLOW = SynchedEntityData.defineId(BlockRiteEffectActivator.class, MalumEntityDataSerializers.SPIRIT_ARCANA.get());

    protected SpiritRiteBlockEffect effect;
    public final RiteSparkAttributeDataStorage attributes = new RiteSparkAttributeDataStorage();

    protected BlockPos sourcePosition;
    protected BlockPos activationPosition;
    protected Direction movementDirection;
    protected int blockCounter;
    protected int totalBlocksTraveled;
    protected int age;

    protected int healDuration;
    protected int healCounter;
    protected int copyCounter;

    public BlockRiteEffectActivator(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public BlockRiteEffectActivator(Level level) {
        this(MalumEntityTypes.RITE_BLOCK_EFFECT_ACTIVATOR.get(), level);
    }

    public BlockRiteEffectActivator(Level level, SpiritRiteBlockEffect effect, BlockPos sourcePosition, Direction movementDirection) {
        this(MalumEntityTypes.RITE_BLOCK_EFFECT_ACTIVATOR.get(), level, effect, sourcePosition, movementDirection);
    }

    public BlockRiteEffectActivator(EntityType<?> entityType, Level level, SpiritRiteBlockEffect effect, BlockPos sourcePosition, Direction movementDirection) {
        this(entityType, level);
        this.effect = effect;
        this.sourcePosition = sourcePosition;
        this.activationPosition = sourcePosition;
        this.movementDirection = movementDirection;
        setPos(sourcePosition.getBottomCenter().add(0, 0.05f, 0));
        updateMotion();
    }

    @Override
    public RiteSparkAttributeDataStorage getLociAttributes() {
        return attributes;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_SPIRIT_GLOW, MalumSpiritTypes.ARCANE_SPIRIT.get());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        var spirit = getSpiritType();
        if (spirit != null) {
            spirit.save(tag);
        }

        if (effect != null) {
            effect.save(tag);
        }

        attributes.save(tag);

        if (sourcePosition != null) {
            tag.put("sourcePosition", NBTHelper.saveBlockPos(sourcePosition));
        }

        if (activationPosition != null) {
            tag.put("activationPosition", NBTHelper.saveBlockPos(activationPosition));
        }

        if (movementDirection != null) {
            tag.putInt("movementDirection", movementDirection.ordinal());
        }

        tag.putInt("blockCounter", blockCounter);
        tag.putInt("blocksTraveled", totalBlocksTraveled);
        tag.putInt("age", age);

        tag.putInt("healDuration", healDuration);
        tag.putInt("healCounter", healCounter);
        tag.putInt("copyCounter", copyCounter);

        ListTag items = new ListTag();

        for (int i = 0; i < storedItems.size(); i++) {
            ItemStack stack = storedItems.get(i);

            if (!stack.isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                stack.save(level().registryAccess(), itemTag);
                items.add(itemTag);
            }
        }

        tag.put("StoredItems", items);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        setSpirit(
                SpiritArcanaType.load(tag)
                        .orElse(MalumSpiritTypes.ARCANE_SPIRIT.get())
        );

        effect = SpiritRiteEntityEffect.CODEC
                .load(tag, SpiritRiteBlockEffect.class)
                .orElse(null);

        attributes.load(tag);

        sourcePosition = NBTHelper.readBlockPos(
                tag.getCompound("sourcePosition")
        );

        activationPosition = NBTHelper.readBlockPos(
                tag.getCompound("activationPosition")
        );

        movementDirection = Direction.values()[
                tag.getInt("movementDirection")
                ];

        blockCounter = tag.getInt("blockCounter");
        totalBlocksTraveled = tag.getInt("blocksTraveled");
        age = tag.getInt("age");

        healDuration = tag.getInt("healDuration");
        healCounter = tag.getInt("healCounter");
        copyCounter = tag.getInt("copyCounter");

        storedItems.clear();

        ListTag items = tag.getList("StoredItems", Tag.TAG_COMPOUND);

        for (int i = 0; i < items.size(); i++) {
            CompoundTag itemTag = items.getCompound(i);

            int slot = itemTag.getInt("Slot");

            if (slot >= 0 && slot < storedItems.size()) {
                storedItems.set(
                        slot,
                        ItemStack.parse(
                                level().registryAccess(),
                                itemTag
                        ).orElse(ItemStack.EMPTY)
                );
            }
        }
    }

    @Override
    public void tick() {
        updateMotion();
        super.tick();
        if (level() instanceof ServerLevel serverLevel) {
            notifyTotem();
            if (healDuration > 0) {
                healDuration--;
                return;
            }
            if (updatePosition()) {
                if (activationPosition == sourcePosition) {
                    return;
                }
                var level = level();
                var affectedPos = getRiteEffectPosition(activationPosition);
                boolean canTriggerEffect = true;
                if (level.getBlockEntity(affectedPos) instanceof RiteSparkInteractable interactable) {
                    if (canTriggerTravelEffects()) {
                        interactable.travel(serverLevel, this);
                    }
                    canTriggerEffect = false;
                } else if (level.getBlockState(affectedPos).is(MalumTags.Blocks.IS_RITE_IMMUNE)) {
                    canTriggerEffect = false;
                }
                if (canTriggerEffect) {
                    if (blockCounter >= attributes.distance.getValue()) {
                        destroyAndDropItems();
                        return;
                    }
                    if (triggerRiteEffect(serverLevel, affectedPos)) {
                        blockCounter++;
                    }
                }
                totalBlocksTraveled++;
            }
        }
        if (level().isClientSide) {
            addTrailPoints();
            trail.tickTrailPoints();
            longTrail.tickTrailPoints();
            if (level().getGameTime() % 3 == 0L) {
                var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level(), position(), getSpiritType());
                lightSpecs.getBuilder().modifyScaleData(d -> d.multiplyValue(1.5f)).multiplyLifetime(2);
                lightSpecs.getBloomBuilder().multiplyLifetime(2);
                lightSpecs.spawnParticles();
            }
        }
        age++;
    }

    @Override
    public float getFriction() {
        return 1;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    public float getTravelSpeedMultiplier() {
        return 1f;
    }

    public BlockPos getRiteEffectPosition(BlockPos pos) {
        return pos.below();
    }

    public Direction getMovementDirection() {
        return movementDirection;
    }

    public SpiritRiteBlockEffect getEffect() {
        return effect;
    }

    public int getAge() {
        return age;
    }

    public boolean canTriggerTravelEffects() {
        return true;
    }

    public void addTrailPoints() {
        Vec3 position = getPosition(0.5f);
        trail.addTrailPoint(position);
        longTrail.addTrailPoint(position);
    }

    public boolean triggerRiteEffect(ServerLevel level, BlockPos pos) {
        var state = level.getBlockState(pos);
        effect.applyEffect(level, this, state, pos, attributes.getImpact().getValue());
        return true;
    }

    public void upgrade(Function<RiteSparkAttributeDataStorage, RiteSparkAttributeData> target) {
        attributes.upgrade(target);
    }

    public void recoverHealth() {
        if (blockCounter > 0) {
            healCounter++;
            if (healCounter > 4) {
                return;
            }
            healDuration = Mth.floor((blockCounter * 4) / attributes.getPotency().getValue());
            blockCounter = 0;
            updateMotion();
        }
    }

    public void leechHealth() {
        if (blockCounter > 0) {
            healCounter++;
            if (healCounter > 4) {
                return;
            }
            blockCounter = 0;
            for (RiteSparkAttributeData attribute : attributes.getAttributes()) {
                if (attribute.decrease()) {
                    return;
                }
            }
        }
    }

    public void duplicate() {
        if (copyCounter == -1 || copyCounter >= 4) {
            return;
        }
        //The created copy more so takes over where the spark was moving, rather than the copy being one to follow the direction as defined by the anchor
        var doppelganger = new BlockRiteEffectActivator(level(), effect, activationPosition, movementDirection);
        var data = new CompoundTag();
        addAdditionalSaveData(data);
        doppelganger.readAdditionalSaveData(data);
        doppelganger.copyCounter++;
        copyCounter = -1;
        level().addFreshEntity(doppelganger);
    }

    public void notifyTotem() {
        if (sourcePosition != null) {
            if (level().getBlockEntity(sourcePosition) instanceof TotemBaseBlockEntity totemBase) {
                totemBase.receiveSparkUpdate();
            }
        }
    }

    public void updateDirection(Direction direction) {
        this.movementDirection = direction;
        updateMotion();
    }

    public void updateMotion() {
        if (movementDirection != null) {
            float rate = 0.2f * attributes.getSpeed().getValue() * getTravelSpeedMultiplier();
            if (healDuration > 0) {
                setDeltaMovement(Vec3.ZERO);
                return;
            }
            setDeltaMovement(new Vec3(movementDirection.getStepX() * rate, movementDirection.getStepY() * rate, movementDirection.getStepZ() * rate));
        }
    }

    public boolean updatePosition() {
        if (movementDirection == null) {
            return false;
        }
        float xOffset = movementDirection.getStepX() * 0.5f;
        float yOffset = movementDirection.getStepY() * 0.5f;
        float zOffset = movementDirection.getStepZ() * 0.5f;
        int x = Mth.floor(getX() - xOffset);
        int y = Mth.floor(getY() - yOffset);
        int z = Mth.floor(getZ() - zOffset);
        var centered = new BlockPos(x, y, z);
        if (activationPosition == null) {
            activationPosition = centered;
            return true;
        }
        if (x != activationPosition.getX() || y != activationPosition.getY() || z != activationPosition.getZ()) {
            activationPosition = centered;
            return true;
        }
        return false;
    }

    public float getVisualEffectScalar() {
        return Math.min(age / 10f, 1f);
    }

    public SpiritArcanaType getSpiritType() {
        return getEntityData().get(DATA_SPIRIT_GLOW);
    }

    public void setSpirit(SpiritArcanaType spirit) {
        getEntityData().set(DATA_SPIRIT_GLOW, spirit);
    }

    public NonNullList<ItemStack> getStoredItems() {
        return storedItems;
    }

    public boolean addItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        for (int i = 0; i < storedItems.size(); i++) {
            ItemStack existing = storedItems.get(i);

            if (existing.isEmpty()) {
                storedItems.set(i, stack.copy());
                return true;
            }

            if (ItemStack.isSameItemSameComponents(existing, stack)) {
                int space = existing.getMaxStackSize() - existing.getCount();

                if (space > 0) {
                    int amount = Math.min(space, stack.getCount());
                    existing.grow(amount);

                    if (amount == stack.getCount()) {
                        return true;
                    }

                    stack.shrink(amount);
                }
            }
        }

        return stack.isEmpty();
    }
    public void destroyAndDropItems() {
        if (level().isClientSide) {
            discard();
            return;
        }

        for (ItemStack stack : storedItems) {
            if (!stack.isEmpty()) {
                spawnAtLocation(stack.copy());
            }
        }

        storedItems.clear();
        discard();
    }
}