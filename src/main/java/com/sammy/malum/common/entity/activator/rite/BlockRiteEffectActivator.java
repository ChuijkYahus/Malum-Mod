package com.sammy.malum.common.entity.activator.rite;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.*;
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
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import java.util.function.*;

public class BlockRiteEffectActivator extends MovingEntity implements ILociAttributeBearer {

    public final TrailPointBuilder trail = TrailPointBuilder.create(6);
    public final TrailPointBuilder longTrail = TrailPointBuilder.create(20);

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
    public void addAdditionalSaveData(CompoundTag pCompound) {
        var spirit = getSpiritType();
        if (spirit != null) {
            spirit.save(pCompound);
        }
        if (effect != null) {
            effect.save(pCompound);
        }
        attributes.save(pCompound);

        if (sourcePosition != null) {
            pCompound.put("sourcePosition", NBTHelper.saveBlockPos(sourcePosition));
        }
        if (activationPosition != null) {
            pCompound.put("activationPosition", NBTHelper.saveBlockPos(activationPosition));
        }
        if (movementDirection != null) {
            pCompound.putInt("movementDirection", movementDirection.ordinal());
        }
        pCompound.putInt("blockCounter", blockCounter);
        pCompound.putInt("blocksTraveled", totalBlocksTraveled);
        pCompound.putInt("age", age);

        pCompound.putInt("healDuration", healDuration);
        pCompound.putInt("healCounter", healCounter);
        pCompound.putInt("copyCounter", copyCounter);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        setSpirit(SpiritArcanaType.load(pCompound).orElse(MalumSpiritTypes.ARCANE_SPIRIT.get()));
        effect = SpiritRiteEntityEffect.CODEC.load(pCompound, SpiritRiteBlockEffect.class).orElse(null);
        attributes.load(pCompound);

        sourcePosition = NBTHelper.readBlockPos(pCompound.getCompound("sourcePosition"));
        activationPosition = NBTHelper.readBlockPos(pCompound.getCompound("activationPosition"));
        movementDirection = Direction.values()[pCompound.getInt("movementDirection")];

        blockCounter = pCompound.getInt("blockCounter");
        totalBlocksTraveled = pCompound.getInt("blocksTraveled");
        age = pCompound.getInt("age");

        healDuration = pCompound.getInt("healDuration");
        healCounter = pCompound.getInt("healCounter");
        copyCounter = pCompound.getInt("copyCounter");
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
                        discard();
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
}