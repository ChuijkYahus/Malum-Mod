package com.sammy.malum.common.entity.activator;

import com.sammy.malum.common.block.curiosities.totem.anchor.*;
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
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import java.util.*;

public class BlockRiteEffectActivatorEntity extends MovingEntity {

    public final TrailPointBuilder trail = TrailPointBuilder.create(6);
    public final TrailPointBuilder longTrail = TrailPointBuilder.create(20);

    protected static final EntityDataAccessor<SpiritArcanaType> DATA_SPIRIT_GLOW = SynchedEntityData.defineId(BlockRiteEffectActivatorEntity.class, MalumEntityDataSerializers.SPIRIT_ARCANA.get());

    protected SpiritRiteBlockEffect effect;

    protected int upgradeSlots;
    public final RiteSparkAttributeData speed = new RiteSparkAttributeData(RiteSparkAttributeDataType.SPEED);
    public final RiteSparkAttributeData potency = new RiteSparkAttributeData(RiteSparkAttributeDataType.POTENCY);
    public final RiteSparkAttributeData distance = new RiteSparkAttributeData(RiteSparkAttributeDataType.MAX_DISTANCE);
    public final RiteSparkAttributeData impact = new RiteSparkAttributeData(RiteSparkAttributeDataType.IMPACT);
    public final List<RiteSparkAttributeData> attributes = List.of(speed, potency, distance, impact);

    protected BlockPos sourcePosition;
    protected BlockPos activationPosition;
    protected Direction movementDirection;
    protected int blockCounter;
    protected int healDuration;
    protected int totalBlocksTraveled;
    protected int age;


    public BlockRiteEffectActivatorEntity(Level level) {
        super(MalumEntities.RITE_BLOCK_EFFECT_ACTIVATOR.get(), level);
    }

    public BlockRiteEffectActivatorEntity(Level level, SpiritRiteBlockEffect effect, BlockPos sourcePosition, Direction movementDirection) {
        this(level);
        this.effect = effect;
        this.sourcePosition = sourcePosition;
        this.activationPosition = sourcePosition;
        this.movementDirection = movementDirection;
        this.upgradeSlots = 8;
        setPos(sourcePosition.getBottomCenter().add(0, 0.05f, 0));
        updateMotion();
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

        pCompound.putInt("upgradeSlots", upgradeSlots);
        attributes.forEach(a -> a.save(pCompound));

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
        pCompound.putInt("healDuration", healDuration);
        pCompound.putInt("blocksTraveled", totalBlocksTraveled);
        pCompound.putInt("age", age);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        setSpirit(SpiritArcanaType.load(pCompound).orElse(MalumSpiritTypes.ARCANE_SPIRIT.get()));
        effect = SpiritRiteEntityEffect.CODEC.load(pCompound, SpiritRiteBlockEffect.class).orElse(null);

        upgradeSlots = pCompound.getInt("upgradeSlots");
        attributes.forEach(a -> a.load(pCompound));

        sourcePosition = NBTHelper.readBlockPos(pCompound.getCompound("sourcePosition"));
        activationPosition = NBTHelper.readBlockPos(pCompound.getCompound("activationPosition"));

        movementDirection = Direction.values()[pCompound.getInt("movementDirection")];
        blockCounter = pCompound.getInt("blockCounter");
        healDuration = pCompound.getInt("healDuration");
        totalBlocksTraveled = pCompound.getInt("blocksTraveled");
        age = pCompound.getInt("age");
    }

