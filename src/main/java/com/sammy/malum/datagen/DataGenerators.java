package com.sammy.malum.datagen;

import com.sammy.malum.MalumMod;
import com.sammy.malum.datagen.block.*;
import com.sammy.malum.datagen.item.MalumItemModelDatagen;
import com.sammy.malum.datagen.sound.*;
import com.sammy.malum.datagen.tag.MalumItemTagDatagen;
import com.sammy.malum.datagen.lang.*;
import com.sammy.malum.datagen.recipe.*;
import com.sammy.malum.datagen.tag.*;
import net.minecraft.core.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MalumMod.MALUM)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();

        var registryDataDatagen = new RegistryDataGenerator(output, provider);
        var registryProvider = registryDataDatagen.getRegistryProvider();

        generator.addProvider(includeServer, registryDataDatagen);

        var itemModelsDatagen = new MalumItemModelDatagen(output, helper);
        var blockStateDatagen = new MalumBlockStateDatagen(output, helper, itemModelsDatagen);
        var langDatagen = new MalumLangDatagen(output);
        var soundDatagen = new MalumSoundDatagen(output, helper);

        var dataMapDatagen = new MalumDataMapDatagen(output, registryProvider);
        var blockLootDatagen = new MalumBlockLootTables(output, registryProvider);

        var blockTagsDatagen = new MalumBlockTagDatagen(output, registryProvider, helper);
        var itemTagDatagen = new MalumItemTagDatagen(output, provider, blockTagsDatagen.contentsGetter(), helper);
        var geasTagDatagen = new MalumGeasTagDatagen(output, provider, helper);
        var entityTagDatagen = new MalumEntityTypeTagDatagen(output, provider, helper);
        var biomeTagDatagen = new MalumBiomeTagDatagen(output, registryProvider, helper);
        var enchantmentTagDatagen = new MalumEnchantmentTags(output, registryProvider, helper);
        var componentTagDatagen = new MalumDataComponentTypeTagDatagen(output, provider, helper);
        var damageTypeTagDatagen = new MalumDamageTypeTagDatagen(output, registryProvider, helper);

        var curioDataDatagen = new MalumCuriosThings(output, helper, registryProvider);
        var recipeDatagen = new MalumRecipes(output, registryProvider);

        generator.addProvider(includeClient, itemModelsDatagen);
        generator.addProvider(includeClient, blockStateDatagen);
        generator.addProvider(includeClient, langDatagen);
        generator.addProvider(includeClient, soundDatagen);

        generator.addProvider(includeServer, dataMapDatagen);
        generator.addProvider(includeServer, blockLootDatagen);

        generator.addProvider(includeServer, blockTagsDatagen);
        generator.addProvider(includeServer, itemTagDatagen);
        generator.addProvider(includeServer, geasTagDatagen);
        generator.addProvider(includeServer, entityTagDatagen);
        generator.addProvider(includeServer, biomeTagDatagen);
        generator.addProvider(includeServer, enchantmentTagDatagen);
        generator.addProvider(includeServer, componentTagDatagen);
        generator.addProvider(includeServer, damageTypeTagDatagen);

        generator.addProvider(includeServer, curioDataDatagen);
        generator.addProvider(includeServer, recipeDatagen);
    }
}
