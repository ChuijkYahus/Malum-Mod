package com.sammy.malum.common.entity;

import com.sammy.malum.registry.common.entity.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import com.mojang.logging.LogUtils;

import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.minecraft.CrashReportCategory;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.DimensionTransition;
import org.slf4j.Logger;

public class AscendingBlockEntity extends Entity {
    private static final Logger LOGGER = LogUtils.getLogger();
    private BlockState blockState;
    public int time;
    public boolean dropItem;
    private boolean cancelDrop;
    private boolean hurtEntities;
    private int fallDamageMax;
    private float maxRiseTicks;
    private float fallDamagePerDistance;
    @Nullable
    public CompoundTag blockData;
    public boolean forceTickAfterTeleportToDuplicate;
    static final EntityDataAccessor<BlockPos> DATA_START_POS =
            SynchedEntityData.defineId(AscendingBlockEntity.class, EntityDataSerializers.BLOCK_POS);


    public AscendingBlockEntity(Level level) {
        super(MalumEntities.ASCENDING_BLOCK.get(), level);
        this.blockState = Blocks.SAND.defaultBlockState();
        this.dropItem = true;
        this.fallDamageMax = 40;
//        this.maxRiseTicks = 200;
    }

    private AscendingBlockEntity(Level level, double x, double y, double z, BlockState state, float lifetime) {
        this(level);
        this.maxRiseTicks = lifetime;
        this.blockState = state;
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setStartPos(this.blockPosition());
    }



    public static AscendingBlockEntity rise(Level level, BlockPos pos, BlockState blockState, float lifetime) {
        AscendingBlockEntity fallingblockentity = new AscendingBlockEntity(level, (double) pos.getX() + (double) 0.5F, (double) pos.getY(), (double) pos.getZ() + (double) 0.5F, blockState.hasProperty(BlockStateProperties.WATERLOGGED) ? (BlockState) blockState.setValue(BlockStateProperties.WATERLOGGED, false) : blockState, lifetime);
        level.setBlock(pos, blockState.getFluidState().createLegacyBlock(), 3);
        level.addFreshEntity(fallingblockentity);
        return fallingblockentity;
    }

    public boolean isAttackable() {
        return false;
    }

    public void setStartPos(BlockPos startPos) {
        this.entityData.set(DATA_START_POS, startPos);
    }

