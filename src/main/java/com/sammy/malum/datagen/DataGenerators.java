package com.sammy.malum.datagen;

import com.sammy.malum.MalumMod;
import com.sammy.malum.datagen.block.*;
import com.sammy.malum.datagen.item.MalumItemModels;
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

@EventBusSubscriber(modid = MalumMod.MALUM, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        final boolean includeClient = event.includeClient();
        final boolean includeServer = event.includeServer();

        var registryDataProvider = new RegistryDataGenerator(output, provider);
        generator.addProvider(includeServer, registryDataProvider);
        var registryProvider = registryDataProvider.getRegistryProvider();
        generator.addProvider(includeServer, new MalumDataMaps(output, registryProvider));

        var itemModelsProvider = new MalumItemModels(output, helper);

        generator.addProvider(includeClient, new MalumBlockStates(output, helper, itemModelsProvider));
        generator.addProvider(includeClient, itemModelsProvider);
        generator.addProvider(includeClient, new MalumLang(output));
        generator.addProvider(includeClient, new MalumSoundDatagen(output, helper));

        generator.addProvider(includeServer, new MalumBlockLootTables(output, registryProvider));


        var blockTagsProvider = new MalumBlockTagDatagen(output, registryProvider, helper);
        generator.addProvider(includeServer, blockTagsProvider);
        generator.addProvider(includeServer, new MalumItemTagDatagen(output, provider, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(includeServer, new MalumGeasTagDatagen(output, provider, helper));
        generator.addProvider(includeServer, new MalumBiomeTags(output, registryProvider, helper));
        generator.addProvider(includeServer, new MalumDamageTypeTagDatagen(output, registryProvider, helper));
        generator.addProvider(includeServer, new MalumEnchantmentTags(output, registryProvider, helper));


        generator.addProvider(includeServer, new MalumRecipes(output, registryProvider));

        generator.addProvider(includeServer, new MalumCuriosThings(output, helper, registryProvider));


    }
}
