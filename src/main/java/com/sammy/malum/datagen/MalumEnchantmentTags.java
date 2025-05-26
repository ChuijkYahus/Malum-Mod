package com.sammy.malum.datagen;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.enchantment.*;
import net.minecraft.core.HolderLookup.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.neoforged.neoforge.common.data.*;

import java.util.concurrent.*;

public class MalumEnchantmentTags extends EnchantmentTagsProvider {

    public MalumEnchantmentTags(PackOutput pOutput, CompletableFuture<Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        tag(EnchantmentTags.NON_TREASURE).add(
                EnchantmentKeys.HAUNTED, EnchantmentKeys.ANIMATED,
                EnchantmentKeys.ASCENSION, EnchantmentKeys.REBOUND,
                EnchantmentKeys.REPLENISHING, EnchantmentKeys.CAPACITOR,
                EnchantmentKeys.SPIRIT_PLUNDER);
        tag(EnchantmentTags.IN_ENCHANTING_TABLE).add(
                EnchantmentKeys.HAUNTED, EnchantmentKeys.ANIMATED,
                EnchantmentKeys.ASCENSION, EnchantmentKeys.REBOUND,
                EnchantmentKeys.REPLENISHING, EnchantmentKeys.CAPACITOR,
                EnchantmentKeys.SPIRIT_PLUNDER);
    }
}