    @Override
    public void tick() {
        updateMotion();
        super.tick();
        if (healDuration > 0) {
            healDuration--;
            return;
        }
        if (updatePosition()) {
            if (activationPosition == sourcePosition) {
                return;
            }
            var level = level();
            var affectedPos = activationPosition.below();
            boolean canTriggerEffect = true;
            if (level.getBlockEntity(affectedPos) instanceof RiteAnchorBlockEntity anchor) {
                anchor.travel(this);
                canTriggerEffect = false;
            }
            if (level instanceof ServerLevel serverLevel) {
                if (blockCounter > distance.getValue()+1) {
                    discard();
                    return;
                }
                if (canTriggerEffect) {
                    if (blockCounter <= distance.getValue()) {
                        triggerRiteEffect(serverLevel, affectedPos);
                    }
                    blockCounter++;
                }
                totalBlocksTraveled++;
            }
        }
        if (level().isClientSide) {
            Vec3 position = getPosition(0.5f);
            trail.addTrailPoint(position);
            longTrail.addTrailPoint(position);
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

    public void triggerRiteEffect(ServerLevel level, BlockPos pos) {
        var state = level.getBlockState(pos);
        if (state.is(MalumTags.BlockTags.IS_RITE_IMMUNE)) {
            return;
        }
        effect.applyEffect(level, state, pos);
    }

    public boolean tryUpgrade(Level level) {
        if (upgradeSlots > 0) {
            if (!level.isClientSide) {
                upgradeSlots--;
            }
            return true;
        }
        return false;
    }

    public void startHealing() {
        if (blockCounter > 0) {
            healDuration = Mth.floor((blockCounter * 5) / potency.getValue());
            blockCounter = 0;
        }
    }

    public void updateDirection(Direction direction) {
        this.movementDirection = direction;
        updateMotion();
    }

    public void updateMotion() {
        if (movementDirection != null) {
            float rate = 0.2f * speed.getValue();
            if (healDuration > 0) {
                setDeltaMovement(Vec3.ZERO);
                return;
            }
            setDeltaMovement(new Vec3(movementDirection.getStepX() * rate, movementDirection.getStepY(), movementDirection.getStepZ() * rate));
        }
    }

    public boolean updatePosition() {
        if (movementDirection == null) {
            return false;
        }
        float xOffset = movementDirection.getStepX() * 0.5f;
        float zOffset = movementDirection.getStepZ() * 0.5f;
        int i = Mth.floor(getX() - xOffset);
        int j = Mth.floor(getY());
        int k = Mth.floor(getZ() - zOffset);
        var centered = new BlockPos(i, j, k);
        if (activationPosition == null) {
            activationPosition = centered;
            return true;
        }
        if (i != activationPosition.getX() || j != activationPosition.getY() || k != activationPosition.getZ()) {
            var above = activationPosition.relative(movementDirection).above();
            if (!level().getBlockState(above).canBeReplaced()) {
                discard();
                return false;
            }
            activationPosition = centered;
            setPos(centered.getX() + 0.5f, centered.getY() + 0.05f, centered.getZ() + 0.5f);
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

    public static class RiteSparkAttributeData {

        protected final RiteSparkAttributeDataType type;
        protected int tier;

        public RiteSparkAttributeData(RiteSparkAttributeDataType type, int tier) {
            this.type = type;
            this.tier = tier;
        }

        public RiteSparkAttributeData(RiteSparkAttributeDataType type) {
            this(type, 0);
        }

        public float getValue() {
            return type.getValue(tier);
        }

        public void increase() {
            increase(1);
        }

        public void increase(int amount) {
            if (tier < type.maxTier()-1) {
                tier = Math.min(tier + amount, type.maxTier()-1);
            }
        }

        public void save(CompoundTag compoundTag) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("tier", tier);
            compoundTag.put(type.name, tag);
        }

        public void load(CompoundTag compoundTag) {
            if (compoundTag.contains(type.name)) {
                CompoundTag tag = compoundTag.getCompound(type.name);
                this.tier = tag.getInt("tier");
            }
        }
    }

    public record RiteSparkAttributeDataType(String name, int maxTier, List<Float> valuePerTier) {

        public static RiteSparkAttributeDataType MAX_DISTANCE = new RiteSparkAttributeDataType("max_distance", List.of(8f, 16f, 32f));
        public static RiteSparkAttributeDataType SPEED = new RiteSparkAttributeDataType("speed", List.of(1f, 2f, 4f));
        public static RiteSparkAttributeDataType POTENCY = new RiteSparkAttributeDataType("potency", List.of(1f, 2f, 4f));
        public static RiteSparkAttributeDataType IMPACT = new RiteSparkAttributeDataType("impact", List.of(1f, 2f, 4f));

        public RiteSparkAttributeDataType(String name, List<Float> valuePerTier) {
            this(name, valuePerTier.size(), valuePerTier);
        }

        public float getValue(int tier) {
            if (tier < 0) {
                return valuePerTier.getFirst() / (Mth.abs(tier)+1);
            }
            if (tier >= maxTier) {
                return 0;
            }
            return valuePerTier.get(tier);
        }
    }
}