    public BlockPos getStartPos() {
        return (BlockPos) this.entityData.get(DATA_START_POS);
    }

    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_START_POS, BlockPos.ZERO);
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    protected double getDefaultGravity() {
        return -0.02; //renderer doesn't like going slower than this
    }

    public void tick() {

        if (this.blockState.isAir()) {
            this.discard();
        } else {
            if (!this.level().isClientSide()) { //Update client-side position
            ((ServerLevel) this.level()).getChunkSource().broadcast(this, new ClientboundSetEntityMotionPacket(this));
            ((ServerLevel) this.level()).getChunkSource().broadcast(this, new ClientboundTeleportEntityPacket(this)); // <-- sends exact pos
        }

            ++this.time;
            this.applyGravity();
            //Set custom gravity motion
            double grav = this.getDefaultGravity();
            if (grav != (double) 0.0F) {
                this.setDeltaMovement(this.getDeltaMovement().add((double) 0.0F, -grav, (double) 0.0F));
            }
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.handlePortal();

            //Check for ceiling
            BlockPos posAbove = this.blockPosition().above();
            if (!this.level().getBlockState(posAbove).isAir()) {
                placeBlockAndDiscard(this.blockPosition());
                return;
            }

            //Check for age
            if (this.time >= this.maxRiseTicks) {
                placeBlockAndDiscard(this.blockPosition());
            }

            //Dampen movement slightly
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98));

        }

    }


    private void placeBlockAndDiscard(BlockPos pos) {
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        //From falling block class
        if (!serverLevel.setBlock(pos, this.getBlockState(), 3)) {
            this.spawnAtLocation(this.getBlockState().getBlock());
        }

        this.discard();
        serverLevel.gameEvent(this, GameEvent.BLOCK_PLACE, pos);
    }

    //Probably don't need this, but is funny - From falling block class
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        if (!this.hurtEntities) {
            return false;
        } else {
            int i = Mth.ceil(fallDistance - 1.0F);
            if (i < 0) {
                return false;
            } else {
                Predicate<Entity> predicate = EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
                Block var8 = this.blockState.getBlock();
                DamageSource var10000;
                if (var8 instanceof Fallable) {
                    Fallable fallable = (Fallable) var8;
                    var10000 = fallable.getFallDamageSource(this);
                } else {
                    var10000 = this.damageSources().fallingBlock(this);
                }

                DamageSource damagesource = var10000;
                float f = (float) Math.min(Mth.floor((float) i * this.fallDamagePerDistance), this.fallDamageMax);
                this.level().getEntities(this, this.getBoundingBox(), predicate).forEach((p_149649_) -> p_149649_.hurt(damagesource, f));
                boolean flag = this.blockState.is(BlockTags.ANVIL);
                if (flag && f > 0.0F && this.random.nextFloat() < 0.05F + (float) i * 0.05F) {
                    BlockState blockstate = AnvilBlock.damage(this.blockState);
                    if (blockstate == null) {
                        this.cancelDrop = true;
                    } else {
                        this.blockState = blockstate;
                    }
                }

                return false;
            }
        }
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.put("BlockState", NbtUtils.writeBlockState(this.blockState));
        compound.putInt("Time", this.time);
        compound.putBoolean("DropItem", this.dropItem);
        compound.putBoolean("HurtEntities", this.hurtEntities);
        compound.putFloat("FallHurtAmount", this.fallDamagePerDistance);
        compound.putInt("FallHurtMax", this.fallDamageMax);
        if (this.blockData != null) {
            compound.put("TileEntityData", this.blockData);
        }

        compound.putBoolean("CancelDrop", this.cancelDrop);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.blockState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound("BlockState"));
        this.time = compound.getInt("Time");
        if (compound.contains("HurtEntities", 99)) {
            this.hurtEntities = compound.getBoolean("HurtEntities");
            this.fallDamagePerDistance = compound.getFloat("FallHurtAmount");
            this.fallDamageMax = compound.getInt("FallHurtMax");
        } else if (this.blockState.is(BlockTags.ANVIL)) {
            this.hurtEntities = true;
        }

        if (compound.contains("DropItem", 99)) {
            this.dropItem = compound.getBoolean("DropItem");
        }

        if (compound.contains("TileEntityData", 10)) {
            this.blockData = compound.getCompound("TileEntityData").copy();
        }

        this.cancelDrop = compound.getBoolean("CancelDrop");
        if (this.blockState.isAir()) {
            this.blockState = Blocks.SAND.defaultBlockState();
        }

    }

    public void setHurtsEntities(float fallDamagePerDistance, int fallDamageMax) {
        this.hurtEntities = true;
        this.fallDamagePerDistance = fallDamagePerDistance;
        this.fallDamageMax = fallDamageMax;
    }

    public void disableDrop() {
        this.cancelDrop = true;
    }

    public boolean displayFireAnimation() {
        return false;
    }

    public void fillCrashReportCategory(CrashReportCategory category) {
        super.fillCrashReportCategory(category);
        category.setDetail("Immitating BlockState", this.blockState.toString());
    }

    public BlockState getBlockState() {
        return this.blockState;
    }

    protected Component getTypeName() {
        return Component.translatable("entity.minecraft.falling_block_type", new Object[]{this.blockState.getBlock().getName()});
    }

    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity, Block.getId(this.getBlockState()));
    }

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        this.blockState = Block.stateById(packet.getData());
        this.blocksBuilding = true;
        double d0 = packet.getX();
        double d1 = packet.getY();
        double d2 = packet.getZ();
        this.setPos(d0, d1, d2);
        this.setStartPos(this.blockPosition());
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Nullable
    public Entity changeDimension(DimensionTransition transition) {
        ResourceKey<Level> resourcekey = transition.newLevel().dimension();
        ResourceKey<Level> resourcekey1 = this.level().dimension();
        boolean flag = (resourcekey1 == Level.END || resourcekey == Level.END) && resourcekey1 != resourcekey;
        Entity entity = super.changeDimension(transition);
        this.forceTickAfterTeleportToDuplicate = entity != null && flag;
        return entity;
    }


}
