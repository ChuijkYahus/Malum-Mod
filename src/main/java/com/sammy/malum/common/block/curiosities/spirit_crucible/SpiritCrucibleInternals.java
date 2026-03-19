package com.sammy.malum.common.block.curiosities.spirit_crucible;

import com.sammy.malum.common.item.augment.MendingDiffuserItem;
import com.sammy.malum.common.item.augment.ShieldingApparatusItem;
import com.sammy.malum.common.item.augment.WarpingEngineItem;
import com.sammy.malum.common.item.augment.core.SuspiciousDeviceItem;
import com.sammy.malum.common.item.augment.core.SympathyDrive;
import com.sammy.malum.registry.common.MalumDataMaps;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import team.lodestar.lodestone.helpers.RandomHelper;

public class SpiritCrucibleInternals {

    public static void addCrackFeedback(ServerLevel level, SpiritCrucibleCoreBlockEntity crucible) {
        if (crucible.queuedCracks <= 0) {
            crucible.crackTimer = 0;
            return;
        }
        crucible.crackTimer++;
        if (crucible.crackTimer % 5 == 0) {
            var random = level.getRandom();
            float pitchDelta = (crucible.crackTimer / 15f);
            float pitch = RandomHelper.randomBetween(random, 0.9f, 1.1f) * Mth.lerp(pitchDelta, 0.9f, 1.1f);
            crucible.playSound(MalumSoundEvents.IMPETUS_CRACK.get(), 0.5f, pitch);
            crucible.queuedCracks--;
            if (crucible.crackTimer >= 15) {
                crucible.crackTimer = 0;
            }
        }
    }

    public static void tickFocusingCycle(ServerLevel level, SpiritCrucibleCoreBlockEntity crucible) {
        var recipe = crucible.recipe;
        if (crucible.isCrafting == (recipe == null)) {
            crucible.isCrafting = !crucible.isCrafting;
            crucible.setDirty();
        }
        if (crucible.isCrafting) {
            if (recipe == null) {
                crucible.isCrafting = false;
                return;
            }
            var attributes = crucible.attributes;
            float speed = attributes.focusingSpeed.getValue(attributes);
            attributes.getInfluenceData(level).ifPresent(d -> d.tickInfluences(level, crucible));
            crucible.progress += speed;
            if (crucible.progress >= recipe.getTime()) {
                completeFocusingCycle(level, crucible);
            }
        } else {
            if (crucible.progress != 0) {
                crucible.progress = 0;
                crucible.invalidateModifiers(level);
            }
        }
    }

    public static void completeFocusingCycle(ServerLevel level, SpiritCrucibleCoreBlockEntity crucible) {
        handleFocusingCycleData(level, crucible);
        spawnFocusingOutput(level, crucible);
        addFocusingFeedback(level, crucible);
        crucible.updateRecipe();
        crucible.notifyObservers();
        crucible.setDirty();
    }

    public static void handleFocusingCycleData(ServerLevel level, SpiritCrucibleCoreBlockEntity crucible) {
        var pos = crucible.getBlockPos();
        var recipe = crucible.recipe;
        var coreAugment = crucible.coreAugmentInventory.getStackInSlot(0);
        var attributes = crucible.attributes;
        float speed = attributes.focusingSpeed.getValue(attributes);

        int durabilityCost = applyImpetusDamage(level, crucible);
        crucible.queuedCracks += durabilityCost;
        crucible.spiritInventory.spendSpiritsOnRecipe(recipe.getSpirits());

        if (coreAugment.getItem() instanceof SuspiciousDeviceItem) {
            SuspiciousDeviceItem.blowUp(level, pos);
        }
        if (WarpingEngineItem.skipForward(level, pos, attributes)) {
            crucible.progress = recipe.getTime() - 10 * speed;
            return;
        }
        crucible.progress = 0;
        SympathyDrive.completeFocusingCycle(attributes, durabilityCost);
    }

    public static void spawnFocusingOutput(ServerLevel level, SpiritCrucibleCoreBlockEntity crucible) {
        var recipe = crucible.recipe;
        var attributes = crucible.attributes;
        var outputStack = recipe.createOutput();
        var itemPos = crucible.getItemPos();
        float fortuneChance = attributes.fortuneChance.getValue(attributes);
        level.addFreshEntity(new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, outputStack));
        while (fortuneChance > 0) {
            if (fortuneChance >= 1 || level.random.nextFloat() < fortuneChance) {
                level.addFreshEntity(new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, outputStack.copy()));
            }
            fortuneChance -= 1;
        }
    }

    public static void addFocusingFeedback(ServerLevel level, SpiritCrucibleCoreBlockEntity crucible) {
        var recipe = crucible.recipe;
        var pos = crucible.getBlockPos();
        var spirits = recipe.getSpirits();
        MalumParticleEffectTypes.SPIRIT_CRUCIBLE_CRAFTS.createEffect(pos)
                .color(MalumNetworkedParticleEffectColorData.fromSpirits(spirits))
                .spawn(level);
        float pitch = RandomHelper.randomBetween(level.random, 0.75f, 1.25f);
        crucible.playSound(MalumSoundEvents.CRUCIBLE_CRAFT.get(), 1, pitch);
    }

    public static int applyImpetusDamage(ServerLevel level, SpiritCrucibleCoreBlockEntity crucible) {
        var inventory = crucible.inventory;
        var impetus = inventory.getStackInSlot(0);
        var attributes = crucible.attributes;
        float instability = attributes.instability.getValue(attributes);
        var random = level.random;
        int durabilityCost = 0;
        if (!ShieldingApparatusItem.shieldImpetus(level, crucible.getBlockPos(), attributes)) {
            var recipe = crucible.recipe;
            int recipeCost = recipe.getDurabilityCost();
            if (recipeCost != 0 && impetus.isDamageableItem()) {
                durabilityCost = recipeCost;
                if (instability > 0 && random.nextFloat() < instability) {
                    durabilityCost *= 2;
                    if (instability > 1) {
                        durabilityCost = Math.round(durabilityCost * (instability));
                    }
                }
            }
        }
        if (durabilityCost > 0) {
            impetus.hurtAndBreak(durabilityCost, level, null, item -> {
                var key = BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow();
                var data = BuiltInRegistries.ITEM.getData(MalumDataMaps.FRACTURED_IMPETUS_VARIANT, key);
                if (data != null) {
                    inventory.setStackInSlot(0, data.otherImpetus().value().getDefaultInstance());
                }
            });
            if (MendingDiffuserItem.repairImpetus(level, attributes, impetus)) {
                SympathyDrive.repairImpetus(level, attributes, impetus);
            }
        }
        return durabilityCost;
    }
}
