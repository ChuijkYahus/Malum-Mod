package com.sammy.malum.common.block.curiosities.obelisk.rite_pylon;

import com.sammy.malum.common.block.*;
import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.common.block.curiosities.spirit_altar.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.magic.rite.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.arcana_pylon.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.items.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.toolkit.inventory.*;
import team.lodestar.lodestone.modules.toolkit.multiblock.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

public class ArcanaPylonBlockEntity extends ObeliskCoreBlockEntity implements IAltarAccelerator, IInventoryCapabilityProvider {

    public static final Supplier<MultiBlockStructure> STRUCTURE = () -> (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumBlocks.ARCANA_PYLON_COMPONENT.get().defaultBlockState())));
    private static final IAltarAccelerator.AltarAcceleratorType ARCANA_PYLON = new IAltarAccelerator.AltarAcceleratorType(4, "arcana_pylon");

    private static final HashMap<Holder<SpiritArcanaType>, RiteEffectHolder<? extends SpiritRiteEmpowermentEffect<?>>> RITE_EMPOWERMENT_EFFECTS = new HashMap<>() {{
        put(MalumSpiritTypes.AERIAL_SPIRIT, MalumSpiritRiteEffectTypes.EMPOWER_AERIAL_EFFECTS);
        put(MalumSpiritTypes.AQUEOUS_SPIRIT, MalumSpiritRiteEffectTypes.EMPOWER_AQUEOUS_EFFECTS);
        put(MalumSpiritTypes.EARTHEN_SPIRIT, MalumSpiritRiteEffectTypes.EMPOWER_EARTHEN_EFFECTS);
        put(MalumSpiritTypes.INFERNAL_SPIRIT, MalumSpiritRiteEffectTypes.EMPOWER_INFERNAL_EFFECTS);
    }};


    private static final Vec3 ITEM_OFFSET = new Vec3(0.5f, 2.25f, 0.5f);
    private static final int WARMUP_DURATION = 20;

    public LodestoneItemStackHandler inventory;
    public SpiritArcanaType spirit;
    protected int unspentSpiritFuel;
    protected int visualEffectStrength;
    protected int timer;

    public ArcanaPylonBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.ARCANA_PYLON.get(), STRUCTURE.get(), pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 1).onlySpirits().onContentsChanged(this::updateSpirit).build();
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventory;
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registryLookup) {
        inventory.save(registryLookup, compound);
        if (spirit != null) {
            spirit.save(compound);
        }
        compound.putInt("unspentSpiritFuel", unspentSpiritFuel);
        compound.putInt("visualEffectStrength", visualEffectStrength);
        compound.putInt("timer", timer);
        super.saveAdditional(compound, registryLookup);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        inventory.load(registries, compound);
        spirit = SpiritArcanaType.load(compound).orElse(null);
        unspentSpiritFuel = compound.getInt("unspentSpiritFuel");
        visualEffectStrength = compound.getInt("visualEffectStrength");
        timer = compound.getInt("timer");
        super.loadAdditional(compound, registries);
    }

    @Override
    public AltarAcceleratorType getAcceleratorType() {
        return ARCANA_PYLON;
    }

    @Override
    public float getAcceleration() {
        return 0.5f;
    }

    @Override
    public boolean canAccelerate(SpiritAltarBlockEntity altar) {
        return spirit != null && spirit.matches(MalumSpiritTypes.ARCANE_SPIRIT);
    }

    @Override
    public void completeSpiritInfusion(ServerLevel level, SpiritAltarBlockEntity altar) {
        if (canAccelerate(altar)) {
            spendSpiritFuel(level, 16);
        }
    }

    @Override
    public void addParticles(SpiritAltarBlockEntity altar, SpiritArcanaType activeSpiritType) {
        SpiritAltarParticleEffects.arcanaPylonParticles(this, altar, spirit);
    }

    @Override
    public ItemInteractionResult onUseWithItem(Player pPlayer, ItemStack pStack, InteractionHand pHand) {
        if (level instanceof ServerLevel serverLevel) {
            inventory.interact(serverLevel, pPlayer, pHand);
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void onBreak(@Nullable Player player) {
        if (!level.isClientSide) {
            inventory.dumpItems(level, worldPosition);
        }
        super.onBreak(player);
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (spirit == null) {
            return;
        }
        if (timer-- <= 0) {
            triggerPassiveEffects(level);
            timer = 100;
        }
    }

    @Override
    public void commonTick(Level level) {
        var stack = inventory.getStackInSlot(0);
        if (stack.getItem() instanceof SpiritShardItem || hasLeftoverSpirit()) {
            if (visualEffectStrength < WARMUP_DURATION) {
                visualEffectStrength++;
            }
            return;
        }
        if (visualEffectStrength > 0) {
            visualEffectStrength--;
            if (visualEffectStrength == 0) {
                spirit = null;
                setDirty();
            }
        }
    }

    @Override
    public void clientTick(Level level) {
        if (inventory.getStackInSlot(0).getItem() instanceof SpiritShardItem item) {
            SpiritLightSpecs.rotatingLightSpecs(level, getItemPos(), item, 0.55f, 2);
        }
    }

    public void updateSpirit() {
        if (inventory.getStackInSlot(0).getItem() instanceof SpiritShardItem item) {
            if (spirit != item.getSpirit()) {
                unspentSpiritFuel = 0;
            }
            spirit = item.getSpirit();
        }
    }

    public void triggerPassiveEffects(ServerLevel level) {
        var holder = spirit.getHolder();
        if (RITE_EMPOWERMENT_EFFECTS.containsKey(holder)) {
            var effectHolder = RITE_EMPOWERMENT_EFFECTS.get(holder);
            if (effectHolder != null) {
                var params = SpiritRiteEffect.builder()
                        .setTotemHeight(2)
                        .build();
                var effect = effectHolder.get();
                if (effect.triggerRiteEffect(level, worldPosition, spirit, params)) {
                    spendSpiritFuel(level, 4);
                }
            }
        }
    }

    public void spendSpiritFuel(ServerLevel level, int fuelPerSpirit) {
        if (level instanceof ServerLevel serverLevel) {
            MalumParticleEffectTypes.ARCANA_PYLON_EATS_SPIRIT
                    .createEffect(worldPosition)
                    .color(spirit)
                    .customData(new ArcanaPylonEffectData(unspentSpiritFuel == 0 ? 1f : 0.5f))
                    .spawn(serverLevel);
        }
        if (unspentSpiritFuel == 0) {
            unspentSpiritFuel = fuelPerSpirit;
            inventory.getStackInSlot(0).shrink(1);
        }
        unspentSpiritFuel--;
        setDirty();
    }

    public boolean hasLeftoverSpirit() {
        return spirit != null && unspentSpiritFuel > 0;
    }

    public float getGlowDelta() {
        return visualEffectStrength / (float) WARMUP_DURATION;
    }

    public Vec3 getItemPos() {
        var blockPos = getBlockPos();
        var offset = getCentralItemOffset();
        return new Vec3(blockPos.getX() + offset.x, blockPos.getY() + offset.y, blockPos.getZ() + offset.z);
    }

    public Vec3 getCentralItemOffset() {
        return ITEM_OFFSET;
    }
}