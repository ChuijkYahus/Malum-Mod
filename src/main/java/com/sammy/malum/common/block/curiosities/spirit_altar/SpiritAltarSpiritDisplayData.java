package com.sammy.malum.common.block.curiosities.spirit_altar;

import dev.kosmx.playerAnim.core.util.MathHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.helpers.VecHelper;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.inventory.ItemStackHandlerItemDisplayData;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackBlockHandler;

import java.util.Objects;

public class SpiritAltarSpiritDisplayData extends ItemStackHandlerItemDisplayData {

    protected static final int WARMUP_DURATION = 30;

    protected final SpiritAltarBlockEntity altar;
    public int warmupTicks;

    public SpiritAltarSpiritDisplayData(LodestoneItemStackBlockHandler parent) {
        super(parent, 0.015f, 0.1f);
        altar = (SpiritAltarBlockEntity) parent.getParent();
    }

    @Override
    public ItemDisplayDataEntry addNewItem(int index, ItemStack stack) {
        RandomSource random = Objects.requireNonNull(handler.getParent().getLevel()).random;
        return super.addNewItem(index, stack).setAngle(random.nextFloat() * 6.28f);
    }

    @Override
    public void tick(LodestoneBlockEntity parent, Level level, BlockPos pos, BlockState state) {
        int target = altar.possibleRecipes.isEmpty() ? 0 : WARMUP_DURATION;
        warmupTicks = DataHelper.approach(warmupTicks, target, 1);
        super.tick(parent, level, pos, state);
        turn += getSpinUp(Easing.SINE_IN_OUT) * 0.05f + altar.speed * 0.5f;
    }

    @Override
    public float getDistanceForItem(ItemDisplayDataEntry item, int index, float total) {
        float angle = item.getAngle(0);
        float distanceOscillation = Mth.sin(angle) * 0.025f;
        return 1 - getSpinUp(Easing.SINE_OUT) * 0.25f + distanceOscillation;
    }

    @Override
    public float getLiftForItem(ItemDisplayDataEntry item, int index, float total) {
        return 0.25f + getSpinUp(Easing.QUARTIC_OUT) * getSpinUp(Easing.BACK_OUT) * 0.5f;
    }

    @Override
    public float getItemScaleForItem(ItemDisplayDataEntry item, int index, float total) {
        float delta = Math.min(item.getAge() / 6f, 1f);
        return Easing.SINE_IN_OUT.lerp(delta, 0f, 0.5f);
    }

    public float getSpinUp(Easing easing) {
        return easing.ease(warmupTicks / (float)WARMUP_DURATION);
    }
}