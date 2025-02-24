package com.sammy.malum.common.item.augment.core;

import com.sammy.malum.core.systems.artifice.*;
import com.sammy.malum.registry.common.SpiritTypeRegistry;
import net.minecraft.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

import java.util.List;

public class SympathyDrive extends CoreAugmentItem {
    public SympathyDrive(Properties pProperties) {
        super(pProperties, List.of(SpiritTypeRegistry.SACRED_SPIRIT, SpiritTypeRegistry.WICKED_SPIRIT), true,
                new ArtificeModifier(ArtificeAttributeType.MISFORTUNE_REVERSAL, 1f));
    }

    public static void completeCycle(ArtificeAttributeData attributes, int durabilityCost) {
        float misfortuneReversal = attributes.misfortuneReversal.getValue(attributes);
        if (misfortuneReversal > 0) {
            attributes.sympathyDamageStacks += durabilityCost;
        }
        if (attributes.sympathyBuffedCycles > 0) {
            attributes.sympathyBuffedCycles--;
        }
    }

    public static void repairImpetus(Level level, ArtificeAttributeData attributes, ItemStack impetus) {
        float misfortuneReversal = attributes.misfortuneReversal.getValue(attributes);
        float repairPercentage = 0.02f * misfortuneReversal;
        float buffStrength = 0.2f + attributes.sympathyDamageStacks * 0.05f;
        int repairedAmount = Mth.ceil(impetus.getMaxDamage() * repairPercentage);
        impetus.setDamageValue(Math.max(impetus.getDamageValue() - repairedAmount, 0));
        attributes.sympathyDamageStacks = 0;
        attributes.sympathyBuffedCycles = 3;
        attributes.sympathyBuffStrength = buffStrength;
    }
}